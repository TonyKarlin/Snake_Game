package api_client;

import tools.jackson.databind.ObjectMapper;
import utils.Log;
import utils.dto.GameDTO;
import utils.dto.response.HiscoresResponse;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.*;

public class ApiClient implements IApiClient {
    private static final String BASE_URL = "http://localhost:8080";
    private final HttpClient http;
    private final ObjectMapper mapper;
    private final ExecutorService executors;

    public ApiClient() {
        this.mapper = new ObjectMapper();

        this.executors = Executors.newVirtualThreadPerTaskExecutor();
        this.http = HttpClient.newBuilder()
            .executor(executors)
            .connectTimeout(Duration.ofSeconds(5))
            .build();
    }

    public CompletableFuture<List<GameDTO>> sendGetRequest() {
        HttpRequest request = buildGetRequest();
        return http.sendAsync(request, HttpResponse.BodyHandlers.ofString())
            .thenApply(res -> {
                validateStatusCode(res.statusCode());
                validateResponse(res.body());

                try {
                    return deserializeFromJson(res.body());
                } catch (Exception e) {
                    Log.error("Failed to parse GET response: " + e.getMessage());
                    throw new RuntimeException("Failed to parse GET response", e);
                }
            });
    }

    public CompletableFuture<Void> sendPostRequest(GameDTO dto) {
        final String json;
        try {
            json = serializeToJson(dto);
        } catch (RuntimeException e) {
            Log.error("Failed to serialize GameDTO: " + e.getMessage());
            return CompletableFuture.failedFuture(e);
        }

        HttpRequest request = buildPostRequest(json);

        return http.sendAsync(request, HttpResponse.BodyHandlers.ofString())
            .thenAccept(res -> validateStatusCode(res.statusCode()));
    }

    private HttpRequest buildGetRequest() {
        return HttpRequest.newBuilder()
            .uri(URI.create(getHiscoreUrl()))
            .header("Accept", "application/json")
            .GET()
            .build();
    }

    private HttpRequest buildPostRequest(String json) {
        return HttpRequest.newBuilder()
            .uri(URI.create(getHiscoreUrl()))
            .header("Content-Type", "application/json")
            .header("Accept", "application/json")
            .POST(HttpRequest.BodyPublishers.ofString(json))
            .build();
    }

    private String serializeToJson(GameDTO dto) {
        try {
            return mapper.writeValueAsString(dto);
        } catch (Exception e) {
            throw new RuntimeException("JSON serialization failed", e);
        }
    }

    private List<GameDTO> deserializeFromJson(String json) {
        try {
            HiscoresResponse response = mapper.readValue(json, HiscoresResponse.class);

            if (response == null || response.getEmbedded() == null || response.getEmbedded().getGameList() == null) {
                return List.of();
            }

            return response.getEmbedded().getGameList();
        } catch (Exception e) {
            throw new RuntimeException("Failed to parse JSON response", e);
        }
    }
    
    private void validateStatusCode(int code) {
        if (code < 200 || code >= 300) {
            Log.error("Request failed with status code: " + code);
            throw new RuntimeException("Request failed with status code: " + code);
        }
    }
    
    private void validateResponse(String body) {
        if (body == null || body.isEmpty()) {
            Log.error("Response body is empty");
            throw new RuntimeException("Response body is empty");
        }
    }

    public void shutdownExecutors() {
        executors.shutdown();
    }

    private String getHiscoreUrl() {
        return BASE_URL + ApiPaths.ROOT.getPath() + ApiPaths.HISCORES.getPath();
    }

    @Override
    public CompletableFuture<List<GameDTO>> loadHiscores() {
        return sendGetRequest()
            .thenApply(list -> {
                Log.log("Hiscores loaded successfully");
                return list;
            })
            .exceptionally(ex -> {
                Log.error("Failed to load hiscores: " + ex.getMessage());
                throw new RuntimeException(ex);
            });
    }

    @Override
    public CompletableFuture<Void> submitScore(GameDTO game) {
        return sendPostRequest(game)
            .thenRun(() -> Log.log("Score submitted successfully"))
            .exceptionally(ex -> {
                Log.error("Failed to submit score: " + ex.getMessage());
                throw new CompletionException(ex);
            });
    }
}

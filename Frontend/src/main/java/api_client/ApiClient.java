package api_client;

import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;
import utils.Log;
import utils.dto.GameDTO;
import utils.dto.response.HiscoresResponse;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.sql.SQLOutput;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.*;

public class ApiClient implements IApiClient {
    private static final String BASE_URL = "http://localhost:8080";
    private final HttpClient http;
    private final ObjectMapper mapper;
    private final ExecutorService executorService;

    public ApiClient() {
        this.mapper = new ObjectMapper();

        this.executorService = Executors.newFixedThreadPool(2);
        this.http = HttpClient.newBuilder()
            .executor(executorService)
            .connectTimeout(Duration.ofSeconds(5))
            .build();
    }

    public CompletableFuture<List<GameDTO>> sendGetRequest() {
        HttpRequest request = buildGetRequest();
        return http.sendAsync(request, HttpResponse.BodyHandlers.ofString())
            .thenApply(res -> {
                int statusCode = res.statusCode();
                if (statusCode < 200 || statusCode >= 300) {
                    Log.error("GET request failed with status code: " + statusCode);
                    throw new RuntimeException("GET failed with status code: " + statusCode);
                }

                try {
                    System.out.println("GET response body: " + res.body());
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
            .thenApply(res -> {
                int statusCode = res.statusCode();
                if (statusCode < 200 || statusCode >= 300) {
                    Log.error("POST request failed with status code: " + statusCode);
                    throw new RuntimeException("POST failed with status code: " + statusCode);
                }
                return null;
            });
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

    public void shutdownExecutors() {
        executorService.shutdown();
    }

    private String getHiscoreUrl() {
        return BASE_URL + ApiPaths.ROOT.getPath() + ApiPaths.HISCORES.getPath();
    }

    @Override
    public CompletableFuture<List<GameDTO>> loadHiscores() {
        return sendGetRequest()
            .thenApply(list -> {
                Log.log("Loaded hiscores: " + list.size() + " entries");
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

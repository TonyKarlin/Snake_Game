package api_client;

import utils.dto.GameDTO;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface IApiClient {
    CompletableFuture<List<GameDTO>> loadHiscores();
    CompletableFuture<Void> submitScore(GameDTO game);
}

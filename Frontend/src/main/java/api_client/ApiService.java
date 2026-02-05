package api_client;

import utils.dto.GameDTO;

public class ApiService {
    private final IApiClient api;

    public ApiService(IApiClient client) {
        this.api = client;
    }

//    public static void main(String[] args) {
//        IApiClient client = new ApiClient();
//        ApiService service = new ApiService(client);
//
//        GameDTO dto = new GameDTO("Test Game", 23, 69.8, 230);
//        client.loadHiscores();
//        client.submitScore(dto);
//        client.loadHiscores();
//    }
}



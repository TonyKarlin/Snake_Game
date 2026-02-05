package api_client;

import utils.dto.GameDTO;

public class ApiService {
    private final IApiClient api;

    public ApiService(IApiClient client) {
        this.api = client;
    }
}

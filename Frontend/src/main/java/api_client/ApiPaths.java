package api_client;

public enum ApiPaths {
    ROOT("/api"),
    HISCORES("/hiscores"),
    GAME_BY_ID("/hiscores/{gameId}"),
    GAME_BY_NAME("/hiscores/name/{gameName}");

    private final String path;
    
    ApiPaths(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}

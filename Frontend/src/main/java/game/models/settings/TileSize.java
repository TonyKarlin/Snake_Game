package game.models.settings;

public enum TileSize {
    SMALL(20),
    MEDIUM(25),
    LARGE(30);

    private final int value;

    TileSize(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}

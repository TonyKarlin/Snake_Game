package game.models.settings;

public enum TileSize {
    SMALL(20),
    MEDIUM(22),
    LARGE(24);

    private final int value;

    TileSize(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}

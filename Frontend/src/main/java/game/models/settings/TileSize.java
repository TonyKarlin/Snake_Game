package game.models.settings;

public enum TileSize {
    SMALL(16),
    MEDIUM(20),
    LARGE(24);

    private final int value;

    TileSize(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}

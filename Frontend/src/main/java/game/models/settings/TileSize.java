package game.models.settings;

public enum TileSize {
    TWENTY(20),
    FOURTY(40),
    FIFTY(50);

    private final int value;

    TileSize(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}

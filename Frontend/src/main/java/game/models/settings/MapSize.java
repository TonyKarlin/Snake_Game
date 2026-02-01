package game.models.settings;

public enum MapSize {
    SMALL(15),
    MEDIUM(23),
    LARGE(27);

    private final int value;

    MapSize(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}

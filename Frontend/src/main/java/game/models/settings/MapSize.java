package game.models.settings;

public enum MapSize {
    SMALL(17),
    MEDIUM(21),
    LARGE(25);

    private final int value;

    MapSize(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}

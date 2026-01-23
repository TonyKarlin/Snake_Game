package game.models.settings;

public enum MapSize {
    SMALL(11),
    MEDIUM(15),
    LARGE(21);

    private final int value;

    MapSize(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}

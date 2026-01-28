package game.models.settings;

public enum MapSize {
    SMALL(13),
    MEDIUM(15),
    LARGE(17);

    private final int value;

    MapSize(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}

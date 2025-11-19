package game.settings;

public enum MapSize {
    SIXTEEN(16),
    TWENTY_FOUR(24),
    THIRTY_TWO(32),
    FORTY_EIGHT(48);

    private final int value;

    MapSize(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}

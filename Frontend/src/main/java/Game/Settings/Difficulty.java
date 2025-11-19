package game.Settings;



public enum Difficulty {
    NORMAL(333),
    HARD(250),
    EXTREME(200);

    private final int value;

    Difficulty(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}

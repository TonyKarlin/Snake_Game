package game.models.settings;



public enum Difficulty {
    EASY(400),
    MEDIUM(250),
    HARD(150);

    private final int tickSpeedMs;

    Difficulty(int value) {
        this.tickSpeedMs = value;
    }

    public int getValue() {
        return tickSpeedMs;
    }
}

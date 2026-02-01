package game.models.settings;



public enum Difficulty {
    EASY(300),
    MEDIUM(200),
    HARD(100);

    private final int tickSpeedMs;

    Difficulty(int value) {
        this.tickSpeedMs = value;
    }

    public int getValue() {
        return tickSpeedMs;
    }
}

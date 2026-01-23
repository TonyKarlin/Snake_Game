package game.models.settings;



public enum Difficulty {
    EASY(300),
    MEDIUM(200),
    HARD(100);

    private final int tickSpeed;

    Difficulty(int value) {
        this.tickSpeed = value;
    }

    public int getValue() {
        return tickSpeed;
    }
}

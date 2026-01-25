package game.models;

public class GameEngine {
    private final GameState state;
    private double start;
    private double finish;
    private double elapsedTime = finish - start;
    private double tick; // A set value for elapsed time between game cycles / ticks
    
    public GameEngine() {
        this.state = new GameState();
        this.start = System.currentTimeMillis();
    }

    public void setTick(double tick) {
        this.tick = tick;
    }
}

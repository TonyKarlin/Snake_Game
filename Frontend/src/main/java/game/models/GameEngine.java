package game.models;


import game.models.character.Direction;
import game.models.character.Snake;

public class GameEngine {
    private final GameState state;
    private double start;
    private long tickRate; // A set value for elapsed time between game cycles / ticks
    private long lastTick;
    
    public GameEngine(GameState state) {
        this.state = state;
        this.lastTick = System.currentTimeMillis();
    }

    private void moveSnake() {
        Snake snake = state.getSnake();
        Position headPos = snake.getHead().getPosition();
        Direction dir = state.getCurrentDirection();

        Position next = dir.move(headPos);

        // TODO: collision checks
        // TODO: food checks

        // Move the snake
    }
    
    public Position getNextTile(Position headPos) {
        int x = headPos.getX();
        int y = headPos.getY();
        switch (state.getCurrentDirection()) {
            case Direction.UP -> y--;
            case Direction.DOWN -> y++;
            case Direction.LEFT -> x--;
            case Direction.RIGHT -> x++;
            default -> throw new IllegalStateException("Unexpected value: " + state.getCurrentDirection());
        }
        return new Position(x, y);
    }

    public void update() {
        long now = System.currentTimeMillis();
        if (now - lastTick >= tickRate) {
            moveSnake();
            lastTick = now;
        }
    }

    public void setTickRate(long tickRate) {
        this.tickRate = tickRate;
    }
}

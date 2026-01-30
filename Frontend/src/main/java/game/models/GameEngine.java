package game.models;


import game.models.character.Direction;
import game.models.character.Snake;
import game.models.map.Map;

import java.util.Queue;

public class GameEngine {
    private final GameState state;
    private final Map map;
    private final Snake snake;
    
    private long tickRate; // A set value for elapsed time between game cycles / ticks
    private long lastTick;
    
    private Direction currentDirection;
    private Direction queuedDirection;
    
    private boolean isRunning = false;

    public GameEngine(GameState state, Map map, Snake snake) {
        this.state = state;
        this.map = map;
        this.snake = snake;
    }
    
    public void start() {
        this.isRunning = true;
        this.currentDirection = Direction.UP;
        queuedDirection = null;
    }
    
    public void stop() {
        this.isRunning = false;
    }
    
    public void reset() {
        this.isRunning = false;
        this.currentDirection = null;
        this.queuedDirection = null;
        this.lastTick = 0;
    }
    
    public void restart() {
        reset();
        start();
    }

    public boolean loop(long time) {
        if (!isRunning) return false;
        
        if (lastTick == 0) {
            lastTick = time;
            return false;
        }

        if (time - lastTick < tickRate) {
            return false;
        }

        if (queuedDirection != null) {
            currentDirection = queuedDirection;
            queuedDirection = null;
        }
        
        lastTick = time;
        moveSnake();

        return true;
    }

    private void moveSnake() {
        Position headPos = snake.getHead().getPosition();
        Position nextPos = currentDirection.move(headPos);

        boolean collision = map.isObstacle(nextPos) || snake.isCollidingWithSelf(nextPos);
        if (collision) {
            state.setGameOver(true);
            return;
        }

        snake.addToTheHead(nextPos);
        if (map.isFood(nextPos)) {
            onFoodEaten(nextPos);
        } else {
            snake.removeTail();
        }
    }
    
    private void onFoodEaten(Position pos) {
        state.increaseScore(10);
        state.incrementFoodEaten();
        map.resetTile(pos.getX(), pos.getY());
    }

    public void changeDirection(Direction newDirection) {
        if (!isRunning) return;
        if (newDirection == currentDirection.getOpposite()) return;
        
        queuedDirection = newDirection;
    }

    public void setTickRate(long tickRate) {
        this.tickRate = tickRate * 1_000_000;
    }
}

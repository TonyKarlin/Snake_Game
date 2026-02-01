package game.models;


import game.models.character.Direction;
import game.models.character.Snake;
import game.models.map.Map;
import utils.Clock;


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

    public boolean loop(long time) {
        if (!isRunning) return false;
        Clock.getInstance().update(time);

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
        if (!map.isFoodGenerated()) {
            map.generateFood(map.getSize());
        }

        lastTick = time;

        Position nextPos = moveSnake();

        if (checkCollision(nextPos)) {
            state.setGameOver(true);
            return false;
        }
        updateSnake(nextPos);

        return true;
    }
    
    public void start() {
        reset();
        this.isRunning = true;
        if (currentDirection == null) {
            this.currentDirection = Direction.UP;
        }
        queuedDirection = null;
        Clock.getInstance().reset();
    }
    
    public void pause() {
        this.isRunning = false;
    }
    
    public void resume() {
        this.isRunning = true;
    }
    
    public void reset() {
        this.isRunning = false;
        this.currentDirection = null;
        this.queuedDirection = null;
        this.lastTick = 0;
        state.reset();
    }
    
    public void restart() {
        reset();
        start();
    }

    private Position moveSnake() {
        Position headPos = snake.getHead().getPosition();
        return currentDirection.move(headPos);
    }
    
    private void updateSnake(Position nextPos) {
        snake.addToTheHead(nextPos);
        if (map.isFood(nextPos)) {
            onFoodEaten(nextPos);
            snake.grow();
        } else {
            snake.removeTail();
            snake.getOccupiedPositions().remove(snake.getTail().getPosition());
        }
        snake.getOccupiedPositions().add(nextPos);
    }

    private boolean checkCollision(Position nextPos) {
        if (map.isObstacle(nextPos)) return true;
        
        boolean isEating = map.isFood(nextPos);
        
        if (!isEating && nextPos.equals(snake.getTail().getPosition())) {
            return false;
        }
        
        return snake.isCollidingWithSelf(nextPos);
    }
    
    private void onFoodEaten(Position pos) {
        map.setFoodGenerated(false);
        state.increaseScore(10);
        state.incrementFoodEaten();
        map.resetTile(pos.x(), pos.y());
    }

    public void changeDirection(Direction newDirection) {
        if (!isRunning) return;
        if (newDirection == currentDirection.getOpposite()) return;
        
        queuedDirection = newDirection;
    }

    public void setTickRate(long tickRate) {
        this.tickRate = tickRate * 1_000_000; // converts ms to ns
    }

    public boolean isRunning() {
        return isRunning;
    }
}

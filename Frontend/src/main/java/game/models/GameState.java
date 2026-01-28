package game.models;


import game.models.character.Direction;
import game.models.character.Snake;
import game.view.flyweight.SnakeBodyType;

public class GameState {
    private int score;
    private final Snake snake;
    private boolean isGameOver;
    private Direction currentDirection;
    

    public GameState(Snake snake) {
        this.score = 0;
        this.snake = snake;
        this.currentDirection = Direction.UP;
        this.isGameOver = false;
    }
    
    public void update() {
        
    }
    
    public void moveSnake() {
        
    }
    
//    public void setNextTile() {
//        engine.getNextTile(snake.getHeadPosition(), currentDirection);
//    }
    
    public void onFoodEaten() {
        snake.grow();
        score += 10;
        snake.addToTheTail(SnakeBodyType.TAIL);
    }

    public int getScore() {
        return score;
    }

    public boolean isGameOver() {
        return isGameOver;
    }

    public void setGameOver(boolean gameOver) {
        isGameOver = gameOver;
    }

    public Direction getCurrentDirection() {
        return currentDirection;
    }

    public void setCurrentDirection(Direction currentDirection) {
        this.currentDirection = currentDirection;
    }

    public Snake getSnake() {
        return snake;
    }
}

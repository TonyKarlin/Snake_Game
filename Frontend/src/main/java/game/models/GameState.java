package game.models;


import game.models.character.Direction;
import game.models.character.Snake;
import game.view.flyweight.SnakeBodyType;
import utils.context.AppContext;

public class GameState {
    private int score;
    private int foodEaten;
    private boolean isGameOver;
    private double startTime;
    private double endTime;
    

    public GameState() {
        this.score = 0;
        this.isGameOver = false;
        this.startTime = System.currentTimeMillis();
    }
    
    public void update() {
        
    }
    
    public void increaseScore(int increment) {
        this.score += increment;
    }
    
    public void incrementFoodEaten() {
        this.foodEaten++;
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
}

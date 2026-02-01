package game.models;

import utils.Clock;


public class GameState {
    private int score;
    private int foodEaten;
    private boolean isGameOver;
    private double survivalTime;
    
    public void reset() {
        this.score = 0;
        this.foodEaten = 0;
        this.isGameOver = false;
        this.survivalTime = 0;
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
        if (gameOver && !isGameOver) {
            this.survivalTime = Clock.getInstance().getElapsedTimeInSeconds();
        }
        isGameOver = gameOver;
        Clock.getInstance().reset();
    }

    public int getFoodEaten() {
        return foodEaten;
    }

    public void setSurvivalTime(long survivalTime) {
        this.survivalTime = (double) survivalTime;
    }

    public double getSurvivalTime() {
        return survivalTime;
    }
    
    public double getTimeBetweenFoodEaten() {
        return foodEaten > 0 ? survivalTime / foodEaten : 0;
    }
}

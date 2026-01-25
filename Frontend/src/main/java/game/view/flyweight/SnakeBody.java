package game.view.flyweight;

import javafx.scene.layout.HBox;


public class SnakeBody implements Flyweight{
    private final HBox snakeBody;
    
    public SnakeBody() {
        this.snakeBody = new HBox();
        this.snakeBody.getStyleClass().add("Snake");
    }
    
    @Override
    public HBox getSnakeBody() {
        return snakeBody;
    }
}

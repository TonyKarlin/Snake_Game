package game.view.flyweight;

import javafx.scene.paint.Color;

import java.util.HashMap;
import java.util.Map;

public class SnakeBodyFactory {
    private static final Map<SnakeBodyType, SnakeBody> snakeExtensionGraphics = new HashMap<>();
    
    public static SnakeBody getBodyExtension(SnakeBodyType type) {
        SnakeBody extension = snakeExtensionGraphics.get(type);
        if (extension == null) {
            extension = create(type);
            snakeExtensionGraphics.put(type, extension);
        }
        return extension;
    }

    private static SnakeBody create(SnakeBodyType type) {
        return switch (type) {
            case HEAD -> new SnakeBody(Color.LIMEGREEN);
            case BODY -> new SnakeBody(Color.GREEN);
            case TAIL -> new SnakeBody(Color.DARKGREEN);
        };
    }
}

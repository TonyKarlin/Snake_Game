package utils.flyweight;

import javafx.scene.layout.HBox;

import java.util.HashMap;
import java.util.Map;

public class SnakeBodyFactory {
    private static final Map<SnakeBodyType, SnakeBody> snakeExtensionGraphics = new HashMap<>();
    
    public static SnakeBody getBodyExtension(SnakeBodyType type) {
        SnakeBody extension = snakeExtensionGraphics.get(type);
        if (extension == null) {
            switch (type) {
                case CLASSIC, BLOODY, BLOAT -> extension = new SnakeBody();
                default -> throw new IllegalArgumentException("Unkown extension type");
            }
            snakeExtensionGraphics.put(type, extension);
        }
        return extension;
    }
}

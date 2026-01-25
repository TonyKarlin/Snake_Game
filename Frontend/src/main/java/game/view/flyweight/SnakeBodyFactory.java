package game.view.flyweight;

import java.util.HashMap;
import java.util.Map;

public class SnakeBodyFactory {
    private static final Map<SnakeBodyType, SnakeBody> snakeExtensionGraphics = new HashMap<>();
    
    public static SnakeBody getBodyExtension(SnakeBodyType type) {
        SnakeBody extension = snakeExtensionGraphics.get(type);
        if (extension == null) {
            // allows for easy addition of new graphic types in the future
            // e.g. different skins for the snake
            switch (type) {
                case BODY -> extension = new SnakeBody();
                default -> throw new IllegalArgumentException("Unkown extension type");
            }
            snakeExtensionGraphics.put(type, extension);
        }
        return extension;
    }
}

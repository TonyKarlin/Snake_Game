package game.view.flyweight;

import javafx.scene.canvas.GraphicsContext;


public interface Flyweight {
    void draw(GraphicsContext gc, int x, int y, int cellSize, double sizeMultiplier);
}

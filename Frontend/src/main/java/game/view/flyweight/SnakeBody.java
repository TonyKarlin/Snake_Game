package game.view.flyweight;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;


public class SnakeBody implements Flyweight{
    private final Color fillColor;

    public SnakeBody(Color fillColor) {
        this.fillColor = fillColor;
    }

    @Override
    public void draw(GraphicsContext gc, int x, int y, int cellSize, double sizeMultiplier) {
        gc.setFill(fillColor);

        double drawSize = cellSize * sizeMultiplier; // size of the oval based on the body part multiplier
        double offset = (cellSize - drawSize) / 2.0; // centers the oval within the cell

        gc.fillOval(
            x * cellSize + offset,
            y * cellSize + offset,
            drawSize,
            drawSize + 1
        );
    }
}

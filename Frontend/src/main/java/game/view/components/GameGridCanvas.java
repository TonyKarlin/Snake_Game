package game.view.components;

import game.models.map.TileType;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class GameGridCanvas extends GameGrid {
    
    
    public GameGridCanvas(int cellSize) {
        super(cellSize);
    }


    @Override
    protected void drawTile(
        GraphicsContext gc,
        TileType type,
        int x,
        int y
    ) {
        switch (type) {
            case OBSTACLE -> gc.setFill(Color.DARKGRAY);
            case FOOD -> gc.setFill(Color.RED);
            case EMPTY -> gc.setFill(Color.BLACK);
        }

        gc.fillRect(
            x * cellSize,
            y * cellSize,
            cellSize,
            cellSize
        );
    }
}

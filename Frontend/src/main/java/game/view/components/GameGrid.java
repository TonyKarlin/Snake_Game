package game.view.components;

import game.models.map.TileType;
import game.models.settings.TileSize;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public abstract class GameGrid {

    protected final int cellSize;

    protected GameGrid(int cellSize) {
        this.cellSize = cellSize;
    }

    public void render(GraphicsContext gc, TileType[][] logicalMap) {
        int size = logicalMap.length;

        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                drawTile(gc, logicalMap[x][y], x, y);
            }
        }
    }

    protected abstract void drawTile(
        GraphicsContext gc,
        TileType type,
        int x,
        int y
    );
}

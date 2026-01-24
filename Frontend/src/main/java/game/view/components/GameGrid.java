package game.view.components;

import game.models.map.TileType;
import game.models.map.factory.FoodTile;
import game.models.map.factory.ITile;
import game.models.map.factory.EmptyTile;
import game.models.map.factory.ObstacleTile;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;

public abstract class GameGrid {
    
    public void populateGrid(GridPane gridPane, TileType[][] logicalMap) {
        int size = logicalMap.length;
        VBox[][] tileSet = new VBox[size][size];
        setGridSize(gridPane, size);
        
        for(int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                ITile tile = createTileForType(logicalMap[i][j]);
                tileSet[i][j] = tile.getTile();
                gridPane.add(tileSet[i][j], i, j);
            }
        }
    }
    
    private void setGridSize(GridPane gridPane, int size) {
        gridPane.getColumnConstraints().clear();
        gridPane.getRowConstraints().clear();
        
        for (int i = 0; i < size; i++) {
            gridPane.getColumnConstraints().add(new ColumnConstraints(gridPane.getPrefWidth() / size));
            gridPane.getRowConstraints().add(new RowConstraints(gridPane.getPrefHeight() / size));
        }
    }

    private ITile createTileForType(TileType type) {
        return switch (type) {
            case OBSTACLE -> new ObstacleTile();
            case FOOD -> new FoodTile();
            default -> new EmptyTile();
        };
    }

    public abstract ITile createTile();
}

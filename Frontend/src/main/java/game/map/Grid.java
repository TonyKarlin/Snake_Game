package game.map;

import game.map.factory.ITile;
import game.map.factory.MovementTile;
import game.map.factory.ObstacleTile;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;

public abstract class Grid {
    private VBox[][] tileSet;

    public void populateGrid(int size, GridPane gridPane) {
        this.tileSet = new VBox[size][size];
        
        setGridSize(gridPane, size);
        
        for(int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
//              ITile tile = Math.random() < 0.05 ? new MovementTile() : new ObstacleTile();
                ITile tile = new MovementTile();
                VBox concreteTile = tile.getTile();
                this.getTileSet()[i][j] = concreteTile;
                gridPane.add(concreteTile, i, j);
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


    public VBox[][] getTileSet() {
        return tileSet;
    }

    public void setTileSet(VBox[][] tileSet) {
        this.tileSet = tileSet;
    }

    public abstract ITile createTile();
}

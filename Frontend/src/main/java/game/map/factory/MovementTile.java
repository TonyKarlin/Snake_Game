package game.map.factory;

import game.map.TileType;
import game.models.settings.TileSize;
import javafx.scene.layout.VBox;


public class MovementTile implements ITile{
    private final TileType type = TileType.MOVEMENT;
    
    private void setTileSize(int size, VBox tile) {
        tile.setPrefWidth(size);
        tile.setPrefHeight(size);
    }

    @Override
    public TileType getTileType() {
        return type;
    }

    @Override
    public VBox getTile() {
        VBox tile = new VBox();
        tile.getStyleClass().add("movementTile");
        setTileSize(TileSize.FOURTY.getValue(), tile);
        return tile;
    }
}

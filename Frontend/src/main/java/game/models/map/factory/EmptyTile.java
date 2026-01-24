package game.models.map.factory;

import game.models.map.TileType;
import game.models.settings.TileSize;
import javafx.scene.layout.VBox;


public class EmptyTile implements ITile{
    private final TileType type = TileType.EMPTY;
    
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
        tile.getStyleClass().add("empty-tile");
        setTileSize(TileSize.FOURTY.getValue(), tile);
        return tile;
    }
}

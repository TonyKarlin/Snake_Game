package game.models.map.factory;

import game.models.map.TileType;
import javafx.scene.layout.VBox;


public interface ITile {
//    default void setTileSize(int size, VBox tile) {
//        tile.setPrefWidth(size);
//        tile.setPrefHeight(size);
//    }
    
    TileType getTileType();
    VBox getTile();
}

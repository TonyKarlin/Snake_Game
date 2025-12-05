package game.map.factory;

import game.map.TileType;
import javafx.scene.layout.VBox;


public interface ITile {
    TileType getTileType();
    VBox getTile();
}

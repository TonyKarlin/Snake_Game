package game.map.factory;

import game.map.MapType;
import javafx.scene.layout.VBox;


public interface ITile {
    MapType getTileType();
    VBox getTile();
}

package game.models.map.factory;

import game.models.map.TileType;
import javafx.scene.layout.VBox;


public interface ITile {
    TileType getTileType();
    VBox getTile();
}

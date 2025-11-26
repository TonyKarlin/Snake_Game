package game.map.factory;

import game.map.MapType;
import javafx.scene.layout.VBox;


public class MovementTile implements ITile{
    private final MapType type = MapType.MOVEMENT;

    @Override
    public MapType getTileType() {
        return type;
    }

    @Override
    public VBox getTile() {
        return new VBox();
    }
}

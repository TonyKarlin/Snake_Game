package game.map.factory;

import game.map.TileType;
import javafx.scene.layout.VBox;

public class ObstacleTile implements ITile{
    private final TileType type = TileType.OBSTACLE;

    @Override
    public TileType getTileType() {
        return type;
    }

    @Override
    public VBox getTile() {
        return new VBox();
    }
}

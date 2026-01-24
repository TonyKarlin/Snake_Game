package game.models.map.factory;

import game.models.map.TileType;
import javafx.scene.layout.VBox;

public class FoodTile implements ITile{
    private final TileType type = TileType.FOOD;
    @Override
    public TileType getTileType() {
        return type;
    }

    @Override
    public VBox getTile() {
        return new VBox();
    }
}

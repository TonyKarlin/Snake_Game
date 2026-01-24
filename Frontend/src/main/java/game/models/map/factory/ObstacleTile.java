package game.models.map.factory;

import game.models.map.TileType;
import game.models.settings.TileSize;
import javafx.scene.layout.VBox;

public class ObstacleTile implements ITile{
    private final TileType type = TileType.OBSTACLE;

    @Override
    public TileType getTileType() {
        return type;
    }

    @Override
    public VBox getTile() {
        VBox tile = new VBox();
        tile.getStyleClass().add("obstacle-tile");
//        setTileSize(TileSize.FOURTY.getValue(), tile);
        return tile;
    }
}

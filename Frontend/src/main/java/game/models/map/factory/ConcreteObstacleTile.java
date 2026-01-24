package game.models.map.factory;

import game.view.components.GameGrid;

public class ConcreteObstacleTile extends GameGrid {

    @Override
    public ITile createTile() {
        return new ObstacleTile();
    }
    
}

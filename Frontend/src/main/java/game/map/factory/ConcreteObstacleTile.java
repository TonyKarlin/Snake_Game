package game.map.factory;

import game.map.Grid;

public class ConcreteObstacleTile extends Grid{

    @Override
    public ITile createTile() {
        return new ObstacleTile();
    }
    
}

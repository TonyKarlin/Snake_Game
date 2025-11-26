package game.map.factory;

import game.map.Grid;

public class ConcreteMovementTile extends Grid{

    @Override
    public ITile createTile() {
        return new MovementTile();
    }
    
}

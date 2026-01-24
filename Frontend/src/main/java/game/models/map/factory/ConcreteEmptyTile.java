package game.models.map.factory;

import game.view.components.GameGrid;

public class ConcreteEmptyTile extends GameGrid {

    @Override
    public ITile createTile() {
        return new EmptyTile();
    }
    
}

package utils.context;

import game.models.GameEngine;
import game.models.character.Snake;
import game.models.map.Map;

public interface ModelContext {
    Map getMapModel();
    GameEngine getEngineModel();
    Snake getSnakeModel();
}

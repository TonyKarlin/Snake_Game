package utils.context;

import game.models.GameEngine;
import game.models.GameState;
import game.models.character.Snake;
import game.models.map.Map;
import game.view.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import utils.Log;
import game.view.ViewTypes;

public class ConcreteContext extends Application implements AppContext {
    // models
    private final Map mapModel;
    private final GameEngine engine;
    private final GameState gameState;
    private final Snake snake;

    // views
    private final MenuView menu;
    private final GameView game;
    private final GameSettingsView options;
    private final HiscoresView hiscores;
    private final EndScreenView end;

    // primaryStage for the application to live in
    private Stage primaryStage;

    public ConcreteContext() {
        this.mapModel = new Map();
        this.gameState = new GameState();
        this.snake = new Snake();
        this.engine = new GameEngine(gameState, mapModel, snake);

        this.hiscores = new HiscoresView(this);
        this.menu = new MenuView(this);
        this.game = new GameView(this);
        this.options = new GameSettingsView(this);
        this.end = new EndScreenView(this);
    }
    

    @Override
    public void start(Stage stage) throws Exception {
        this.primaryStage = stage;
        menu.show(primaryStage);
    }

    public Stage getPrimaryStage() {
        return this.primaryStage;
    }

    @Override
    public void updateView(ViewTypes type) {
        try {
            switch (type) {
                case ViewTypes.MENU -> this.menu.show(primaryStage);
                case ViewTypes.GAME -> this.game.show(primaryStage);
                case ViewTypes.OPTIONS -> this.options.show(primaryStage);
                case ViewTypes.HISCORES -> this.hiscores.show(primaryStage);
                case ViewTypes.END -> this.end.show(primaryStage);
                case ViewTypes.NONE -> Platform.exit();
                default -> throw new IllegalArgumentException("Unexpected value: " + type);
            }
        } catch (Exception e) {
            Log.log("Error while starting the " + type + " Screen: " + e);
            e.printStackTrace();
        }
    }

    @Override
    public Map getMapModel() {
        return mapModel;
    }

    @Override
    public GameEngine getEngineModel() {
        return engine;
    }

    @Override
    public GameState getGameStateModel() {
        return gameState;
    }

    @Override
    public Snake getSnakeModel() {
        return snake;
    }
}

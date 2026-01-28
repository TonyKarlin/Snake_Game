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

public class ConcreteContextMediator extends Application implements AppContext {
    // Models
    private final Map mapModel;
    //    private final GameEngine engine;
    private final Snake snake;

    // Views
    private final MenuView menu;
    private final GameView game;
    private final GameSettingsView options;
    private final HiscoresView hiscores;
    private final EndScreenView end;

    private Stage primaryStage;

    public ConcreteContextMediator() {
        this.mapModel = new Map();
//        this.engine = new GameEngine();
        this.snake = new Snake();

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

    public Map getMapModel() {
        return mapModel;
    }

//    public GameEngine getEngineModel() {
//        return engine;
//    }

    public Snake getSnakeModel() {
        return snake;
    }
}

package utils.context;

import game.models.GameEngine;
import game.models.map.Map;
import game.view.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import utils.Log;
import utils.ViewTypes;

public class ConcreteContextMediator extends Application implements AppContext {
    // Models
    private final Map mapModel;
    private final GameEngine engine;

    // Views
    private final MenuView menu;
    private final GameView game;
    private final GameSettingsView options;
    private final HiscoresView hiscores;
    private final EndScreenView end;

    private Stage primaryStage;

    public ConcreteContextMediator() {
        this.mapModel = new Map();
        this.engine = new GameEngine();

        this.hiscores = new HiscoresView(this);
        this.menu = new MenuView(this);
        this.game = new GameView(this);
        this.options = new GameSettingsView(this);
        this.end = new EndScreenView(this);
    }

    @Override
    public void start(Stage stage) throws Exception {
        this.primaryStage = stage;
        menu.start(primaryStage);
    }

    public Stage getPrimaryStage() {
        return this.primaryStage;
    }

    @Override
    public void updateView(ViewTypes type) {
        try {
            switch (type) {
                case ViewTypes.MENU -> this.menu.start(primaryStage);
                case ViewTypes.GAME -> this.game.start(primaryStage);
                case ViewTypes.OPTIONS -> this.options.start(primaryStage);
                case ViewTypes.HISCORES -> this.hiscores.start(primaryStage);
                case ViewTypes.END -> this.end.start(primaryStage);
                case ViewTypes.NONE -> Platform.exit();
                default -> throw new IllegalArgumentException("Unexpected value: " + type);
            }
        } catch (Exception e) {
            Log.log("Error while starting the " + type + " Screen: " + e);
        }
    }

    public Map getMapModel() {
        return mapModel;
    }

    public GameEngine getEngineModel() {
        return engine;
    }
}

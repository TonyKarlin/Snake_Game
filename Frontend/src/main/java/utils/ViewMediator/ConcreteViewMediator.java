package utils.ViewMediator;

import game.view.GameSettingsView;
import game.view.GameView;
import game.view.HiscoresView;
import game.view.MenuView;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import utils.Log;
import utils.ViewTypes;

public class ConcreteViewMediator extends Application implements Mediator {
    private final MenuView menu;
    private final GameView game;
    private final GameSettingsView options;
    private final HiscoresView hiscores;
    // private final EndScreenView end;
    private Stage primaryStage;

    public ConcreteViewMediator() {
        this.hiscores = new HiscoresView(this);
        this.menu = new MenuView(this);
        this.game = new GameView(this);
        this.options = new GameSettingsView(this);
        // this.end = new EndScreenView(this);
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
                // case ViewTypes.END -> this.end.start(primaryStage)
                case ViewTypes.NONE -> Platform.exit();
                default -> throw new IllegalArgumentException("Unexpected value: " + type);
            }
        } catch (Exception e) {
            Log.log("Error while starting the " + type + " Screen: " + e);
        }
    }
}

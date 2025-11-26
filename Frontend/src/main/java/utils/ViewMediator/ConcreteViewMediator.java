package utils.ViewMediator;

import game.view.GameView;
import game.view.MenuView;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import utils.Log;
import utils.ViewTypes;

public class ConcreteViewMediator extends Application implements Mediator{
    private final MenuView menu;
    private final GameView game;
    // private final HiscoresView hiscores;
    private Stage primaryStage;

    public ConcreteViewMediator() {
        this.menu = new MenuView(this);
        this.game = new GameView(this);
        // this.hiscores = new HiscoresView(this);
    }

    @Override
    public void start(Stage stage) throws Exception {
        this.primaryStage = stage;
        menu.start(primaryStage);
    }

    public void showMainMenu() {
        try {
            this.menu.start(primaryStage);
        } catch(Exception e) {
            Log.log("Error while starting the MainMenu: " + e);
        }
    }

    public void showGame() {
        try {
            this.game.start(primaryStage);
        } catch (Exception e) {
            Log.log("Error while starting the Game: " + e);
        }
    }

    // public void showHiscores() throws Exception{
    //     hiscores.start(primaryStage);
    // }

    public Stage getPrimaryStage() {
        return this.primaryStage;
    }

    @Override
    public void updateView(ViewTypes type) {
        switch(type) {
            case ViewTypes.MENU -> {
                    showMainMenu();
            }
            case ViewTypes.GAME -> {
                    showGame();
            }
            // case ViewEnums.HISCORES -> {
            //     try {
            //         showHiscores();
            //     } catch(Exception ex){}
            // }
            case ViewTypes.NONE -> {
                Platform.exit();
            }
            default -> throw new IllegalArgumentException("Unexpected value: " + type);
        }
    }
}

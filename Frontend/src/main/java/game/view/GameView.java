package game.view;

import game.controllers.GameController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import utils.FetchFxml;
import utils.ViewMediator.Mediator;

public class GameView extends Application{
    private final String GAME_VIEW_NAME = "game";
    private final Mediator mediator;

    public GameView(Mediator mediator) {
        this.mediator = mediator;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = FetchFxml.fetchAndValidateLoader(this, GAME_VIEW_NAME);
        Scene scene = new Scene(loader.load(), Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);
        scene.getStylesheets().add("/css/game_window_stylesheet.css");
        GameController controller = loader.getController();
        controller.setMediator(mediator);

        primaryStage.setScene(scene);
        primaryStage.setTitle("Snake - Game");
        primaryStage.show();
    }
 
    public String getViewName() {
        return GAME_VIEW_NAME;
    }
}

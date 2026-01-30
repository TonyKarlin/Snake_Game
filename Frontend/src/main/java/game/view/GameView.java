package game.view;

import game.controllers.GameController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import utils.FetchFxml;
import utils.context.AppContext;

public class GameView {
    private final AppContext context;

    public GameView(AppContext context) {
        this.context = context;
    }

    public void show(Stage stage) throws Exception {
        String GAME_VIEW_NAME = "game";
        FXMLLoader loader = FetchFxml.fetchAndValidateLoader(this, GAME_VIEW_NAME);
        Scene scene = new Scene(loader.load(), Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);
        scene.getStylesheets().add("/css/game_window_stylesheet.css");

        GameController controller = loader.getController();
        controller.setContext(context);
        controller.load();

        scene.setOnKeyPressed(controller::keyPressed);

        stage.setScene(scene);
        stage.setTitle("Snake - Game");
        stage.show();

        scene.getRoot().requestFocus();
    }
}

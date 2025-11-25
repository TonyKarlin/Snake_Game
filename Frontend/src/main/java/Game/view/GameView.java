package game.view;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import utils.FetchFxml;

public class GameView extends Application{
    private final String NAME = "game";

    @Override
    public void start(Stage primaryStage) throws Exception {
        Scene scene = new Scene(FetchFxml.fetchAndLoadView(this, NAME), 600, 400);
        scene.getStylesheets().add("/css/game_window_stylesheet.css");

        primaryStage.setScene(scene);
        primaryStage.setTitle("Snake - Game");
        primaryStage.show();
    }
    
}

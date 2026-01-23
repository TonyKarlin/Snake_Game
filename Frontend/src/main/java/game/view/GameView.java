package game.view;

import game.controllers.GameController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import utils.FetchFxml;
import utils.context.AppContext;

public class GameView extends Application{
    private final String GAME_VIEW_NAME = "game";
    private final AppContext context;

    public GameView(AppContext context) {
        this.context = context;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = FetchFxml.fetchAndValidateLoader(this, GAME_VIEW_NAME);
        Scene scene = new Scene(loader.load(), Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);
        scene.getStylesheets().add("/css/game_window_stylesheet.css");
        GameController controller = loader.getController();
        controller.setMediator(context);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Snake - Game");
        primaryStage.show();
    }
 
    public String getViewName() {
        return GAME_VIEW_NAME;
    }
}

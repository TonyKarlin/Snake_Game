package game.view;

import game.controllers.GameSettingsController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import utils.FetchFxml;
import utils.context.AppContext;

import java.io.IOException;

public class GameSettingsView extends Application {
//    private final ViewMediator viewMediator;
    private final AppContext context;
    private final String GAME_SETTINGS_VIEW_NAME = "game_settings";

    public GameSettingsView(AppContext context) {
        this.context = context;
    }
    
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader loader = FetchFxml.fetchAndValidateLoader(this, GAME_SETTINGS_VIEW_NAME);
        Scene scene = new Scene(loader.load(), 600, 500);
        scene.getStylesheets().add("/css/game_settings_stylesheet.css");
        GameSettingsController controller = loader.getController();
        controller.setMediator(context);
        controller.setContext(context);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Snake - GameOptions");
        primaryStage.show();
        
    }

    public String getGAME_VIEW_NAME() {
        return GAME_SETTINGS_VIEW_NAME;
    }
}

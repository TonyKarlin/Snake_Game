package game.view;

import game.controllers.GameSettingsController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import utils.FetchFxml;
import utils.context.AppContext;

import java.io.IOException;

public class GameSettingsView {
    private final AppContext context;

    public GameSettingsView(AppContext context) {
        this.context = context;
    }
    
    public void show(Stage stage) throws IOException {
        String GAME_SETTINGS_VIEW_NAME = "game_settings";
        FXMLLoader loader = FetchFxml.fetchAndValidateLoader(this, GAME_SETTINGS_VIEW_NAME);
        Scene scene = new Scene(loader.load(), 600, 600);
        scene.getStylesheets().add("/css/game_settings_stylesheet.css");
        GameSettingsController controller = loader.getController();
        controller.setMediator(context);
        controller.setContext(context);
        stage.setScene(scene);
        stage.setTitle("Snake - GameOptions");
        stage.show();
        
    }
}

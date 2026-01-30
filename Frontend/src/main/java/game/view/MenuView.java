package game.view;

import game.controllers.MainMenuController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import utils.FetchFxml;
import utils.context.AppContext;

public class MenuView {
    private final AppContext context;


    public MenuView(AppContext context) {
        this.context = context;
    }
    
    public void show(Stage stage) throws Exception {
        String MENU_VIEW_NAME = "menu";
        FXMLLoader loader = FetchFxml.fetchAndValidateLoader(this, MENU_VIEW_NAME);
        Parent root = loader.load();
        MainMenuController controller = loader.getController();
        controller.setMediator(context);
        controller.initButtons();

        Scene scene = new Scene(root, 600, 600);
        scene.getStylesheets().add("/css/menu_stylesheet.css");


        stage.setScene(scene);
        stage.setTitle("Snake - Main Menu");
        stage.show();

    }
}

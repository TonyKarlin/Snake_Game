package game.view;

import game.controllers.MainMenuController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import utils.FetchFxml;
import utils.context.AppContext;

public class MenuView extends Application{
    private final String MENU_VIEW_NAME = "menu";
    private final AppContext context;


    public MenuView(AppContext context) {
        this.context = context;
    }

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = FetchFxml.fetchAndValidateLoader(this, MENU_VIEW_NAME);
        Parent root = loader.load();
        MainMenuController controller = loader.getController();
        controller.setMediator(context);

        Scene scene = new Scene(root, 600, 600);
        scene.getStylesheets().add("/css/menu_stylesheet.css");


        stage.setScene(scene);
        stage.setTitle("Snake - Main Menu");
        stage.show();

    }

    public String getViewName() {
        return MENU_VIEW_NAME;
    }
}

package game.view;

import game.controllers.HiscoresController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import utils.FetchFxml;
import utils.context.AppContext;

public class HiscoresView extends Application {
    private final String MENU_VIEW_NAME = "hiscores";
    private final AppContext context;
    
    public HiscoresView(AppContext context) {
        this.context = context;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = FetchFxml.fetchAndValidateLoader(this, MENU_VIEW_NAME);
        Scene scene = new Scene(loader.load(), 400, 600);
        HiscoresController controller = loader.getController();
        controller.setMediator(context);

        primaryStage.setScene(scene);
        primaryStage.setTitle("Snake - Hiscores");
        primaryStage.show();
    }

    public String getMENU_VIEW_NAME() {
        return MENU_VIEW_NAME;
    }
}

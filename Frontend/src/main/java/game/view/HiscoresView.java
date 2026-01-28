package game.view;

import game.controllers.HiscoresController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import utils.FetchFxml;
import utils.context.AppContext;

public class HiscoresView {
    private final AppContext context;

    public HiscoresView(AppContext context) {
        this.context = context;
    }

    public void show(Stage stage) throws Exception {
        String MENU_VIEW_NAME = "hiscores";
        FXMLLoader loader = FetchFxml.fetchAndValidateLoader(this, MENU_VIEW_NAME);
        Scene scene = new Scene(loader.load(), 400, 600);
        HiscoresController controller = loader.getController();
        controller.setMediator(context);

        stage.setScene(scene);
        stage.setTitle("Snake - Hiscores");
        stage.show();
    }
}

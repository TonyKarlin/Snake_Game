package game.view;

import game.controllers.EndScreenController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import utils.FetchFxml;
import utils.context.AppContext;

public class EndScreenView extends Application {
    private final AppContext context;
    private final String END_SCREEN_VIEW_NAME = "end_screen";
    
    public EndScreenView(AppContext context) {
        this.context = context;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = FetchFxml.fetchAndValidateLoader(this, "end_screen");
        primaryStage.setScene(new Scene(loader.load(), 600, 400));
        EndScreenController controller = loader.getController();
        controller.setMediator(context);
        
        primaryStage.setTitle("Snake - Game Over");
        primaryStage.show();
    }
}

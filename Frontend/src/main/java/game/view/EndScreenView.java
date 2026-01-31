package game.view;

import game.controllers.EndScreenController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import utils.FetchFxml;
import utils.context.AppContext;

public class EndScreenView {
    private final AppContext context;
    private final String END_SCREEN_VIEW_NAME = "end_screen";
    
    public EndScreenView(AppContext context) {
        this.context = context;
    }
    
    public void show(Stage stage) throws Exception {
        FXMLLoader loader = FetchFxml.fetchAndValidateLoader(this, END_SCREEN_VIEW_NAME);
        stage.setScene(new Scene(loader.load(), 600, 400));
        EndScreenController controller = loader.getController();
        controller.setContext(context);
        controller.addNavigationButtons();
        controller.reset();
        controller.update();
        
        stage.setTitle("Snake - Game Over");
        stage.show();
    }
}

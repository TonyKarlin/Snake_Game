package game.view;

import java.net.URL;

import game.settings.Difficulty;
import game.settings.MapSize;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Menu extends Application{
    private static Difficulty difficulty;
    private static int size;


    public Menu() {
        difficulty = Difficulty.NORMAL;
        size = MapSize.TWENTY_FOUR.getValue();
    }

    @Override
    public void start(Stage stage) throws Exception {
        String menuResourcePath = "/fxml/menu.fxml";
        URL menuViewUri = getClass().getResource(menuResourcePath);

        if (menuViewUri == null) {
            throw new IllegalArgumentException("FXML file not found at: " + menuViewUri);
        }

        FXMLLoader loader = new FXMLLoader(menuViewUri);

        Scene scene = new Scene(loader.load(), 600, 600);

        stage.setScene(scene);
        stage.setTitle("Snake");
        stage.show();

    }

    public static int getSize() {
        return size;
    }

    public static Difficulty getDifficulty() {
        return difficulty;
    }
}

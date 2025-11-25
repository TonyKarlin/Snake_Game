package game.view;

import game.models.settings.Difficulty;
import game.models.settings.MapSize;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import utils.FetchFxml;

public class MenuView extends Application{
    private static Difficulty difficulty;
    private static int size;
    private final String NAME = "menu";


    public MenuView() {
        difficulty = Difficulty.NORMAL;
        size = MapSize.TWENTY_FOUR.getValue();
    }

    @Override
    public void start(Stage stage) throws Exception {
        Scene scene = new Scene(FetchFxml.fetchAndLoadView(this, NAME).load(), 600, 600);
        scene.getStylesheets().add("/css/menu_stylesheet.css");


        stage.setScene(scene);
        stage.setTitle("Snake - Main Menu");
        stage.show();

    }

    public static int getSize() {
        return size;
    }

    public static Difficulty getDifficulty() {
        return difficulty;
    }
}

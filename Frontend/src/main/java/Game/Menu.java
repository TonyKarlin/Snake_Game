package game;

import game.settings.Difficulty;
import game.settings.MapSize;
import javafx.application.Application;
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

    }

    public static int getSize() {
        return size;
    }

    public static Difficulty getDifficulty() {
        return difficulty;
    }
}

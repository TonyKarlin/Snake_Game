import game.view.MenuView;
import utils.Log;

public class Main {
    public static void main(String[] args) {
        Log.log("\nApplication launched!\n");
        MenuView.launch(MenuView.class, args);
    }
}

import game.view.Menu;
import utils.Log;

public class Main {
    public static void main(String[] args) {
        Log.log("\nApplication launched!\n");
        Menu.launch(Menu.class, args);
    }
}

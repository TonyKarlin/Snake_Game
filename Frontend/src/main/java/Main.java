import utils.Log;
import utils.context.ConcreteContext;

public class Main {
    public static void main(String[] args) {
        Log.log("\nApplication launched!\n");
        ConcreteContext.launch(ConcreteContext.class, args);
    }
}

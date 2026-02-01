import utils.Log;
import utils.context.ConcreteContextContext;

public class Main {
    public static void main(String[] args) {
        Log.log("\nApplication launched!\n");
        ConcreteContextContext.launch(ConcreteContextContext.class, args);
    }
}

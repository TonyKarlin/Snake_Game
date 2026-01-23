import utils.Log;
import utils.context.ConcreteContextMediator;

public class Main {
    public static void main(String[] args) {
        Log.log("\nApplication launched!\n");
        ConcreteContextMediator.launch(ConcreteContextMediator.class, args);
    }
}

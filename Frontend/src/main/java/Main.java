import utils.Log;
import utils.ViewMediator.ConcreteViewMediator;

public class Main {
    public static void main(String[] args) {
        Log.log("\nApplication launched!\n");
        ConcreteViewMediator.launch(ConcreteViewMediator.class, args);
    }
}

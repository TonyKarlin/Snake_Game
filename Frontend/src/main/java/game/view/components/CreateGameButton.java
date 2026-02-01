package game.view.components;

import utils.context.ViewContext;
import game.view.ViewTypes;

public class CreateGameButton extends CustomButton{
    private final ViewContext viewContext;

    public CreateGameButton(ViewContext viewContext) {
        System.out.println("Button mediator state: " + viewContext);
        this.viewContext = viewContext;
        initializeButton("Create a Game");
    }


    @Override
    public void onClick() {
        System.out.println(getClass().getSimpleName() + " clicked!");
        viewContext.updateView(ViewTypes.OPTIONS);
    }
}

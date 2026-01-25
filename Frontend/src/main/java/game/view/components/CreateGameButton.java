package game.view.components;

import utils.context.ViewMediator;
import game.view.ViewTypes;

public class CreateGameButton extends CustomButton{
    private final ViewMediator viewMediator;

    public CreateGameButton(ViewMediator viewMediator) {
        System.out.println("Button mediator state: " + viewMediator);
        this.viewMediator = viewMediator;
        initializeButton("Create a Game");
    }


    @Override
    public void onClick() {
        System.out.println(getClass().getSimpleName() + " clicked!");
        viewMediator.updateView(ViewTypes.OPTIONS);
    }
}

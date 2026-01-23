package game.view.components;

import utils.ViewMediator.Mediator;
import utils.ViewTypes;

public class CreateGameButton extends CustomButton{
    private final Mediator mediator;

    public CreateGameButton(Mediator mediator) {
        System.out.println("Button mediator state: " + mediator);
        this.mediator = mediator;
        initializeButton("Create a Game");
    }


    @Override
    public void onClick() {
        System.out.println(getClass().getSimpleName() + " clicked!");
        mediator.updateView(ViewTypes.OPTIONS);
    }
}

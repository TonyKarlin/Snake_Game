package game.view.components;

import utils.ViewMediator.Mediator;
import utils.ViewTypes;

public class StartGameButton extends CustomButton{
    private final Mediator mediator;

    public StartGameButton(Mediator mediator) {
        System.out.println("Button mediator state: " + mediator);
        this.mediator = mediator;
        initializeButton("Start Game");
    }

    @Override
    public void onClick() {
        System.out.println(getClass().getSimpleName() + " clicked!");
        System.out.println("Mediator state: " + mediator);
        mediator.updateView(ViewTypes.GAME);
    }
    
}

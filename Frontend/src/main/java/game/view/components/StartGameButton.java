package game.view.components;

import utils.context.ViewMediator;
import game.view.ViewTypes;

public class StartGameButton extends CustomButton{
    private final ViewMediator viewMediator;

    public StartGameButton(ViewMediator viewMediator) {
        this.viewMediator = viewMediator;
        initializeButton("Start Game");
        this.setButtonPadding(10);
        this.setButtonLabelColour("GREEN");
    }

    @Override
    public void onClick() {
        System.out.println(getClass().getSimpleName() + " clicked!");
        System.out.println("Mediator state: " + viewMediator);
        viewMediator.updateView(ViewTypes.GAME);
    }
    
}

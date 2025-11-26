package game.view.components;

import utils.ViewMediator.Mediator;
import utils.ViewTypes;

public class ExitButton extends CustomButton {
    private final Mediator mediator;

    public ExitButton(Mediator mediator) {
        this.mediator = mediator;
        initializeButton("Exit");
    }

    @Override
    public void onClick() {
        System.out.println(getClass().getSimpleName() + " clicked!");
        mediator.updateView(ViewTypes.NONE);
    }
}

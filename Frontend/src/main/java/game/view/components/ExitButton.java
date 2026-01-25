package game.view.components;

import utils.context.ViewMediator;
import game.view.ViewTypes;

public class ExitButton extends CustomButton {
    private final ViewMediator viewMediator;

    public ExitButton(ViewMediator viewMediator) {
        this.viewMediator = viewMediator;
        initializeButton("Exit");
    }

    @Override
    public void onClick() {
        System.out.println(getClass().getSimpleName() + " clicked!");
        viewMediator.updateView(ViewTypes.NONE);
    }
}

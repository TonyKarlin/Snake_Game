package game.view.components;

import utils.ViewMediator.Mediator;
import utils.ViewTypes;

public class HiscoresButton extends CustomButton{
    private final Mediator mediator;

    public HiscoresButton(Mediator mediator) {
        this.mediator = mediator;
        initializeButton("Hiscores");
    }

    @Override
    public void onClick() {
        System.out.println(getClass().getSimpleName() + " clicked!");
        mediator.updateView(ViewTypes.HISCORES);
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}

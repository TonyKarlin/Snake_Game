package game.view.components;

import utils.context.ViewMediator;
import utils.ViewTypes;

public class HiscoresButton extends CustomButton{
    private final ViewMediator viewMediator;

    public HiscoresButton(ViewMediator viewMediator) {
        this.viewMediator = viewMediator;
        initializeButton("Hiscores");
    }

    @Override
    public void onClick() {
        System.out.println(getClass().getSimpleName() + " clicked!");
        viewMediator.updateView(ViewTypes.HISCORES);
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}

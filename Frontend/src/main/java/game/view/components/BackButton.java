package game.view.components;

import utils.context.ViewMediator;
import game.view.ViewTypes;

public class BackButton extends CustomButton{
    private final ViewMediator viewMediator;
    
    public BackButton(ViewMediator viewMediator) {
        this.viewMediator = viewMediator;
        initializeButton("Back");
        this.setButtonPadding(10);
        this.setButtonLabelColour("RED");
    }
    
    @Override
    public void onClick() {
        System.out.println(getClass().getSimpleName() + " clicked!");
        viewMediator.updateView(ViewTypes.MENU);
    }
}

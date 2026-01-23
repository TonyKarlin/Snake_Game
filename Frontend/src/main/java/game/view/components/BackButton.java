package game.view.components;

import javafx.scene.layout.HBox;
import utils.ViewMediator.Mediator;
import utils.ViewTypes;

public class BackButton extends CustomButton{
    private final Mediator mediator;
    
    public BackButton(Mediator mediator) {
        this.mediator = mediator;
        initializeButton("Back");
        this.setButtonPadding(10);
        this.setButtonLabelColour("RED");
    }
    
    @Override
    public void onClick() {
        System.out.println(getClass().getSimpleName() + " clicked!");
        mediator.updateView(ViewTypes.MENU);
    }
}

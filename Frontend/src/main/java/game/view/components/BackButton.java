package game.view.components;

import utils.context.ViewContext;
import game.view.ViewTypes;

public class BackButton extends CustomButton{
    private final ViewContext viewContext;
    
    public BackButton(ViewContext viewContext) {
        this.viewContext = viewContext;
        initializeButton("Back");
        this.setButtonPadding(10);
        this.setButtonLabelColour("RED");
    }
    
    @Override
    public void onClick() {
        System.out.println(getClass().getSimpleName() + " clicked!");
        viewContext.updateView(ViewTypes.MENU);
    }
}

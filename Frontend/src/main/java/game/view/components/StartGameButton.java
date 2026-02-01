package game.view.components;

import utils.context.ViewContext;
import game.view.ViewTypes;

public class StartGameButton extends CustomButton{
    private final ViewContext viewContext;

    public StartGameButton(ViewContext viewContext) {
        this.viewContext = viewContext;
        initializeButton("Start Game");
        this.setButtonPadding(10);
        this.setButtonLabelColour("GREEN");
    }

    @Override
    public void onClick() {
        System.out.println(getClass().getSimpleName() + " clicked!");
        System.out.println("Mediator state: " + viewContext);
        viewContext.updateView(ViewTypes.GAME);
    }
    
}

package game.view.components;

import utils.context.ViewContext;
import game.view.ViewTypes;

public class HiscoresButton extends CustomButton{
    private final ViewContext viewContext;

    public HiscoresButton(ViewContext viewContext) {
        this.viewContext = viewContext;
        initializeButton("Hiscores");
    }

    @Override
    public void onClick() {
        System.out.println(getClass().getSimpleName() + " clicked!");
        viewContext.updateView(ViewTypes.HISCORES);
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}

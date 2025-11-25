package game.view.components;

import utils.ViewContext;

public class HiscoresButton extends CustomButton{
    private final ViewContext context;

    public HiscoresButton(ViewContext context) {
        this.context = context;
        initializeButton("Hiscores");
    }

    @Override
    public void onClick() {
        System.out.println(getClass().getSimpleName() + " clicked!");
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}

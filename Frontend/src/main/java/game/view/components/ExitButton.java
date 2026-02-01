package game.view.components;

import utils.context.ViewContext;
import game.view.ViewTypes;

public class ExitButton extends CustomButton {
    private final ViewContext viewContext;

    public ExitButton(ViewContext viewContext) {
        this.viewContext = viewContext;
        initializeButton("Exit");
    }

    @Override
    public void onClick() {
        System.out.println(getClass().getSimpleName() + " clicked!");
        viewContext.updateView(ViewTypes.NONE);
    }
}

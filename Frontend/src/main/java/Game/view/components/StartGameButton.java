package game.view.components;

import utils.ViewContext;

public class StartGameButton extends CustomButton{
    private final ViewContext context;

    public StartGameButton(ViewContext context) {
        this.context = context;
        initializeButton("Start Game");
    }

    @Override
    public void onClick() {
        System.out.println(getClass().getSimpleName() + " clicked!");
        
    }
    
}

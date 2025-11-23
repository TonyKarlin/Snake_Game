package game.view.components;

public class StartGameButton extends CustomButton{
    public StartGameButton() {
        initializeButton("Start Game");
    }

    @Override
    public void onClick() {
        System.out.println(getClass().getSimpleName() + " clicked!");
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}

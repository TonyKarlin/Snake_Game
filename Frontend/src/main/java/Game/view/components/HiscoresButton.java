package game.view.components;

public class HiscoresButton extends CustomButton{

    public HiscoresButton() {
        initializeButton("Hiscores");
    }

    @Override
    public void onClick() {
        System.out.println(getClass().getSimpleName() + " clicked!");
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}

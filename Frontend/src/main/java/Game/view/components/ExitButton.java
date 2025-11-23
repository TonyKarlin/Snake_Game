package game.view.components;

import javafx.application.Platform;

public class ExitButton extends CustomButton {
    public ExitButton() {
        initializeButton("Exit");
    }

    @Override
    public void onClick() {
        System.out.println(getClass().getSimpleName() + " clicked!");
        Platform.exit();
    }
}

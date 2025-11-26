package game.controllers;

import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;
import utils.ViewMediator.Mediator;

public class GameController {
    private Mediator mediator; // TODO: on "Game Over" set view to some endscreen, and from there back to Main Menu


    @FXML
    private GridPane tileContainer;

    

    public void setMediator(Mediator mediator) {
        this.mediator = mediator;
    }
}

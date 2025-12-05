package game.controllers;

import game.map.factory.ConcreteMovementTile;
import game.models.settings.MapSize;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import utils.ViewMediator.Mediator;

public class GameController {
    private Mediator mediator; // TODO: on "Game Over" set view to some endscreen, and from there back to Main Menu
    
    @FXML
    private GridPane tileContainer;

    @FXML
    private FlowPane gameRoot;

    @FXML
    public void initialize() {
        ConcreteMovementTile defaultTiles = new ConcreteMovementTile();
        Platform.runLater(() -> {
            validateGameContainerPosition();
            try {
                defaultTiles.populateGrid(MapSize.TEN.getValue(), tileContainer);
            } catch (Exception e) {
                System.out.println("Grid Error: " + e.getMessage());
            }
        });
    }

    private void validateGameContainerPosition() {
        double MIN_SIZE = 600;
        double MIN_AREA = MIN_SIZE * MIN_SIZE;
        double AREA_MULTIPLIER = 0.9;
        
        double gameRootHeight = gameRoot.getHeight();
        double gameRootWidth = gameRoot.getWidth();
        double gameRootArea = gameRootHeight * gameRootWidth;

        double size;
        if (gameRootArea < MIN_AREA) {
            size = MIN_SIZE * AREA_MULTIPLIER;
        } else if (gameRootHeight != gameRootWidth) {
            size = Math.min(gameRootWidth, gameRootHeight) * AREA_MULTIPLIER;
        } else {
            // Height is used here because the program has ensured that the container is a square (w == h)
            size = gameRootHeight * AREA_MULTIPLIER;
        }
        
        setTileContainerDims(size);
    }

    private void setTileContainerDims(double size) {
        tileContainer.setPrefHeight(size);
        tileContainer.setPrefWidth(size);
    }


    public void setMediator(Mediator mediator) {
        this.mediator = mediator;
    }
}


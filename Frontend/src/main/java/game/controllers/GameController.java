package game.controllers;

import game.models.character.Snake;
import game.models.map.Map;
import game.models.map.factory.ConcreteEmptyTile;
import game.view.components.GameGrid;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import utils.context.AppContext;
import utils.context.ViewMediator;

public class GameController {
    private AppContext context; // TODO: on "Game Over" set view to some EndScreenView, and from there back to Main Menu
    private Map mapModel;
    private Snake snake;
    
    public GameController() {
    }
    
    
    @FXML
    private GridPane tileContainer;

    @FXML
    private FlowPane gameRoot;

    @FXML
    public void initialize() {
        GameGrid grid = new ConcreteEmptyTile();
        Platform.runLater(() -> {
            validateGameContainerPosition();
            try {
                mapModel.initializeMap();
                grid.populateGrid(tileContainer, mapModel.getLogicalMap());
//                int centerX = mapModel.getCenterPosition()[0][0];
//                int centerY = mapModel.getCenterPosition()[0][1];
//                snake.initializeSnake();
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

    public void setContext(AppContext context) {
        this.context = context;
        this.mapModel = context.getMapModel();
        this.snake = context.getSnakeModel();
    }
}


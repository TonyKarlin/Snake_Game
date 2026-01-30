package game.controllers;

import game.models.GameEngine;
import game.models.Position;
import game.models.character.Direction;
import game.models.character.Node;
import game.models.character.Snake;
import game.models.map.Map;
import game.view.GameGridCanvas;
import game.view.components.GameGrid;
import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import utils.context.AppContext;


public class GameController {
    private Map mapModel;
    private Snake snake;
    private GameEngine engine;
    private GraphicsContext gc;
    private GameGrid grid;
    
    @FXML
    private Canvas gameCanvas;

    @FXML
    private StackPane gameRoot;

    @FXML
    public void initialize() {
        gc = gameCanvas.getGraphicsContext2D();

        gameRoot.setMinSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);
        gameRoot.setMaxSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);

        gameRoot.prefWidthProperty().bind(gameCanvas.widthProperty());
        gameRoot.prefHeightProperty().bind(gameCanvas.heightProperty());
    }

    private void redraw() {
        gc.clearRect(0, 0, gameCanvas.getWidth(), gameCanvas.getHeight());
        grid.render(gc, mapModel.getLogicalMap());
        drawSnake();
    }

    public void setContext(AppContext context) {
        this.mapModel = context.getMapModel();
        this.snake = context.getSnakeModel();
        this.engine = context.getEngineModel();
    }

    public void load() {
        grid = new GameGridCanvas(mapModel.getTileSize());
        mapModel.initializeMap();
        grid.render(gc, mapModel.getLogicalMap());
        setClientSize();
        Position center = mapModel.getCenterPosition();
        snake.initializeSnake(center);

        startGameLoop();
    }
    
    private void setClientSize() {
        double size = getClientSize();
        gameCanvas.setWidth(size);
        gameCanvas.setHeight(size);
    }
    
    private double getClientSize() {
        int cells = mapModel.getLogicalMap().length;
        int tileSize = mapModel.getTileSize();
        return cells * tileSize;
    }

    public void startGameLoop() {
        engine.start();
        AnimationTimer gameLoop = new AnimationTimer() {
            @Override
            public void handle(long now) {
                boolean ticked = engine.loop(now);
                if (ticked) {
                    redraw();
                }
            }
        };
        gameLoop.start();
    }

    private void drawSnake() {
        Node current = snake.getHead();
        int tileSize = mapModel.getTileSize();

        while (current != null) {
            int x = current.getPosition().getX();
            int y = current.getPosition().getY();
            double multiplier = validateMultiplier(current);

            current.getNode().draw(gc, x, y, tileSize, multiplier);

            current = current.getNext();
        }
    }

    private double validateMultiplier(Node node) {
        boolean isHead = node.getPrev() == null;
        boolean isTail = node.getNext() == null;

        return isHead ? 1.05 : isTail ? 0.95 : 1.0;
    }

    @FXML
    public void keyPressed(KeyEvent evt) {
        Direction nextDir = switch (evt.getCode()) {
            case UP, W -> Direction.UP;
            case DOWN, S -> Direction.DOWN;
            case LEFT, A -> Direction.LEFT;
            case RIGHT, D -> Direction.RIGHT;
            default -> null;
        };
        
        if (nextDir != null) {
            engine.changeDirection(nextDir);
        }
    }
}


package game.controllers;

import game.models.GameEngine;
import game.models.GameState;
import game.models.Position;
import game.models.character.Direction;
import game.models.character.Node;
import game.models.character.Snake;
import game.models.map.Map;
import game.view.components.GameGridCanvas;
import game.view.ViewTypes;
import game.view.components.GameGrid;
import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import utils.Clock;
import utils.context.AppContext;


public class GameController {
    private AppContext context;
    private Map mapModel;
    private Snake snake;
    private GameEngine engine;
    private GameState state;
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
        drawTimer();
    }

    public void setContext(AppContext context) {
        this.context = context;
        this.mapModel = context.getMapModel();
        this.snake = context.getSnakeModel();
        this.engine = context.getEngineModel();
        this.state = context.getGameStateModel();
    }

    public void load() {
        grid = new GameGridCanvas(mapModel.getTileSize());
        mapModel.initializeMap();
        grid.render(gc, mapModel.getLogicalMap());
        setClientSize();
        Position center = mapModel.getCenterPosition();
        snake.initializeSnake(center);

        state.reset();
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
                if (engine.isRunning()) {
                    if (state.isGameOver()) {
                        stop();
                        context.updateView(ViewTypes.END);
                        return;
                    }
                    boolean hasTickUpdated = engine.loop(now);
                    if (hasTickUpdated) {
                        redraw();
                    }
                }
            }
        };
        gameLoop.start();
    }

    private void drawSnake() {
        Node current = snake.getHead();
        int tileSize = mapModel.getTileSize();

        while (current != null) {
            int x = current.getPosition().x();
            int y = current.getPosition().y();
            double multiplier = validateMultiplier(current);

            current.getNode().draw(gc, x, y, tileSize, multiplier);

            current = current.getNext();
        }
    }

    public void drawTimer() {
        double seconds = Clock.getInstance().getElapsedTimeInMs();
        // place at top center
        gc.fillText(String.format("Time: %.2f", seconds / 1000), (gameCanvas.getWidth() / 2) - 20, 17);
        gc.setFill(Color.BLACK);

    }

    private double validateMultiplier(Node node) {
        boolean isHead = node.getPrev() == null;
        boolean isTail = node.getNext() == null;

        return isHead ? 1.05 : isTail ? 0.95 : 1.0;
    }
    
    @FXML
    public void mapKeys(Scene scene) {
        scene.setOnKeyPressed(evt -> {
            switch (evt.getCode()) {
                case UP, W -> engine.changeDirection(Direction.UP);
                case DOWN, S -> engine.changeDirection(Direction.DOWN);
                case LEFT, A -> engine.changeDirection(Direction.LEFT);
                case RIGHT, D -> engine.changeDirection(Direction.RIGHT);
                case P, ESCAPE -> {
                    if (engine.isRunning()) {
                        engine.pause();
                    } else {
                        engine.resume();
                    }
                }
                default -> {}
            }
        });
    }
}


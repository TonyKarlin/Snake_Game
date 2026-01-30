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
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
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
        
        // AI generated resizing logic
        gameRoot.widthProperty().addListener((obs, ov, nv) -> resizeCanvasToSquareAndRedraw());
        gameRoot.heightProperty().addListener((obs, ov, nv) -> resizeCanvasToSquareAndRedraw());
        Platform.runLater(this::resizeCanvasToSquareAndRedraw);

    }

    private void resizeCanvasToSquareAndRedraw() {
        double size = Math.min(gameRoot.getWidth(), gameRoot.getHeight());
        if (size <= 0) return;
        gameCanvas.setWidth(size);
        gameCanvas.setHeight(size);

        redraw();
    }

    
    // AI generated centering logic
    private void redraw() {
        if (grid == null) return;
        double canvasW = gameCanvas.getWidth();
        double canvasH = gameCanvas.getHeight();

        int cells = mapModel.getLogicalMap().length;
        int cellPx = mapModel.getTileSize();

        double boardW = cells * cellPx;
        double boardH = cells * cellPx;

        double offsetX = Math.floor((canvasW - boardW) / 2.0);
        double offsetY = Math.floor((canvasH - boardH) / 2.0);
        
        // Reset any previous transforms so offsets don't stack up
        gc.setTransform(1, 0, 0, 1, 0, 0);
        gc.clearRect(0, 0, canvasW, canvasH);
        gc.save();
        gc.translate(offsetX, offsetY);
        grid.render(gc, mapModel.getLogicalMap()); // draws from (0,0) -> now centered
        drawSnake();                               // snake uses x*tile,y*tile -> now centered too
        gc.restore();
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
        Position center = mapModel.getCenterPosition();
        snake.initializeSnake(center);

        startGameLoop();
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


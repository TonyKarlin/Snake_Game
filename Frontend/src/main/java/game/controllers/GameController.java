package game.controllers;

import game.models.Position;
import game.models.character.Direction;
import game.models.character.Node;
import game.models.character.Snake;
import game.models.map.Map;
import game.view.GameGridCanvas;
import game.view.components.GameGrid;
import game.view.flyweight.Flyweight;
import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import utils.context.AppContext;


public class GameController {
    private Map mapModel;
    private Snake snake;
    private Flyweight snakeGraphics;
    private GraphicsContext gc;
    private GameGrid grid;
    private Direction currentDirection;
    private AnimationTimer gameLoop;

    public GameController() {
        this.currentDirection = Direction.UP;
    }


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
        if (mapModel == null || grid == null) return;
        double canvasW = gameCanvas.getWidth();
        double canvasH = gameCanvas.getHeight();

        int cells = mapModel.getLogicalMap().length;
        int cellPx = mapModel.getTileSize().getValue();

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

        postContextInit();
    }

    private void postContextInit() {
        grid = new GameGridCanvas(mapModel.getTileSize());
        mapModel.initializeMap();
        grid.render(gc, mapModel.getLogicalMap());
        Position center = mapModel.getCenterPosition();
        snake.initializeSnake(center);

        // Start game loop here
    }

    private void drawSnake() {
        Node current = snake.getHead();
        int tileSize = mapModel.getTileSize().getValue();

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

        return isHead ? 1.1 : isTail ? 0.9 : 1.0;
    }

    @FXML
    public void keyPressed(KeyEvent evt) {
        KeyCode key = evt.getCode();

        switch (key) {
            case UP, W -> {
                if (currentDirection != Direction.DOWN) currentDirection = Direction.UP;
            }
            case DOWN, S -> {
                if (currentDirection != Direction.UP) currentDirection = Direction.DOWN;
            }
            case LEFT, A -> {
                if (currentDirection != Direction.RIGHT) currentDirection = Direction.LEFT;
            }
            case RIGHT, D -> {
                if (currentDirection != Direction.LEFT) currentDirection = Direction.RIGHT;
            }
        }
    }
}


package game.controllers;

import game.models.GameEngine;
import game.models.map.Map;
import game.models.settings.Difficulty;
import game.models.settings.MapSize;
import game.models.settings.TileSize;
import game.view.components.BackButton;
import game.view.components.CustomButton;
import game.view.components.CustomOptionButton;
import game.view.components.StartGameButton;
import javafx.fxml.FXML;
import javafx.scene.layout.HBox;
import utils.context.AppContext;

import java.util.ArrayList;
import java.util.List;

public class GameSettingsController {
    private final List<CustomButton> navigationButtons;
    private final List<CustomOptionButton> difficultyButtons;
    private final List<CustomOptionButton> gridSizeButtons;
    private final List<CustomOptionButton> tileSizeButtons;
    private AppContext context;
    private Map mapModel;
    private GameEngine engine;
    
    public GameSettingsController() {
        this.tileSizeButtons = new ArrayList<>();
        this.navigationButtons = new ArrayList<>();
        this.difficultyButtons = new ArrayList<>();
        this.gridSizeButtons = new ArrayList<>();
    }
    
    @FXML
    private HBox navigationContainer;
    
    @FXML
    private HBox difficultyContainer;
    
    @FXML
    private HBox gridSizeContainer;
    
    @FXML
    private HBox tileSizeContainer;
    
    @FXML
    public void initialize() {
        addDifficulties();
        addGridSizes();
        addTileSizes();
    }
    
    public void initButtons() {
        navigationButtons.add(new BackButton(context));
        navigationButtons.add(new StartGameButton(context));
        addButtonsToLayout();
    }
    
    public void addButtonsToLayout() {
        navigationButtons.forEach(item -> {
            navigationContainer.getChildren().add(item.getButtonComponent());
        });
    }
    
    public void addDifficulties() {
        for (Difficulty diff : Difficulty.values()) {
            CustomOptionButton button = new CustomOptionButton(diff.name(), difficultyButtons) {
                @Override
                public void onToggle() {
                    setDifficulty(diff.getValue());
                    System.out.println("Difficulty set to: " + diff.name());
                }
            };
            difficultyContainer.getChildren().add(button.getButtonComponent());
        }
    }
    
    public void addGridSizes() {
        for (MapSize size : MapSize.values()) {
            CustomOptionButton button = new CustomOptionButton(size.name(), gridSizeButtons) {
                @Override
                public void onToggle() {
                    setSize(size.getValue());
                    System.out.println("Map Size set to: " + size.name());
                }
            };
            gridSizeContainer.getChildren().add(button.getButtonComponent());
        }
    }
    
    public void addTileSizes() {
        for (TileSize size : TileSize.values()) {
            CustomOptionButton button = new CustomOptionButton(size.name(), tileSizeButtons) {
                @Override
                public void onToggle() {
                    setCellSize(size);
                }
            };
            tileSizeContainer.getChildren().add(button.getButtonComponent());
        }
    }
    
    public void setSize(int selectedSize) {
        mapModel.setSize(selectedSize);
    }
    
    public void setDifficulty(int tickSpeed) {
//        engine.setTick(tickSpeed);
    }
    
    public void setCellSize(TileSize size) {
        mapModel.setTileSize(size);
    }

    public void setMediator(AppContext context) {
        this.context = context;
        initButtons();
    }
    
    public void setContext(AppContext context) {
        this.context = context;
        this.mapModel = context.getMapModel();
//        this.engine = context.getEngineModel();
    }
}

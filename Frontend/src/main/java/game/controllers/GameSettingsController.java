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
    private static final Difficulty DEFAULT_DIFFICULTY = Difficulty.MEDIUM;
    private static final MapSize DEFAULT_MAP_SIZE = MapSize.MEDIUM;
    private static final TileSize DEFAULT_TILE_SIZE = TileSize.MEDIUM;

    private final List<CustomButton> navigationButtons;
    private final List<CustomOptionButton<Integer>> difficultyButtons;
    private final List<CustomOptionButton<Integer>> gridSizeButtons;
    private final List<CustomOptionButton<Integer>> tileSizeButtons;

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

    public void initButtons() {
        addNavigationButtons();
        addDifficulties();
        addGridSizes();
        addTileSizes();
    }

    public void addNavigationButtons() {
        navigationButtons.add(new BackButton(context));
        navigationButtons.add(new StartGameButton(context));
        
        navigationButtons.forEach(item ->
            navigationContainer.getChildren().add(item.getButtonComponent()));
    }

    public void addDifficulties() {
        for (Difficulty diff : Difficulty.values()) {
            CustomOptionButton<Integer> button =
                new CustomOptionButton<>(diff.name(), diff.getValue(), difficultyButtons) {

                    @Override
                    public void onToggle(Integer tickRate) {
                        setDifficulty(tickRate);
                    }
                };

            difficultyContainer.getChildren().add(button.getButtonComponent());

            if (diff == DEFAULT_DIFFICULTY) {
                button.select();
            }
        }
    }

    public void addGridSizes() {
        for (MapSize mapSize : MapSize.values()) {
            CustomOptionButton<Integer> button =
                new CustomOptionButton<>(mapSize.name(), mapSize.getValue(), gridSizeButtons) {

                    @Override
                    public void onToggle(Integer size) {
                        setSize(size);
                    }
                };
            gridSizeContainer.getChildren().add(button.getButtonComponent());

            if (mapSize == DEFAULT_MAP_SIZE) {
                button.select();
            }
        }
    }


    public void addTileSizes() {
        for (TileSize tileSize : TileSize.values()) {
            CustomOptionButton<Integer> button
                = new CustomOptionButton<>(tileSize.name(), tileSize.getValue(), tileSizeButtons) {

                @Override
                public void onToggle(Integer size) {
                    setTileSize(size);
                }
            };
            tileSizeContainer.getChildren().add(button.getButtonComponent());

            if (tileSize == DEFAULT_TILE_SIZE) {
                button.select();
            }
        }
    }


    public void setSize(int selectedSize) {
        mapModel.setSize(selectedSize);
    }

    public void setDifficulty(long tickSpeed) {
        engine.setTickRate(tickSpeed);
    }

    public void setTileSize(int size) {
        mapModel.setTileSize(size);
    }

    public void setContext(AppContext context) {
        this.context = context;
        this.mapModel = context.getMapModel();
        this.engine = context.getEngineModel();
    }
}

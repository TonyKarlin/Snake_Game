package game.controllers;

import game.models.settings.Difficulty;
import game.models.settings.MapSize;
import game.view.components.BackButton;
import game.view.components.CustomButton;
import game.view.components.CustomOptionButton;
import game.view.components.StartGameButton;
import javafx.fxml.FXML;
import javafx.scene.layout.HBox;
import utils.ViewMediator.Mediator;

import java.util.ArrayList;
import java.util.List;

public class GameSettingsController {
    private final List<CustomButton> optionsButtons;
    private final List<CustomOptionButton> difficultyButtons;
    private final List<CustomOptionButton> sizeButtons;
    private Mediator mediator;
    
    public GameSettingsController() {
        this.optionsButtons = new ArrayList<>();
        this.difficultyButtons = new ArrayList<>();
        this.sizeButtons = new ArrayList<>();
    }
    
    @FXML
    private HBox navigationContainer;
    
    @FXML
    private HBox difficultyContainer;
    
    @FXML
    private HBox sizeContainer;
    
    @FXML
    public void initialize() {
        addDifficulties();
        addSizes();
    }
    
    public void initButtons() {
        optionsButtons.add(new BackButton(mediator));
        optionsButtons.add(new StartGameButton(mediator));
        addButtonsToLayout();
    }
    
    public void addButtonsToLayout() {
        optionsButtons.forEach(item -> {
            navigationContainer.getChildren().add(item.getButtonComponent());
        });
    }
    
    public void addDifficulties() {
        for (Difficulty diff : Difficulty.values()) {
            CustomOptionButton button = new CustomOptionButton(diff.name(), difficultyButtons) {
                @Override
                public void onToggle() {
                    
                }
            };
            difficultyContainer.getChildren().add(button.getButtonComponent());
        }
    }
    
    public void addSizes() {
        for (MapSize size : MapSize.values()) {
            CustomOptionButton button = new CustomOptionButton(size.name(), sizeButtons) {
                @Override
                public void onToggle() {
                }
            };
            sizeContainer.getChildren().add(button.getButtonComponent());
        }
    }

    public void setMediator(Mediator mediator) {
        this.mediator = mediator;
        initButtons();
    }
}

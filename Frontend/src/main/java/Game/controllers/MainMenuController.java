package game.controllers;

import java.util.ArrayList;
import java.util.List;

import game.view.components.CustomButton;
import game.view.components.ExitButton;
import game.view.components.HiscoresButton;
import game.view.components.StartGameButton;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.VBox;

public class MainMenuController {
    private final List<CustomButton> menuButtons;

    public MainMenuController() {
        this.menuButtons = new ArrayList<>();
        menuButtons.add(new StartGameButton());
        menuButtons.add(new HiscoresButton());
        menuButtons.add(new ExitButton());
    }

    @FXML
    private Button settingsButton;

    @FXML
    private ComboBox langSelector;

    @FXML
    private VBox mainMenuButtons;

    @FXML
    private void initialize() {
        addButtonsToLayout();
        Tooltip settingsHoverTooltip = new Tooltip("Settings");
        Tooltip languageTooltip = new Tooltip("Language");
        settingsButton.setTooltip(settingsHoverTooltip);
        langSelector.setTooltip(languageTooltip);
    }

    public void addButtonsToLayout() {
        menuButtons.forEach(item -> {
            mainMenuButtons.getChildren().add(item.getButtonComponent());
        });
    }
}

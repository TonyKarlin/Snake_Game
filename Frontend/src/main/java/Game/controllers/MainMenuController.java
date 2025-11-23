package game.controllers;

import game.view.components.ExitButton;
import game.view.components.HiscoresButton;
import game.view.components.StartGameButton;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.VBox;

public class MainMenuController {
    private final StartGameButton start;
    private final HiscoresButton hiscores;
    private final ExitButton exit;

    public MainMenuController() {
        this.start = new StartGameButton();
        this.hiscores = new HiscoresButton();
        this.exit = new ExitButton();
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
        mainMenuButtons.getChildren().add(start.getButtonComponent());
        mainMenuButtons.getChildren().add(hiscores.getButtonComponent());
        mainMenuButtons.getChildren().add(exit.getButtonComponent());
    }
}

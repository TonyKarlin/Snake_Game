package game.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Tooltip;

public class MenuController {


    @FXML
    private Button settingsButton, closeButton, startGameButton, historyButton, exitGameButton;

    @FXML
    private ComboBox langSelector;

    @FXML
    private void initialize() {
        Tooltip settingsHoverTooltip = new Tooltip("Settings");
        Tooltip languageTooltip = new Tooltip("Language");
        settingsButton.setTooltip(settingsHoverTooltip);
        langSelector.setTooltip(languageTooltip);
    }

    public void closeGame() {
        closeButton.setOnAction((e) -> {

        });
    }
}

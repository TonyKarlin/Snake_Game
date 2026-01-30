package game.controllers;

import java.util.ArrayList;
import java.util.List;

import game.view.components.*;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.VBox;
import utils.context.ViewMediator;

public class MainMenuController {
    private final List<CustomButton> menuButtons;
    private ViewMediator viewMediator;

    public MainMenuController() {
        this.menuButtons = new ArrayList<>();
    }


    @FXML
    private Button settingsButton;

    @FXML
    private ComboBox langSelector;

    @FXML
    private VBox mainMenuButtons;

    @FXML
    private void initialize() {
        Tooltip settingsHoverTooltip = new Tooltip("Settings");
        Tooltip languageTooltip = new Tooltip("Language");
        settingsButton.setTooltip(settingsHoverTooltip);
        langSelector.setTooltip(languageTooltip);
    }

    public void initButtons() {
        menuButtons.add(new CreateGameButton(viewMediator));
        menuButtons.add(new HiscoresButton(viewMediator));
        menuButtons.add(new ExitButton(viewMediator));
        addButtonsToLayout();
    }

    public void addButtonsToLayout() {
        menuButtons.forEach(item -> {
            mainMenuButtons.getChildren().add(item.getButtonComponent());
        });
    }

    public void setMediator(ViewMediator viewMediator) {
        this.viewMediator = viewMediator;
    }

}

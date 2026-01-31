package game.controllers;

import game.models.GameState;
import game.view.components.BackButton;
import game.view.components.CustomButton;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import utils.context.AppContext;

import java.util.ArrayList;
import java.util.List;

public class EndScreenController {
    private final List<CustomButton> navigationButtons;
    private AppContext context;
    private GameState state;
    
    public EndScreenController() {
        navigationButtons = new ArrayList<>();
    }
    
    @FXML
    private Label scoreLabel;
    
    @FXML
    private Label foodLabel;

    @FXML
    private Label survivedLabel;

    @FXML
    private Label timePerFoodLabel;
    
    @FXML
    private HBox endNavigationContainer;
    
    public void update() {
        scoreLabel.setText(String.valueOf(state.getScore()));
        foodLabel.setText(String.valueOf(state.getFoodEaten()));
        survivedLabel.setText(String.format("%.2f seconds", state.getSurvivalTime()));
        
        timePerFoodLabel.setText(String.format("%.2f seconds", state.getTimeBetweenFoodEaten()));
        
    }
    
    public void reset() {
        scoreLabel.setText(String.valueOf(0));
        foodLabel.setText(String.valueOf(0));
        survivedLabel.setText(String.format("%.2f seconds", 0.0));
        timePerFoodLabel.setText(String.format("%.2f seconds", 0.0));
    }

    public void addNavigationButtons() {
        navigationButtons.add(new BackButton(context));

        navigationButtons.forEach(item ->
            endNavigationContainer.getChildren().add(item.getButtonComponent()));
    }
    
    public void setContext(AppContext context) {
        this.context = context;
        this.state = context.getGameStateModel();
    }
}

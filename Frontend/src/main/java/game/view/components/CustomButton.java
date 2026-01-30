package game.view.components;


import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;

public abstract class CustomButton {
    private HBox buttonComponent;
    private final int defaultButtonPadding = 5;
    private final Pos labelPosition = Pos.CENTER;
    private String name;

    public void initializeButton(String name) {
        this.name = name;
        initContainer();
        buttonStyling();
        this.buttonComponent.setOnMouseClicked(e -> {
            this.onClick();
        });
    }

    private void initContainer() {
        this.buttonComponent = new HBox();
        this.buttonComponent.getChildren().add(setUpButtonLabel(name));
    }

    private void buttonStyling() {
        this.buttonComponent.getStyleClass().add("MenuButtons");
        this.buttonComponent.setPadding(new Insets(getDefaultButtonPadding()));
        this.buttonComponent.setAlignment(getDefaultPosition());
    }

    private Label setUpButtonLabel(String text) {
        Label buttonName = new Label(text);
        buttonName.setFont(new Font(18));
        return buttonName;
    }

    public HBox getButtonComponent() {
        return buttonComponent;
    }

    public int getDefaultButtonPadding() {
        return defaultButtonPadding;
    }

    public Pos getDefaultPosition() {
        return labelPosition;
    }
    
    public void setButtonPadding(int newPadding) {
        this.buttonComponent.setPadding(new Insets(newPadding));

    }
    
    public void setButtonLabelColour(String color) {
        this.buttonComponent.getChildren().filtered(node -> node instanceof Label).forEach(node -> {
            node.setStyle("-fx-text-fill: " + color + ";");
        });
    }

    public String getName() {
        return name;
    }

    public abstract void onClick();
}

package game.view.components;

import javafx.scene.layout.HBox;

import java.util.ArrayList;
import java.util.List;

public abstract class CustomOptionButton extends  CustomButton{
    private final List<CustomOptionButton> toggleGroup;
    private boolean isSelected = false;
    
    public CustomOptionButton(String name, List<CustomOptionButton> toggleGroup) {
        this.toggleGroup = toggleGroup;
        initializeButton(name);
        toggleGroup.add(this);
    }
    
    
    @Override
    public void onClick() {
        toggleGroup.forEach(btn -> btn.setActive(false));
        setActive(true);
        onToggle();
    }
    
    public void setActive(boolean active) {
        this.isSelected = active;
        HBox button = getButtonComponent();
        if (active) {
            button.getStyleClass().add("OptionButtonActive");
        } else {
            button.getStyleClass().remove("OptionButtonActive");
        }
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public abstract void onToggle();
}

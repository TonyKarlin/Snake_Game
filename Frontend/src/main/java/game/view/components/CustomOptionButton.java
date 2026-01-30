package game.view.components;

import javafx.scene.layout.HBox;
import java.util.List;

public abstract class CustomOptionButton<T> extends  CustomButton{
    // Uses java generics to allow for different types of option values
    // E.g. Difficulty, MapSize, TileSize
    private final T value;
    private final List<CustomOptionButton<T>> toggleGroup;

    public CustomOptionButton(String name, T value, List<CustomOptionButton<T>> toggleGroup) {
        this.value = value;
        this.toggleGroup = toggleGroup;
        initializeButton(name);
        toggleGroup.add(this);
    }
    
    
    @Override
    public void onClick() {
        select();
    }
    
    public void select() {
        toggleGroup.forEach(btn -> btn.setActive(false));
        setActive(true);
        onToggle(value);
    }
    
    public void setActive(boolean active) {
        HBox button = getButtonComponent();
        if (active) {
            if (!button.getStyleClass().contains("OptionButtonActive")) {
                button.getStyleClass().add("OptionButtonActive");
            }
        } else {
            button.getStyleClass().remove("OptionButtonActive");
        }
    }

    public T getValue() {
        return value;
    }

    public abstract void onToggle(T value);
}

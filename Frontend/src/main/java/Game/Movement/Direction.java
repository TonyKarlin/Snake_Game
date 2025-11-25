package game.movement;

import game.models.character.Snake;
import game.view.MenuView;

public class Direction {
    private final Position pos;

    public Direction(Snake snake) {
        this.pos = snake.getPosition();
    }

    public void moveUp() throws InterruptedException {
        Thread.sleep(MenuView.getDifficulty().getValue());
        pos.y++;

    }

    public void moveDown() {
        pos.y--;
    }

    public void moveRight() {
        pos.x++;
    }

    public void moveLeft() {
        pos.x--;
    }
}

package game.movement;

import game.models.character.Snake;

public class Direction {
    private final Position pos;

    public Direction(Snake snake) {
        this.pos = snake.getPosition();
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

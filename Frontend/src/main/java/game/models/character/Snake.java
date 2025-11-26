package game.models.character;

import game.movement.Position;

public class Snake {
    private final Position position = new Position();


    public void move(int x, int y) {
        position.setDx(x);
        position.setDy(y);
    }


    public Position getPosition() {
        return position;
    }
}

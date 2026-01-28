package game.models.character;

import game.models.Position;
import game.view.flyweight.Flyweight;
import game.view.flyweight.SnakeBody;
import game.view.flyweight.SnakeBodyFactory;
import game.view.flyweight.SnakeBodyType;

public class Node {
    // Data for the Snakes Body
    // java's LinkedList is definitely much better, but this is more fun
    // and educational
    private Flyweight node;
    private SnakeBodyType type;
    private Node next = null;
    private Node prev = null;
    
    private final Position position;

    public Node(Flyweight data, Position pos) {
        this.node = data;
        this.position = pos;
    }

    public Position getPosition() {
        return position;
    }
    
    public void setPosition(int x, int y) {
        this.position.setPosition(x, y);
    }

    public Flyweight getNode() {
        return node;
    }

    public Node getNext() {
        return next;
    }

    public Node getPrev() {
        return prev;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    public void setPrev(Node prev) {
        this.prev = prev;
    }

    public SnakeBodyType getType() {
        return type;
    }

    public void setType(SnakeBodyType type) {
        this.type = type;
        this.node = SnakeBodyFactory.getBodyExtension(type);
    }
}

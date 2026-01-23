package game.models.character;

import utils.flyweight.SnakeBody;

public class Node {
    private final SnakeBody node;
    private Node next = null;
    private Node prev = null;

    public Node(SnakeBody data) {
        this.node = data;
    }
    
    public SnakeBody getNode() {
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
}

package game.models.character;

import utils.flyweight.SnakeBody;
import utils.flyweight.SnakeBodyFactory;
import utils.flyweight.SnakeBodyType;

public class Snake {
    private Node head;
    private Node tail;
    private int size;
    
    public Snake() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    public void grow(SnakeBodyType type) {
        SnakeBody body = SnakeBodyFactory.getBodyExtension(type);
        Node newNode = new Node(body);
        if(head == null) {
            head = tail = newNode;
        } else {
            newNode.setNext(getHead());
            head.setPrev(newNode);
            setHead(newNode);
        }
        size++;
    }
    
    public void removeTail() {
        if (tail == null) return;
        if (head == tail) {
            head = null;
            tail = null;
            return;
        }
        Node temp = getTail();
        tail = temp.getPrev();
        tail.setNext(null);
        temp.setPrev(null);
    }

    public Node getHead() {
        return head;
    }

    public void setHead(Node head) {
        this.head = head;
    }
    
    public Node getTail() {
        return tail;
    }

    public void setTail(Node tail) {
        this.tail = tail;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}

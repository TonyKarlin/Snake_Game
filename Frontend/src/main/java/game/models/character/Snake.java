package game.models.character;

import game.models.Position;
import game.view.flyweight.SnakeBody;
import game.view.flyweight.SnakeBodyFactory;
import game.view.flyweight.SnakeBodyType;
import utils.Log;

public class Snake {
    // Snake implemented as a doubly linked list
    // implementation only contains methods to grow and remove tail
    // other methods are not needed for the game logic
    private Node head;
    private Node tail;
    private int size;
    
    public Snake() {
        this.head = null;
        this.tail = null;
    }

    public void initializeSnake(Position centerPosition) {
        Log.log("Initializing Snake...");

        int startLength = 3;
        SnakeBodyType[] types = {
            SnakeBodyType.HEAD,
            SnakeBodyType.BODY,
            SnakeBodyType.TAIL
        };

        int y = centerPosition.getY();
        Node prev = null;

        for (int i = 0; i < startLength; i++) {
            Node current = new Node(
                SnakeBodyFactory.getBodyExtension(types[i]),
                new Position(centerPosition.getX(), y + i)
            );

            if (i == 0) {
                this.head = current;
            } else {
                prev.setNext(current);
                current.setPrev(prev);
            }

            prev = current;
        }

        this.tail = prev;
        this.size = startLength;
    }
    
    public void grow() {
        size++;
    }
    
    public void addToTheHead(SnakeBodyType type) {
        SnakeBody body = SnakeBodyFactory.getBodyExtension(type);

        if (head == null) throw new IllegalStateException("Cannot add to head of an uninitialized snake");
        Position headPos = head.getPosition();
        
        Node newNode = new Node(
            body,
            new Position(headPos.getX(), headPos.getY())
        );

        head.setPrev(newNode);
        newNode.setNext(head);
        head = newNode;
    }

    public void addToTheTail(SnakeBodyType type) {
        SnakeBody body = SnakeBodyFactory.getBodyExtension(type);

        if (tail == null) throw new IllegalStateException("Cannot add to tail of an uninitialized snake");
        Position tailPos = tail.getPosition();
        
        Node newNode = new Node(
            body,
            new Position(tailPos.getX(), tailPos.getY())
        );

        tail.setType(SnakeBodyType.BODY);
        tail.setNext(newNode);
        newNode.setPrev(tail);
        tail = newNode;
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
    public boolean isCollidingWithSelf(Position pos) {
        Node current = head;
        while (current != null) {
            if (current.getPosition().equals(pos)) return true;
            current = current.getNext();
        }
        return false;
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

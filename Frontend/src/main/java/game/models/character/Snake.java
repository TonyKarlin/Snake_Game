package game.models.character;

import game.view.flyweight.SnakeBody;
import game.view.flyweight.SnakeBodyFactory;
import game.view.flyweight.SnakeBodyType;

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
        this.size = 0;
    }
    
    public void initializeSnake(int x, int y) {
        Node headNode = new Node(SnakeBodyFactory.getBodyExtension(SnakeBodyType.HEAD));
        Node bodyNode = new Node(SnakeBodyFactory.getBodyExtension(SnakeBodyType.BODY));
        Node tailNode = new Node(SnakeBodyFactory.getBodyExtension(SnakeBodyType.TAIL));
        
        head.setNext(bodyNode);
        bodyNode.setPrev(head);
        bodyNode.setNext(tailNode);
        tailNode.setPrev(bodyNode);
        
        this.head = headNode;
        this.tail = tailNode;
        this.size = 3;
    }
    
    public void snakeHeadPosition(int centerX, int centerY) {
        
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

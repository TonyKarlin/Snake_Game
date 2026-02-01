package game.models.character;

import game.models.Position;
import game.view.flyweight.SnakeBody;
import game.view.flyweight.SnakeBodyFactory;
import game.view.flyweight.SnakeBodyType;

import java.util.HashSet;
import java.util.Set;

public class Snake {
    // Snake implemented as a doubly linked list
    // implementation only contains methods to grow and remove tail
    // other methods are not needed for the game logic
    private Node head;
    private Node tail;
    private int size;

    private final Set<Position> occupiedPositions;
    
    public Snake() {
        this.occupiedPositions = new HashSet<>();
        this.head = null;
        this.tail = null;
    }

    public void initializeSnake(Position centerPosition) {
        int startLength = 3;
        SnakeBodyType[] types = {
            SnakeBodyType.HEAD,
            SnakeBodyType.BODY,
            SnakeBodyType.TAIL
        };
        
        clearOccupiedPositions();
        buildSnake(startLength, centerPosition, types);
        
        this.size = startLength;
    }
    
    public void grow() {
        size++;
    }
    
    public void shrink() {
        size--;
    }
    
    private void buildSnake(int startLength, Position centerPosition, SnakeBodyType[] types) {
        int y = centerPosition.y();
        Node prev = null;
        
        for (int i = 0; i < startLength; i++) {
            Node current = new Node(
                SnakeBodyFactory.getBodyExtension(types[i]),
                new Position(centerPosition.x(), y + i)
            );

            if (i == 0) {
                this.head = current;
            } else {
                prev.setNext(current);
                current.setPrev(prev);
            }

            occupiedPositions.add(current.getPosition());
            prev = current;
        }
        this.tail = prev;
    }
    
    public void addToTheHead(Position newPos) {
        SnakeBody body = SnakeBodyFactory.getBodyExtension(SnakeBodyType.HEAD);

        if (head == null) throw new IllegalStateException("Cannot add to head of an uninitialized snake");
        
        Node newNode = new Node(body, newPos);
        occupiedPositions.add(newPos);

        head.setType(SnakeBodyType.BODY);
        head.setPrev(newNode);
        newNode.setNext(head);
        head = newNode;
    }

    
    public void removeTail() {
        if (tail == null) throw new IllegalStateException("Cannot remove tail of an uninitialized snake");
        
        Node temp = getTail();
        
        tail = temp.getPrev();
        tail.setType(SnakeBodyType.TAIL);
        
        tail.setNext(null);
        occupiedPositions.remove(temp.getPosition());
        temp.setPrev(null);
    }
    
    public boolean isCollidingWithSelf(Position pos) {
        return occupiedPositions.contains(pos);
    }

    public Set<Position> getOccupiedPositions() {
        return occupiedPositions;
    }
    
    public void clearOccupiedPositions() {
        occupiedPositions.clear();
    }

    public Node getHead() {
        return head;
    }
    
    public Node getTail() {
        return tail;
    }

    public int getSize() {
        return size;
    }
}

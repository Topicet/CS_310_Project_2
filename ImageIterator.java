import java.util.Iterator;
import java.util.NoSuchElementException;

public class ImageIterator<T extends Comparable<T>> implements Iterator<Node<T>>{
    private Node<T> currentNode;
    private Direction direction;


    public ImageIterator(){
        this.direction = Direction.HORIZONTAL;
    }

    public ImageIterator(Direction direction) {
        this.direction = direction;
    }
    /**
        1. declare the constructor(s) that are needed based on the implementation of the iterator() methods in class Image
        2. declare the hasNext() method
        3. declare the next() method
        4. you can optionally declare the remove() method and make it throw an UnsupportedOperationException
    */

    @Override
    public boolean hasNext() {
        // Checks if there are more elements to consume
        if (direction == Direction.HORIZONTAL) {
            return (currentNode != null && currentNode.getRight() != null);
        } else { // VERTICAL
            return (currentNode != null && currentNode.getDown() != null);
        }
    }

    @Override
    public Node<T> next() {
        // Fetches the next node in the direction specified
        if (!hasNext()) {
            throw new NoSuchElementException("No more elements to iterate.");
        }

        Node<T> toReturn = currentNode;

        if (direction == Direction.HORIZONTAL) {
            // Move right for HORIZONTAL traversal
            currentNode = currentNode.getRight();
        } else { // VERTICAL
            // Move down for VERTICAL traversal
            currentNode = currentNode.getDown();
        }

        return toReturn;
    }
}

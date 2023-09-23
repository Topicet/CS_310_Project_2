import java.util.Iterator;
/**
 * This class implements an iterator for a two-dimensional doubly-linked list.
 * The iterator can traverse the list either horizontally or vertically.
 *
 * @param <T> the type of elements held in this collection
 */
public class ImageIterator<T extends Comparable<T>> implements Iterator<Node<T>> {
    /**
     * The node that is currently active in the iterator.
     */
    private Node<T> currentNode;
    /**
     * The direction of iterator traversal.
     */
    private Direction direction;
    /**
     * Keeps track of whether we are at the head node or not.
     */
    private boolean atHeadNode = true;


    /**
     * Creates a new ImageIterator starting at the head node, with default direction as HORIZONTAL.
     *
     * @param head the starting node for the iterator
     */
    public ImageIterator(Node<T> head){
        this.currentNode = head;
        this.direction = Direction.HORIZONTAL;
    }

    /**
     * Creates a new ImageIterator starting at the head node, with a specified direction.
     *
     * @param head      the starting node for the iterator
     * @param direction the direction in which to traverse, either HORIZONTAL or VERTICAL
     */
    public ImageIterator(Node<T> head, Direction direction) {
        this.currentNode = head;
        this.direction = direction;
    }

    /**
     * Checks if there are more elements to iterate through.
     * 
     * @return true if there are more elements, false otherwise
     */
    @Override
    public boolean hasNext() {
        if (currentNode == null) {
            return false;
        }        
        // For the Horizontal direction, check if there's a node to the right or if it can move down to the next row
        if (direction == Direction.HORIZONTAL) {
            return currentNode.getRight() != null || currentNode.getDown() != null;
        } 
        // For the vertical direction, check if there's a node below or if it can move right to the next column
        else { 
            return currentNode.getDown() != null || currentNode.getRight() != null;
        }
    }

    /**
     * Returns the next node in the list based on the current direction of traversal.
     * 
     * @return the next Node object in the traversal
     */
    @Override
    public Node<T> next() {
        // Fetches the next node in the direction specified
        if (!hasNext()) {
            return null;
        }
        
        // If we're at the head node, just return it and update the flag
        if (atHeadNode) {
            atHeadNode = false;
            return currentNode;
        }

        if (direction == Direction.HORIZONTAL) {

            // If there's a node to the right, move right
            if (currentNode.getRight() != null) {
                currentNode = currentNode.getRight();
                return currentNode;
            } 

            // Otherwise, move to the first node of the next row
            else if (currentNode.getDown() != null) {
                currentNode = currentNode.getDown();

                while (currentNode.getLeft() != null) {
                    currentNode = currentNode.getLeft();
                }            
                return currentNode;
            }
            //If there is no node to right and no node below then we are at the end.
            else{
                return null;
            }
        } 
        else { // VERTICAL
            // If there's a node below, move down
            if (currentNode.getDown() != null) {
                currentNode = currentNode.getDown();
                return currentNode;
            } 
            // Otherwise, move to the first node of the next column
            else if (currentNode.getRight() != null) {
                currentNode = currentNode.getRight();

                while (currentNode.getUp() != null) {
                    currentNode = currentNode.getUp();
                }            
                return currentNode;
            }

            //If there is no node to the right and no node below we are done.
            else{
                return null;
            }
        }   
    }
}

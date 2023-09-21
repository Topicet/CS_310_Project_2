import java.util.Iterator;

public class ImageIterator<T extends Comparable<T>> implements Iterator<Node<T>>{
    private Node<T> currentNode;
    private Direction direction;


    public ImageIterator(Node<T> head){
        this.currentNode = head;
        this.direction = Direction.HORIZONTAL;
    }

    public ImageIterator(Node<T> head, Direction direction) {
        this.currentNode = head;
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

    @Override
    public Node<T> next() {
        // Fetches the next node in the direction specified
        if (!hasNext()) {
            return null;
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

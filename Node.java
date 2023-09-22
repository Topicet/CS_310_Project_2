/**
 * The `Node` class represents a single node in a two-dimensional doubly-linked list.
 * It is a generic class that implements the Comparable interface to enable comparison
 * between nodes based on their data values.
 *
 * @param <T> The type of data stored in this node, must implement the Comparable interface.
 * @see Comparable
 */
public final class Node<T extends Comparable<T>> implements Comparable<Node<T>>
{
    /**
     * The `data` variable holds the value stored in this node of the two-dimensional doubly-linked list.
     */
    private T data;

    /**
     * The `up` variable points to the node above this node in the linked list.
     */
    private Node<T> up;

    /**
     * The `down` variable points to the node below this node in the linked list.
     */
    private Node<T> down;

    /**
     * The `right` variable points to the node to the right of this node in the linked list.
     */
    private Node<T> right;

    /**
     * The `left` variable points to the node to the left of this node in the linked list.
     */
    private Node<T> left;
    
    /**
     * Default constructor that sets everything to null.
     */
    public Node(){
        this.data = null;
        this.up = null;
        this.down = null;
        this.left = null;
        this.right = null;
    }

    /**
     * Constructor that sets the data field only and everything else to null.
     * @param value Default value of the node.
     */
    public Node(T value){
        this.data = value;

        this.up = null;
        this.down = null;
        this.left = null;
        this.right = null;
    }

    /**
     * Checks the value of the node above this one and returns it.
     * @return The value of the node above this one.
     */
    public Node<T> getUp(){
        return this.up;
    }

    /**
     * Checks the value of the node below this one and returns it.
     * @return The value of the node below this one.
     */
    public Node<T> getDown(){
        return this.down;
    }

    /**
     * Checks the value of the node to the right of this one and returns it.
     * @return The value of the node to the right of this one.
     */
    public Node<T> getRight(){
        return this.right;
    }

    /**
     * Checks the value of the node to the left of this one and returns it.
     * @return The value of the node to the left of this one.
     */
    public Node<T> getLeft(){
        return this.left;
    }

    /**
     * Checks the value of this node and returns it.
     * @return The value of this node.
     */
    public T getValue(){
        return this.data;
    }

    /**
     * Sets the value of the node above this one.
     * @param p New value the node above will take.
     */
    public void setUp(Node<T> p){
        this.up = p;
    }

    /**
     * Sets the value of the node below this one.
     * @param p New value the node below will take.
     */
    public void setDown(Node<T> p){
        this.down = p;
    }

    /**
     * Sets the value of the node to the right of this one.
     * @param p New value the node to the right of this one will take.
     */
    public void setRight(Node<T> p){
        this.right = p;
    }

    /**
     * Sets the value of the node to the left of this one.
     * @param p New value the node to the left of this one will take.
     */
    public void setLeft(Node<T> p){
        this.left = p;
    }

    /**
     * Sets the value of this node.
     * @param value New value this node will take.
     */
    public void setValue(T value){
        this.data = value;
    }

    /**
     * Compare the value of this node to another.
     * @return 0 if equal / -1 if this node is less than the parameter / 1 if this node is greater than the parameter.
     */
    @Override
    public int compareTo(Node<T> comparisonNode){
        if (this.data == null && comparisonNode.data == null){
            return 0;
        }
        
        if(this.data == null){
            return -1;
        }
        
        if(comparisonNode.data == null){
            return 1;
        }

        return this.data.compareTo(comparisonNode.data);
    }
}
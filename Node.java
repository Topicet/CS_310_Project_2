public final class Node<T extends Comparable<T>> implements Comparable<Node<T>>
{
    private T data;
    private Node<T> up;
    private Node<T> down;
    private Node<T> right;
    private Node<T> left;
    
    /**
     * Constructor that sets all everything to null.
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
     * @param p New value this node will take.
     */
    public void setValue(T value){
        this.data = value;
    }

    /**
     * Compare the value of this node to another
     * @return 0 if equal / -1 if this node is less than the parameter / 1 if this node is greater than the parameter
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
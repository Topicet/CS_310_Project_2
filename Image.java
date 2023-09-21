import java.util.Iterator;

public class Image<T extends Comparable<T>> implements Iterable<Node<T>>{
    private Node<T> head;
    private int height;
    private int width;

    public static void main(String[] args){
        Image<String> myImage = new Image<>(3,3);
        myImage.print2DLinkedList();
        myImage.addBorder();
        myImage.print2DLinkedList();
        myImage.removeBorder();
        myImage.print2DLinkedList();
        System.exit(0);
        testRemoveFirstColumn();
        testRemoveLastColumn();
        testRemoveFirstRow();
        testRemoveLastRow();
    }

    public Image(int width, int height) {
        if (width <= 0 || height <= 0) {
            throw new RuntimeException("Height and Width must be greater than 0.");
        }

        this.height = height;
        this.width = width;

        head = new Node<>();
        Node<T> currentNode = head;

        // Create the first row in the grid.
        for (int r = 1; r < width; r++) {
            Node<T> newNode = new Node<>();
            currentNode.setRight(newNode);
            newNode.setLeft(currentNode);
            currentNode = newNode;
        }

        // Add all additional rows to the grid.
        Node<T> firstNodeinPriorRow = head;
        for (int h = 1; h < height; h++) {
            Node<T> firstNodeinNewRow = new Node<>();
            firstNodeinNewRow.setUp(firstNodeinPriorRow);
            firstNodeinPriorRow.setDown(firstNodeinNewRow);

            currentNode = firstNodeinNewRow;

            Node<T> nodeAboveCurrentNode = firstNodeinPriorRow;

            // Create the next row and attach all nodes to each other.
            for (int r = 1; r < width; r++) {
                Node<T> newNode = new Node<>();
                currentNode.setRight(newNode);
                newNode.setLeft(currentNode);

                nodeAboveCurrentNode = nodeAboveCurrentNode.getRight();
                newNode.setUp(nodeAboveCurrentNode);
                nodeAboveCurrentNode.setDown(newNode);

                currentNode = newNode;
            }

        firstNodeinPriorRow = firstNodeinNewRow;
       }
    }

    public void insertRow(int index, T value){
        if(index < 0 || index > height){
            throw new RuntimeException("Index for insertion is invalid, must be between 0 and " + this.height);
        }

        Node<T> traversalNode = new Node<>();

        // Insertion of a row at the very top of the grid.
        if(index == 0){
            Node<T> nodeBelowFirstRowNode = head;

            head.setUp(traversalNode);
            traversalNode.setDown(nodeBelowFirstRowNode);
            traversalNode.setValue(value);
            head = traversalNode;

            for (int r = 1; r < width; r++) {
                Node<T> newNode = new Node<>();
                newNode.setLeft(traversalNode);
                newNode.setValue(value);

                traversalNode.setRight(newNode);
                traversalNode = newNode;

                nodeBelowFirstRowNode = nodeBelowFirstRowNode.getRight();
                nodeBelowFirstRowNode.setUp(traversalNode);
                traversalNode.setDown(nodeBelowFirstRowNode);
            }
        }

        // Insertion of a row at the very bottom of the grid.
        else if(index == height){
            traversalNode = head;
            for (int i = 1; i < index; i++){
                traversalNode = traversalNode.getDown();
            }

            Node<T> followerNode = new Node<>(value);
            followerNode.setUp(traversalNode);
            traversalNode.setDown(followerNode);

            for (int r = 1; r < width; r++) {
                Node<T> currentNode = new Node<>(value);
                traversalNode = traversalNode.getRight();

                followerNode.setRight(currentNode);
                currentNode.setLeft(followerNode);
                currentNode.setUp(traversalNode);
                traversalNode.setDown(currentNode);

                followerNode = currentNode;
            }
        }
       
        else{
            Node<T> nodeAboveNewRow = head;
            Node<T> nodeBelowNewRow;
        
            // Traverse to the row above where the new row will be inserted
            for (int i = 1; i < index; i++) {
                nodeAboveNewRow = nodeAboveNewRow.getDown();
            }

            nodeBelowNewRow = nodeAboveNewRow.getDown();

            Node<T> newNode = new Node<>();
            newNode.setUp(nodeAboveNewRow);
            newNode.setDown(nodeBelowNewRow);
            nodeAboveNewRow.setDown(newNode);
            nodeBelowNewRow.setUp(newNode);
            
            newNode.setValue(value);

            traversalNode = newNode;

            // Traverse to fill in the rest of the row
            for (int r = 1; r < width; r++) {
                nodeAboveNewRow = nodeAboveNewRow.getRight();
                nodeBelowNewRow = nodeBelowNewRow.getRight();

                Node<T> nextNode = new Node<>();
                nextNode.setUp(nodeAboveNewRow);
                nextNode.setDown(nodeBelowNewRow);
                nodeAboveNewRow.setDown(nextNode);
                nodeBelowNewRow.setUp(nextNode);
                
                nextNode.setValue(value);

                traversalNode.setRight(nextNode);
                nextNode.setLeft(traversalNode);
                
                traversalNode = nextNode;
            }
    
        }

        this.height++;
        print2DLinkedList();

    }

    public void removeColumn(int index) {
        if(index < 0 || index > width){
            throw new RuntimeException("Index for removal is invalid, must be between 0 and " + this.width);
        }
        
        // Special case for removing the first column
        if (index == 0) {
            Node<T> current = head;
            Node<T> nextColumnNode;

            if(head.getRight() != null){
                head = head.getRight();
            }
    
            while (current != null) {
                nextColumnNode = current.getRight();
                if (nextColumnNode != null) {
                    nextColumnNode.setLeft(null);
                }

                current.setRight(null); 
                current = current.getDown();
            }    

        }
        
        // General case for all other columns
        else {
            Node<T> current = head;
    
            // Move to the correct starting column
            for (int i = 0; i < index; i++) {
                current = current.getRight();
            }
    
            Node<T> previousColumnNode, nextColumnNode;
    
            while (current != null) {
                previousColumnNode = current.getLeft();
                nextColumnNode = current.getRight();
    
                if (previousColumnNode != null) {
                    previousColumnNode.setRight(nextColumnNode);
                }
    
                if (nextColumnNode != null) {
                    nextColumnNode.setLeft(previousColumnNode);
                }
                
                current.setLeft(null);
                current.setRight(null);
                current = current.getDown();
            }
        }        
        // Decrementing width after removing a column.
        this.width--;
    }

    private void removeRow(int index) {
        if (index < 0 || index > height) {
            throw new RuntimeException("Index for deletion is invalid, must be between 0 and " + this.height);
        }
    
        // Special case for removing the first row
        if (index == 0) {
            Node<T> currentNode = head;
            Node<T> nextRowNode;

            if(head.getDown() != null){
                head = head.getDown();
            }

            while (currentNode != null) {
                nextRowNode = currentNode.getDown();
                if (nextRowNode != null) {
                    nextRowNode.setUp(null);
                }

                // Nullify the down pointer of the node being removed
                currentNode.setDown(null);
                currentNode = currentNode.getRight();
            }
        }        
    
        // General case for all other rows
        else {
            Node<T> currentNode = head;
    
            // Move to the correct starting row
            for (int i = 0; i < index; i++) {
                currentNode = currentNode.getDown();
            }
    
            Node<T> previousRowNode, nextRowNode;
    
            while (currentNode != null) {
                previousRowNode = currentNode.getUp();
                nextRowNode = currentNode.getDown();
    
                if (previousRowNode != null) {
                    previousRowNode.setDown(nextRowNode);
                }
    
                if (nextRowNode != null) {
                    nextRowNode.setUp(previousRowNode);
                }
    

                // Nullify the up and down pointers of the node being removed
                currentNode.setUp(null);
                currentNode.setDown(null);
                currentNode = currentNode.getRight();
            }
        }
    
        // Decrementing height after removing a row.
        this.height--;
    }
        
    public int getHeight() {
        return this.height;
    }
    
    public int getWidth() {
        return this.width;
    }
    
    public Node<T> getHead() {
        return this.head;
    }

    public int compress() {
        int numberOfRemovedNodes = 0;
        boolean hasBeenCompressed = true;

        while(hasBeenCompressed){
            hasBeenCompressed = false;

            // Systematically check each column to its adjacent one.
            Node<T> columnNode = head;
            int columnIndex = 0;
            while (columnNode != null && columnNode.getRight() != null) {

                if (columnsAreEqual(columnNode, columnNode.getRight())) {
                    removeColumn(columnIndex);
                    numberOfRemovedNodes += height;
                    hasBeenCompressed = true;
                }

                columnNode = columnNode.getRight();
                columnIndex++;
            }

            
            // Check rows
            Node<T> rowStart = head;
            int rowIndex = 0;
            while (rowStart != null && rowStart.getDown() != null) {

                if (rowsAreEqual(rowStart, rowStart.getDown())) {
                    removeRow(rowIndex);
                    numberOfRemovedNodes += width;
                    hasBeenCompressed = true;
                }

            rowStart = rowStart.getDown();
            rowIndex++;
            }
        }

        return numberOfRemovedNodes;
    }


    public void addBorder() {
        
        // Step 1: Add a row at the top
        Node<T> currentNode = head;
        Node<T> newRowcurrentNode = new Node<>();

        newRowcurrentNode.setValue(head.getValue());

        currentNode.setUp(newRowcurrentNode);
        newRowcurrentNode.setDown(currentNode);

        head = newRowcurrentNode;

        while (currentNode.getRight() != null) {
            currentNode = currentNode.getRight();
            
            Node<T> newRowNode = new Node<>();
            newRowNode.setValue(currentNode.getValue());  // Copy value from existing top row.

            newRowNode.setDown(currentNode);
            currentNode.setUp(newRowNode);
            
            newRowcurrentNode.setRight(newRowNode);
            newRowNode.setLeft(newRowcurrentNode);
            
            newRowcurrentNode = newRowNode;
        }


        // Step 2: Add a row at the bottom
        Node<T> bottomNode = head;
        while (bottomNode.getDown() != null) {
            bottomNode = bottomNode.getDown();
        }

        // Initialize for the new bottom row
        Node<T> newBottomNode = new Node<>();
        newBottomNode.setValue(bottomNode.getValue());
        bottomNode.setDown(newBottomNode);
        newBottomNode.setUp(bottomNode);

        // Create and connect the rest of the nodes in the new bottom row
        while (bottomNode.getRight() != null) {
            bottomNode = bottomNode.getRight();

            Node<T> nextNode = new Node<>();

            bottomNode.setDown(nextNode);
            nextNode.setUp(bottomNode);

            nextNode.setValue(bottomNode.getValue());

            newBottomNode.setRight(nextNode);
            nextNode.setLeft(newBottomNode);

            newBottomNode = nextNode;
        }
        

        // Step 3: Add a column on the left
        currentNode = head;  // Start from the new head.
        Node<T> newColumnNode = new Node<>();

        newColumnNode.setValue(currentNode.getValue());
        newColumnNode.setRight(currentNode);
        currentNode.setLeft(newColumnNode);

        head = newColumnNode;

        while(currentNode.getDown() != null){
            currentNode = currentNode.getDown();

            Node<T> nextColumnNode = new Node<>();
            nextColumnNode.setValue(currentNode.getValue());

            nextColumnNode.setRight(currentNode);
            currentNode.setLeft(nextColumnNode);

            newColumnNode.setDown(nextColumnNode);
            nextColumnNode.setUp(newColumnNode);

            newColumnNode = nextColumnNode;
        }

        
        // Step 4: Add a column on the right
        currentNode = head;  // Start from the new head.
        newColumnNode = new Node<>();

        while(currentNode.getRight() != null){
            currentNode=currentNode.getRight();
        }

        newColumnNode.setValue(currentNode.getValue());
        newColumnNode.setLeft(currentNode);
        currentNode.setRight(newColumnNode);

        while(currentNode.getDown() != null){
            currentNode = currentNode.getDown();

            Node<T> nextColumnNode = new Node<>();
            nextColumnNode.setValue(currentNode.getValue());

            nextColumnNode.setLeft(currentNode);
            currentNode.setRight(nextColumnNode);
            newColumnNode.setDown(nextColumnNode);
            nextColumnNode.setUp(newColumnNode);

            newColumnNode = nextColumnNode;
        }
        
        this.height += 2;
        this.width += 2;
    }

    public void removeBorder(){
        if (height < 3 || width < 3) {
            throw new RuntimeException("The image is too small to remove a border.");
        }

        removeRow(0);
        removeRow(this.height);
        removeColumn(0);
        removeColumn(this.width);
    }

    public Image<T> maxFilter(){
        Image<T> filteredImage = new Image<>(this.width, this.height);

        Node<T> currentNode = new Node<>();
        Node<T> filteredNode = new Node<>();

        filteredNode = filteredImage.head;
        currentNode = this.head;

        for (int row = 0; row < this.height; row++) {
            Node<T> rowCurrentNode = currentNode;
            Node<T> rowFilteredNode = filteredNode;

            for (int col = 0; col < this.width; col++) {
                T maxValue = rowCurrentNode.getValue();

                if (rowCurrentNode.getLeft().getValue().compareTo(maxValue) == 1){
                    maxValue = rowCurrentNode.getLeft().getValue();
                }
                if (rowCurrentNode.getRight().getValue().compareTo(maxValue) == 1){
                    maxValue = rowCurrentNode.getRight().getValue();
                }
                if (rowCurrentNode.getUp().getValue().compareTo(maxValue) == 1){
                    maxValue = rowCurrentNode.getUp().getValue();
                }
                if (rowCurrentNode.getDown().getValue().compareTo(maxValue) == 1){
                    maxValue = rowCurrentNode.getDown().getValue();
                }
                if (rowCurrentNode.getUp().getLeft().getValue().compareTo(maxValue) == 1){
                    maxValue = rowCurrentNode.getUp().getLeft().getValue();
                }
                if (rowCurrentNode.getUp().getRight().getValue().compareTo(maxValue) == 1){
                    maxValue = rowCurrentNode.getUp().getRight().getValue();
                }
                if (rowCurrentNode.getDown().getLeft().getValue().compareTo(maxValue) == 1){
                    maxValue = rowCurrentNode.getDown().getLeft().getValue();
                }
                if (rowCurrentNode.getDown().getRight().getValue().compareTo(maxValue) == 1){
                    maxValue = rowCurrentNode.getDown().getRight().getValue();
                }
                
                rowFilteredNode.setValue(maxValue);

                // Move to the next node in the current row
                rowCurrentNode = rowCurrentNode.getRight();
                rowFilteredNode = rowFilteredNode.getRight();
            }

            // Move to the next row
            currentNode = currentNode.getDown();
            filteredNode = filteredNode.getDown();
        }
        return filteredImage;
    }    

    /**
     * Sets all connections coming from this node to null.
     * @param node The node to nullify
     */
    private void setNull(Node<T> node){
        node.setDown(null);
        node.setRight(null);
        node.setLeft(null);
        node.setUp(null);
    }

    private boolean columnsAreEqual(Node<T> firstColumnTopNode, Node<T> secondColumnTopNode){
        boolean equal = true;

        Node<T> leftColumnNode = firstColumnTopNode;
        Node<T> rightColumnNode = secondColumnTopNode;

        
        while (leftColumnNode != null && rightColumnNode != null) {
            T leftColumnNodeValue = leftColumnNode.getValue();
            T rightColumnNodeValue = rightColumnNode.getValue();

            // If at any point the values are not equal, set 'equal' to false and break out of the loop
            if (!leftColumnNodeValue.equals(rightColumnNodeValue)) {
                equal = false;
                break;
            }

            // Continue traversing down the column.
            leftColumnNode = leftColumnNode.getDown();
            rightColumnNode = rightColumnNode.getDown();
        }

        // If one column is longer than the other, they can't be equal
        if (leftColumnNode != null || rightColumnNode != null) {
            equal = false;
        }   

        return equal;
    }

    private boolean rowsAreEqual(Node<T> upperRowFirstNode, Node<T> lowerRowFirstNode) {
        boolean equal = true;
    
        Node<T> UpperRowNode = upperRowFirstNode;
        Node<T> lowerRowNode = lowerRowFirstNode;
    
        while (UpperRowNode != null && lowerRowNode != null) {
            T UpperRowNodeValue = UpperRowNode.getValue();
            T lowerRowNodeValue = lowerRowNode.getValue();
    
            // If at any point the values are not equal, set 'equal' to false and break out of the loop
            if (!UpperRowNodeValue.equals(lowerRowNodeValue)) {
                equal = false;
                break;
            }
    
            // Continue traversing along the row.
            UpperRowNode = UpperRowNode.getRight();
            lowerRowNode = lowerRowNode.getRight();
        }
    
        // If one row is longer than the other, they can't be equal
        if (UpperRowNode != null || lowerRowNode != null) {
            equal = false;
        }
    
        return equal;
    }
    
    private void print2DLinkedList() {
        Node<T> rowHead = head;  // Assuming 'firstRow' is the first node of the first row

        System.out.println("-----------------------------------------------------------------------------");
        while (rowHead != null) {
            Node<T> current = rowHead;
            while (current != null) {
                System.out.print(current.getValue() + "\t");
                current = current.getRight();  // Move to the next column
            }
            System.out.println();  // Newline for the next row
            rowHead = rowHead.getDown();  // Move to the next row
        }

        System.out.println("Number of rows - " + this.height);
        System.out.println("Number of columns - " + this.width);
        System.out.println("Number of nodes - " + this.width*this.height);
    }
        
    /**
     * Creates and returns an ImageIterator object that traverses the image horizontally.
     *
     * @return an ImageIterator object with default direction as HORIZONTAL
     */
    @Override
    public Iterator<Node<T>> iterator() {
        return new ImageIterator<>(head);
    }

    /**
     * Creates and returns an ImageIterator object that can traverse the image either horizontally or vertically.
     *
     * @param dir the direction in which to traverse
     * @return an ImageIterator object with the specified direction
     */
    public Iterator<Node<T>> iterator(Direction dir){
        return new ImageIterator<>(head, dir);
    }

    //TESTER METHODS ---------------------------------------------------------------------------------//

    public static void testRemoveFirstColumn() {
        Image<String> myImage = new Image<>(5, 5);
        // Assume initially the width is 5
        myImage.removeColumn(0);
    
        // Verify: The width should now be 4
        if (myImage.getWidth() != 4) {
            System.out.println("testRemoveFirstColumn FAILED: Width is not 4");
            return;
        }
    
        // Verify: The head should now point to what was the second column
        if (myImage.getHead().getRight() == null) {
            System.out.println("testRemoveFirstColumn FAILED: Head right pointer is null");
            return;
        }
    
        System.out.println("testRemoveFirstColumn PASSED");
    }
    
    public static void testRemoveLastColumn() {
        Image<String> myImage = new Image<>(5, 5);
        // Assume initially the width is 5
        myImage.removeColumn(4);
    
        // Verify: The width should now be 4
        if (myImage.getWidth() != 4) {
            System.out.println("testRemoveLastColumn FAILED: Width is not 4");
            return;
        }
        
        // You would typically also traverse to the last node and verify its 'right' is null
        
        System.out.println("testRemoveLastColumn PASSED");
    }
    
    public static void testRemoveFirstRow() {
        Image<String> myImage = new Image<>(5, 5);
        // Assume initially the height is 5
        myImage.removeRow(0);
    
        // Verify: The height should now be 4
        if (myImage.getHeight() != 4) {
            System.out.println("testRemoveFirstRow FAILED: Height is not 4");
            return;
        }
    
        // Verify: The head should now point to what was the second row
        if (myImage.getHead().getDown() == null) {
            System.out.println("testRemoveFirstRow FAILED: Head down pointer is null");
            return;
        }
    
        System.out.println("testRemoveFirstRow PASSED");
    }
    
    public static void testRemoveLastRow() {
        Image<String> myImage = new Image<>(5, 5);
        // Assume initially the height is 5
        myImage.removeRow(4);
    
        // Verify: The height should now be 4
        if (myImage.getHeight() != 4) {
            System.out.println("testRemoveLastRow FAILED: Height is not 4");
            return;
        }
        
        // You would typically also traverse to the last node in the first column and verify its 'down' is null
    
        System.out.println("testRemoveLastRow PASSED");
    }

}

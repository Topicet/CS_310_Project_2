import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;

/**
    Do NOT submit this file.

    This is just a driver class for debugging your code.
    If any of this code doesn't compile with your other classes,
    it means that you have an error in the way you declare
    or implement the other classes -- do not alter this code to make
    it compile, fix the other classes instead.
*/
public class P2
{
    /**
     * Main method.
     * @param args arguments
     */
    public static void main(String[] args)
    {

        //String filename = "removeColumnTest.pgm";
        String filename = "1x10.pgm";

        checkAddBorder(filename);
        //checkCompressionAllEqual();
        checkAllColumnMethods(filename);

    }

    private static void checkAddBorder(String filename){
        System.out.println("Adding border...");
        Image<Short> image = Utilities.loadImage(filename);
        System.out.println(image);
        image.addBorder();
        System.out.println(image);
    }

    private static void checkBasic(Image<Short> image){
        // print the contents -- only if the optional toString() is implemented
        System.out.println(image);

        // run an enhanced-for loop and print all the pixels in a horizontal traversal
        for(Node<Short> node : image)
            System.out.print(node.getValue());
        
        System.out.println("\n");
        System.out.println("\n");
        // run the same iteration manually (i.e. with a while loop)
        Iterator<Node<Short>> iter = image.iterator();
        while(iter.hasNext())
            System.out.print(iter.next().getValue());

        System.out.println("\n");
        System.out.println("\n");
        // run a vertical iteration
        iter = image.iterator(Direction.VERTICAL);
        while(iter.hasNext())
            System.out.print(iter.next().getValue());

        
        // modify the image and save it to a new file
        image.getHead().setValue((short) 0);
    }


    private static void checkCompression(){
        Image<Short> image = Utilities.loadImage("CompressionTest.pgm");
        System.out.println(image);
        image.compress();
        System.out.println(image);
    }


    private static void checkCompressionAllEqual(){
        Image<Short> image = Utilities.loadImage("CompressionTestAllEqual.pgm");
        System.out.println(image);
        image.compress();
        System.out.println(image);
    }

    
    private static void checkInsertRowAtBottom(){
        System.out.println("Checking row insertion at bottom");
        Image<Short> image = Utilities.loadImage("insertRowTest.pgm");
        System.out.println(image);

        image.insertRow(4, (short) 5);
        System.out.println(image);
    }

    private static void checkInsertRowAtTop(){
        System.out.println("Checking row insertion at Top");
        Image<Short> image = Utilities.loadImage("insertRowTest.pgm");
        System.out.println(image);

        image.insertRow(0, (short) 5);
        System.out.println(image);
    }

    private static void checkInsertRowOOB(){
        System.out.println("Checking row insertion OOB");
        Image<Short> image = Utilities.loadImage("insertRowTest.pgm");
        try{
            image.insertRow(10, (short) 5);
            System.out.println(image);
        }
        catch(Exception e){
            System.out.println("Succesfully caused error");
        }
    }

    private static void checkAllColumnMethods(String filename){
        checkRemoveColumnAtStart(filename);
        checkRemoveColumnAtEnd(filename);
        checkRemoveColumnOOB(filename);
        checkRemoveFinalColumn(filename);
    }

    private static void checkRemoveColumnAtStart(String filename){
        System.out.println("Checking column removal at end");
        Image<Short> image = Utilities.loadImage(filename);
        System.out.println(image);

        image.removeColumn(5);
        System.out.println(image);
    }

    private static void checkRemoveColumnAtEnd(String filename){
        System.out.println("Checking column removal at start");
        Image<Short> image = Utilities.loadImage(filename);
        System.out.println(image);

        image.removeColumn(0);
        System.out.println(image);
    }

    private static void checkRemoveColumnOOB(String filename){
        System.out.println("Checking column removal OOB");
        Image<Short> image = Utilities.loadImage(filename);
        try{
            image.removeColumn(10);
            System.out.println(image);
        }
        catch(Exception e){
            System.out.println("Succesfully caused error");
        }
    }

    private static void checkRemoveFinalColumn(String filename){
        System.out.println("Checking column removal of final column");
        Image<Short> image = Utilities.loadImage(filename);

        try{
            image.removeColumn(0);
            image.removeColumn(0);
            image.removeColumn(0);
            image.removeColumn(0);
            image.removeColumn(0);
            image.removeColumn(0);
        }
        catch(Exception e){
            System.out.println("Succesfully caused error");
        }
    }
    
}

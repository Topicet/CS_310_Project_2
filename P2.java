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
        if (args.length != 2)
        {
            System.err.println("Usage: java P2 <input filename> <output filename>");
            return;
        }

        // create a new image by loading a file
        Image<Short> image = Utilities.loadImage(args[0]);
        
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
        Utilities.saveImage(image, args[1]);
    }
}

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.Scanner;

public final class Utilities
{
    public static Image<Short> loadImage(String pgmFile){
        Image myImage = new Image();

        try{
            Scanner fileScanner = new Scanner(new File(pgmFile));
            myImage = 

        }

        catch(FileNotFoundException e){
            throw new RuntimeException("File " + pgmFile + " not found!");
        }
        /**
            1. open the text file for reading
            2. read the first line and verify the type is P2
            3. read the second line and extract the width and the height of the image
            4. read the third line but don't use it 
            5. create an Image<Short> object
            6. run an enhanced-for loop (or a manual while loop) to get all the nodes from the Image object and use the Node's setter method to set the value for each node
            7. don't forget to close the file
        */



        return myImage;
    }

    public static void saveImage(Image<Short> image, String pgmFile){
        /**
            1. open the text file for writing
            2. write P2 which is the type of the image
            3. write the width and the height of the image separated by a single space
            4. write 255
            5. run an enhanced-for loop (or a manual while loop) to get all the nodes from the Image object and write their pixel value to the file
            6. don't forget to close the file
        */
    }
}

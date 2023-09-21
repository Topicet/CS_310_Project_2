import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.Scanner;

public final class Utilities
{
    public static Image<Short> loadImage(String pgmFile){
        try (Scanner scanner = new Scanner(new File(pgmFile))) {

            // Read the first line to get the PGM format
            String format = scanner.nextLine();

            
            // Read the second line to get the width and height
            int width = scanner.nextInt();
            int height = scanner.nextInt();
            
            // Initialize the new Image object
            Image<Short> myImage = new Image<>(width, height);
            
            // Initialize an iterator for the image
            Iterator<Node<Short>> iterator = myImage.iterator();
            
            // Use the iterator to give each node a value
            while (scanner.hasNext() && iterator.hasNext()) {
                Short pixelValue = scanner.nextShort();
                iterator.next().setValue(pixelValue);
            }
            
            return myImage;
        } catch (FileNotFoundException e) {
            throw new RuntimeException("File " + pgmFile + " not found!");
        }

    }

    public static void saveImage(Image<Short> image, String pgmFile){

        try (PrintWriter writer = new PrintWriter(new File(pgmFile))) {
            // Write the PGM format, P2 in this case.
            writer.println("P2");
            
            // Write the width and height
            writer.println(image.getWidth() + " " + image.getHeight());

            // Write the maximum grayscale value which is 255 in this case
            writer.println("255");
            
            // Initialize an iterator for the image
            Iterator<Node<Short>> iterator = image.iterator();
            
            // Iterate through the image and write each pixel value
            while (iterator.hasNext()) {
                Short pixelValue = iterator.next().getValue();
                writer.println(pixelValue);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException("File " + pgmFile + " not found!");
        }
    }
}

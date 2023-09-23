import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.Scanner;

/**
 * The `Utilities` class provides utility methods for reading and writing images from/to files.
 * This class is designed specifically for handling images with pixel values of type Short and
 * is not generic.
 */
public final class Utilities
{
    /**
     * Reads a PGM image from a file and creates an Image object.
     * 
     * @param pgmFile The file path to the PGM image.
     * @return An Image object representing the image.
     * @throws RuntimeException If the file doesn't exist or an error occurs during loading.
     * @see Image
     */
    public static Image<Short> loadImage(String pgmFile){
        try (Scanner scanner = new Scanner(new File(pgmFile))) {

            // Skip the first line with the PGM format
            scanner.nextLine();
            
            // Read the second line to get the width and height
            int width = scanner.nextInt();
            int height = scanner.nextInt();

            //Skip down to the actual image data.
            scanner.nextLine();
            scanner.nextLine();
            
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
    
    /**
     * Writes an Image object into a PGM file.
     * 
     * @param image    The Image object to be saved.
     * @param pgmFile  The file path where the image will be saved.
     * @throws RuntimeException If the file doesn't exist or an error occurs during saving.
     */
    public static void saveImage(Image<Short> image, String pgmFile){

        try (PrintWriter writer = new PrintWriter(new File(pgmFile))) {
            // Write the PGM format, P2 in this case.
            writer.println("P2\n");
            
            // Write the width and height
            writer.println(image.getWidth() + " " + image.getHeight() + "\n");

            // Write the maximum grayscale value which is 255 in this case
            writer.println("255\n");
            
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

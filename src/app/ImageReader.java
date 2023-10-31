package app;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class ImageReader {
    public static Image ReadImageFromFile(String path) {
    	BufferedImage image = null;
        try {
        	image = ImageIO.read(new File(path));
        } catch (IOException e) {
        	System.out.println("Exception thrown: " + e.getMessage());
        }
        return image;
    }
}

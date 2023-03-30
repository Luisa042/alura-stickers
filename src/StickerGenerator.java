import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class StickerGenerator {

    public void create() throws Exception {
        // read image
        InputStream inputStream = new FileInputStream(new File("input/apple-cat.jpg"));
        BufferedImage sourceImage = ImageIO.read(inputStream);

        // create new image with transparent background and new size
        int width = sourceImage.getWidth();
        int height = sourceImage.getHeight();
        int newHeight = height + (height/4);
        BufferedImage newImage = new BufferedImage(width, newHeight, BufferedImage.TRANSLUCENT);

        // copy source image to new image (in memory)
        Graphics2D graphics = (Graphics2D) newImage.getGraphics();
        graphics.drawImage(sourceImage, 0, 0, null);

        // font config
        Font font = new Font(Font.SANS_SERIF, Font.BOLD, 64);
        graphics.setFont(font);
                
        // write text into new image
        graphics.drawString("^-^", 250, newHeight - 60);
        
        // write new image on a file
        ImageIO.write(newImage, "png", new File("output/apple-cat.png"));
    }

    public static void main(String[] args) throws Exception {
        StickerGenerator generator = new StickerGenerator();
        generator.create();
    }
}

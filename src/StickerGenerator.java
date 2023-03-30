import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
// import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;

import javax.imageio.ImageIO;

public class StickerGenerator {

    public void create() throws Exception {
        // read image
        //InputStream inputStream = new FileInputStream(new File("input/apple-cat.jpg"));
        InputStream inputStream = new URL("https://static.wikia.nocookie.net/evade-nextbot/images/0/0d/Tbh.png").openStream();
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
        Font font = new Font(Font.MONOSPACED, Font.BOLD, 64);
        graphics.setFont(font);
        
        String text = "^-^";
        FontMetrics metrics = graphics.getFontMetrics(font);
        int stringWidth = metrics.stringWidth(text);
        
        // centralizes text on X axis
        var txtPositionX = width / 2 - (stringWidth/2);
        var txtPositionY = newHeight - newHeight / 12; // working on this part

        // write text into new image
        graphics.drawString(text, txtPositionX, txtPositionY);
        
        // write new image on a file
        ImageIO.write(newImage, "png", new File("output/apple-cat.png"));
    }

    public static void main(String[] args) throws Exception {
        StickerGenerator generator = new StickerGenerator();
        generator.create();
    }
}

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class StickerGenerator {

    public void create(InputStream inputStream, String stickerName, String subtitle) throws Exception {
        // read image
        BufferedImage sourceImage = ImageIO.read(inputStream);

        // create new image with transparent background and new size
        int width = sourceImage.getWidth();
        int height = sourceImage.getHeight();
        int newHeight = height + height / 4;
        BufferedImage newImage = new BufferedImage(width, newHeight, BufferedImage.TRANSLUCENT);

        // copy source image to new image
        Graphics2D graphics = (Graphics2D) newImage.getGraphics();
        graphics.drawImage(sourceImage, 0, 0, null);
       
        // font config
        Font font = new Font(Font.MONOSPACED, Font.BOLD, 72);
        graphics.setColor(Color.WHITE);
        graphics.setFont(font);
        
        // pick string measurements
        FontMetrics metrics = graphics.getFontMetrics(font);
        int stringWidth = metrics.stringWidth(subtitle);

        // centralizes text
        int textPositionX = (width - stringWidth) / 2;
        int textPositionY = height + height / 10;
        
        // write text into new image
        graphics.drawString(subtitle, textPositionX, textPositionY);
        
        // write new image on a file
        ImageIO.write(newImage, "png", new File(stickerName));
    }
}

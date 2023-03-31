import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class StickerGenerator {

    public void create(InputStream inputStream, String stickerName) throws Exception {
        // read image
        // InputStream inputStream = new FileInputStream(new File("input/apple-cat.jpg"));
        // InputStream inputStream = new URL("https://static.wikia.nocookie.net/evade-nextbot/images/0/0d/Tbh.png").openStream();
        BufferedImage sourceImage = ImageIO.read(inputStream);

        // create new image with transparent background and new size
        int width = sourceImage.getWidth();
        int height = sourceImage.getHeight();
        int extension = height / 4;
        int newHeight = height + extension;
        BufferedImage newImage = new BufferedImage(width, newHeight, BufferedImage.TRANSLUCENT);

        // copy source image to new image (in memory)
        Graphics2D graphics = (Graphics2D) newImage.getGraphics();
        graphics.drawImage(sourceImage, 0, 0, null);

        // font config
        Font font = new Font(Font.MONOSPACED, Font.BOLD, 64);
        graphics.setFont(font);
        
        String subtitle = "^-^";
        FontMetrics metrics = graphics.getFontMetrics(font);
        int stringWidth = metrics.stringWidth(subtitle);
        
        Rectangle2D textArea = metrics.getStringBounds(subtitle, graphics);
        int stringHeight = (int) textArea.getHeight();

        // centralizes text
        int txtPositionX = (width - stringWidth) / 2;
        int txtPositionY = newHeight - stringHeight;

        // write text into new image
        graphics.drawString(subtitle, txtPositionX, txtPositionY);
        
        // write new image on a file
        ImageIO.write(newImage, "png", new File(stickerName));
    }
}

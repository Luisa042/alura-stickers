import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class StickerGenerator {

    public void create(InputStream inputStream, String stickerName, String subtitle, InputStream inputSuperposingImage) throws Exception {
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

        // add superposing image
        BufferedImage superposingImage = ImageIO.read(inputSuperposingImage);
        int superposingImagePositionX = superposingImage.getWidth();
        int superposingImagePositionY = newHeight - (superposingImage.getHeight() + extension / 2);
        graphics.drawImage(superposingImage, superposingImagePositionX, superposingImagePositionY, null);
       
        // font config
        Font font = new Font(Font.MONOSPACED, Font.BOLD, 72);
        graphics.setColor(Color.white);
        graphics.setFont(font);
        
        // pick string measurements
        FontMetrics metrics = graphics.getFontMetrics(font);
        int stringWidth = metrics.stringWidth(subtitle);
        Rectangle2D textArea = metrics.getStringBounds(subtitle, graphics);
        int stringHeight = (int) textArea.getHeight();

        // centralizes text
        int textPositionX = (width - stringWidth) / 2;
        int textPositionY = newHeight - stringHeight*2;
        
        // font outline (it's buggy)
        // FontRenderContext fontRenderContext = graphics.getFontRenderContext();
        // TextLayout textLayout = new TextLayout(subtitle, font, fontRenderContext);
        // Shape outline = textLayout.getOutline(null);
        // AffineTransform transform = graphics.getTransform();
        // transform.translate(textPositionX, textPositionY);
        // graphics.setTransform(transform);
        // BasicStroke outlineStroke = new BasicStroke(width * 0.004f);
        // graphics.setStroke(outlineStroke);
        // graphics.setColor(Color.BLACK);
        // graphics.draw(outline);
        // graphics.setClip(outline);
        
        // write text into new image
        graphics.drawString(subtitle, textPositionX, textPositionY);
        
        // write new image on a file
        ImageIO.write(newImage, "png", new File(stickerName));
    }
}

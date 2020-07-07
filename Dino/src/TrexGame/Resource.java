package TrexGame;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class Resource {
    public BufferedImage getResourceImage(String path) {
        BufferedImage img = null;
        try {
            img = ImageIO.read(getClass().getResource(path));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return img;
    }
}

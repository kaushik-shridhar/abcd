package TrexGame;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class Background {
    private class BackgroundImage {
        BufferedImage image;
        int x;
    }

    public static int GROUND_Y;

    private BufferedImage image;

    private ArrayList<BackgroundImage> groundImage;

    public Background() {
        GROUND_Y = 290;

        try {
            image = ImageIO.read(new File("data/Ground.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        groundImage = new ArrayList<>();

        for (int i=0;i<3;i++) {
            BackgroundImage obj = new BackgroundImage();
            obj.image = image;
            obj.x = 0;
            groundImage.add(obj);
        }
    }

    public void update() {
        Iterator<BackgroundImage> it = groundImage.iterator();
        BackgroundImage first = it.next();

        first.x -= 10;

        int previous = first.x;

        while (it.hasNext()) {
            BackgroundImage next = it.next();
            next.x = previous + image.getWidth();
            previous = next.x;
        }

        if(first.x < -image.getWidth()) {
            groundImage.remove(first);
            first.x = previous + image.getWidth();
            groundImage.add(first);
        }
    }

    public void create(Graphics g) {
        for(BackgroundImage img : groundImage) {
            g.drawImage(img.image, img.x, GROUND_Y, null);
        }
    }
}
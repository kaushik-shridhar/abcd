package TrexGame;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class Obstacles {
    private class ObstacleImages {
        BufferedImage image;
        int x;
        int y;

        Rectangle getObstacle() {
            Rectangle obstacle = new Rectangle();
            obstacle.x = x;
            obstacle.y = y;
            obstacle.width = image.getWidth();
            obstacle.height = image.getHeight();

            return obstacle;
        }
    }

    private static int speed;
    private static int interval;

    public static int GROUND_X;
    public static int GROUND_Y;
    public static int width1;
    public static int height1;
    public static int width2;
    public static int height2;

    private static int firstX;

    private ArrayList<ObstacleImages> obstacleList;
    private ArrayList<BufferedImage> imageList;

    public Obstacles(int firstPosition) {
        obstacleList = new ArrayList<>();
        imageList = new ArrayList<>();

        firstX = firstPosition;

        speed = 11;
        interval = 500;

        GROUND_X = 400;
        GROUND_Y = 250;

        width1 = 20;
        height1 = 33;

        width2 = 49;
        height2 = 33;

        int x = firstX;

        try {
            imageList.add(ImageIO.read(new File("data/cactus.png")));
            imageList.add(ImageIO.read(new File("data/cactus2.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (BufferedImage bi : imageList) {
            ObstacleImages obj = new ObstacleImages();
            obj.image = bi;
            obj.x = x;
            obj.y = Background.GROUND_Y - bi.getHeight() + 5;
            x += interval;

            obstacleList.add(obj);
        }
    }

    public void update() {
        Iterator<ObstacleImages> it = obstacleList.iterator();

        ObstacleImages first = it.next();
        first.x -= speed;

        while (it.hasNext()) {
            ObstacleImages ob = it.next();
            ob.x -= speed;
        }

        if (first.x < -first.image.getWidth()) {
            obstacleList.remove(first);
            first.x = obstacleList.get(obstacleList.size() - 1).x + interval;
            obstacleList.add(first);
        }
    }

    public void create(Graphics g) {
        for (ObstacleImages ob : obstacleList) {
            g.setColor(Color.BLACK);
            //g.drawRect(ob.getObstacle().x, ob.getObstacle().y, ob.getObstacle().width, ob.getObstacle().height);
            g.drawImage(ob.image, ob.x, ob.y, null);
        }
    }

    public boolean hasCollided() {
        for (ObstacleImages ob : obstacleList) {
            if (Dino.dinoRect().intersects(ob.getObstacle())) {
                return true;
            }
        }
        return false;
    }
}
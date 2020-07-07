package TrexGame;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Dino {
    public static int groundX = 150;
    public static int groundY = 250;

    public static final int STAND_STILL = 1;
    public static final int RUNNING = 2;
    public static final int JUMPING = 3;
    public static final int DIE = 4;

    private final int LEFT_FOOT = 1;
    private final int RIGHT_FOOT = 2;
    private final int NO_FOOT = 3;


    int width = 40;
    int height = 43;

    private static BufferedImage image;
    private static BufferedImage image1;
    private static BufferedImage image2;
    private static BufferedImage image3;

    public static int state;

    private int foot;

    public Dino() {
        try {
            image = ImageIO.read(new File("data/main-character3.png"));
            image1 = ImageIO.read(new File("data/main-character1.png"));
            image2 = ImageIO.read(new File("data/main-character2.png"));
            image3 = ImageIO.read(new File("data/main-character4.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        state = 2;

        foot = NO_FOOT;
    }

    public void create(Graphics g) {
        //g.drawImage(image, groundX, groundY, null);
        //g.drawRect(dinoRect().x, groundY, dinoRect().width, dinoRect().height);

        switch(state) {
            /*case STAND_STILL :
                g.drawImage(image, groundX, groundY, null);
                break;*/

            case RUNNING :
                if (foot == NO_FOOT) {
                    foot = LEFT_FOOT;
                    g.drawImage(image1, groundX, groundY, null);
                } else if (foot == LEFT_FOOT)  {
                    foot = RIGHT_FOOT;
                    g.drawImage(image2, groundX, groundY, null);
                } else {
                    foot = LEFT_FOOT;
                    g.drawImage(image1, groundX, groundY, null);
                }
                break;

            case JUMPING :
                g.drawImage(image, groundX, groundY, null);
                break;

            case DIE :
                g.drawImage(image3, groundX, groundY, null);
                break;
        }
    }

    public static Rectangle dinoRect() {
        Rectangle dino = new Rectangle();

        dino.x = groundX;
        dino.y = groundY;

        dino.width = image.getWidth();
        dino.height = image.getHeight();

        return dino;
    }

    public void die() {
        state = DIE;
    }

    public void startRunning() {
        state = RUNNING;
    }
}
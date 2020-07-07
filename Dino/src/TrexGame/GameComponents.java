package TrexGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GameComponents extends JPanel implements KeyListener, Runnable {
    public static int WIDTH;

    private Thread thread;

    private int score;

    private boolean running = false;
    private boolean gameOver = false;


    Dino dino;
    Background background;
    Obstacles obstacles;
    Rectangle d;

    public GameComponents() {
        WIDTH = Main.WIDTH;

        score = 0;

        dino = new Dino();
        background = new Background();
        obstacles = new Obstacles((int)(WIDTH * 1.5));
        d = new Rectangle();
        thread = new Thread(this);
    }

    public void startGameComponents() {
        thread.start();
    }

    public void paint(Graphics g) {
        super.paint(g);
        g.drawString(Integer.toString(score), getWidth()/2-5, 100);
        dino.create(g);
        background.create(g);
        obstacles.create(g);
    }

    @Override
    public void run() {
        running = true;

        dino.groundY = background.GROUND_Y - 40;
        while (running) {
            updateGame();
            dino.groundY += 7;
            if (dino.groundY >= background.GROUND_Y - 40) {
                dino.groundY = background.GROUND_Y - 40;
            }
            obstacles.GROUND_X -= 4;
            if (obstacles.GROUND_X == 0) {
                obstacles.GROUND_X = 400;
            }
            repaint();
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void updateGame() {
        score++;
        System.out.println(score);

        if (obstacles.hasCollided()) {
            /*for (int i=0;i<10000;i++) {
                System.out.println("Game Over!!");
            }*/
            running = false;
            gameOver = true;
            dino.die();
            resetGame();
        }

        background.update();
        obstacles.update();
    }

    public void resetGame() {
        score = 0;
        gameOver = false;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        if (e.getKeyChar() == 's') {
            //startGameComponents();
            if (gameOver)
                resetGame();
            if (thread == null || !running) {
                thread = new Thread(this);
                thread.start();
                dino.startRunning();
            }
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        //System.out.println("Key Pressed");
        dino.state = 3;
        dino.groundY -= 120;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        //System.out.println("Key Releases");
        dino.state = 2;
    }
}
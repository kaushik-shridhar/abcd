package TrexGame;

import javax.swing.*;

public class Main extends JFrame {
    private GameComponents components;

    public Main() {
        super("Dino");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setSize(400, 400);

        components = new GameComponents();
        add(components);
        addKeyListener(components);
    }

    public static void main(String args[]) {
        Main obj = new Main();
    }
}
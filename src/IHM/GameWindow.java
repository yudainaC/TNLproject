package IHM;

import javax.swing.*;

public class GameWindow {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Mon jeu 2D !");
        GamePanel panel = new GamePanel();

        frame.add(panel);
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setVisible(true);
    }
}

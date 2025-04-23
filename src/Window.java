import IHM.GamePanel;

import java.awt.event.*;
import javax.swing.*;

public class Window {
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

package IHM;

import javax.swing.*;

public class FightWindow extends JFrame {
    public FightWindow() {
        setTitle("Combat !");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        FightPanel panel = new FightPanel();
        add(panel);

        setVisible(true);
    }

    public static void main(String[] args) {
        new FightWindow();
    }
}


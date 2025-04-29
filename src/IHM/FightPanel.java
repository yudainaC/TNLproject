package IHM;

import javax.swing.*;
import java.awt.*;

public class FightPanel extends JPanel {

    public FightPanel() {
        setLayout(new GridLayout(2, 2, 10, 10)); // 2x2 boutons avec marges

        add(makeButton("Attaquer", "Tu attaques !"));
        add(makeButton("Sort", "Tu lances un sort !"));
        add(makeButton("Objet", "Tu ouvres l'inventaire !"));
        add(makeButton("Fuir", "Tu tentes de fuir !"));
    }

    private JButton makeButton(String label, String message) {
        JButton button = new JButton(label);
        button.addActionListener(e -> System.out.println(message));
        return button;
    }
}


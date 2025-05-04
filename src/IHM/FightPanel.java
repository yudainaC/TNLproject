package IHM;

import gameCore.GameFight.FightAction;
import gameCore.GameObjects.GameEntities.Single.Entity;
import gameCore.GameObjects.GameEntities.Single.Monster;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class FightPanel extends JPanel {
    public List<Entity> fighters;
    public List<EntityPanel> fightersPanel;

    public FightPanel(List<Entity> theFighters) {
        fighters = theFighters;
        fightersPanel = new ArrayList<>();

        setLayout(new BorderLayout());
        
        // Panel gauche de la zone des entités : noms des héros
        JPanel panelHeroes = new JPanel();
        panelHeroes.setLayout(new BoxLayout(panelHeroes, BoxLayout.Y_AXIS));
        panelHeroes.setBorder(BorderFactory.createEmptyBorder(10, 50, 10, 10));

        // Panel droit de la zone des entités : noms des monstres
        JPanel panelMonsters = new JPanel();
        panelMonsters.setLayout(new BoxLayout(panelMonsters, BoxLayout.Y_AXIS));
        panelMonsters.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 50));

        // Affichage des Entités dans leur panel
        for (Entity e : fighters) {
            EntityPanel ePanel;
            if (e instanceof Monster) {
                ePanel = new EntityPanel(e, true);
                panelMonsters.add(ePanel);
                //panelMonsters.add(new JLabel(e.getLife() + "/" + e.getMaxLife()));
            } else {
                ePanel = new EntityPanel(e, false);
                panelHeroes.add(ePanel);
                //panelHeroes.add(new JLabel(e.getLife() + "/" + e.getMaxLife()));
            }
            ePanel.updateHP(); // Pour ne pas avoir les pourcentages.
            fightersPanel.add(ePanel);
        }
        
        // Panel du haut : zone des entités
        JPanel entityArea = new JPanel(new BorderLayout());
        entityArea.add(panelMonsters, BorderLayout.WEST);
        entityArea.add(panelHeroes, BorderLayout.EAST);

        // Panel du bas : boutons d'action
        JPanel buttonsPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        for (FightAction action : FightAction.getFightActions()) {
            buttonsPanel.add(makeButton(action, action.toString()));
        }

        add(entityArea, BorderLayout.NORTH);
        add(buttonsPanel, BorderLayout.SOUTH);
    }

    private JButton makeButton(FightAction action, String message) {
        JButton button = new JButton(action.getAction());
        button.addActionListener(e -> {
            switch (action) {
                case FightAction.attack -> {
                    for (EntityPanel fighter : fightersPanel) {
                        fighter.getEntity().isTarget(3);
                        fighter.updateHP();
                    }
                }
                default -> System.out.println(message);
            }
        });
        return button;
    }
}


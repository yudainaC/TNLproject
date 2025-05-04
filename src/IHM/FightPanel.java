package IHM;

import gameCore.GameFight.FightAction;
import gameCore.GameObjects.GameEntities.Single.Entity;
import gameCore.GameObjects.GameEntities.Single.Monster;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class FightPanel extends JPanel {
    private final List<Entity> fighters;
    private final List<EntityPanel> fightersPanel;
    public Entity actualFighter;

    public FightPanel(List<Entity> theFighters) {
        fighters = theFighters;
        fightersPanel = new ArrayList<>();
        actualFighter = theFighters.getFirst();

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
            } else {
                ePanel = new EntityPanel(e, false);
                panelHeroes.add(ePanel);
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
            buttonsPanel.add(makeButton(action));
        }

        // Panel du bas : zone d'actions
        JPanel actionArea = new JPanel(new BorderLayout());
        actionArea.add(new JLabel("Tour "+ 1 + " : " + actualFighter.getName() + " agis"), BorderLayout.NORTH);
        actionArea.add(buttonsPanel, BorderLayout.SOUTH);

        // Affichage des Panels
        add(entityArea, BorderLayout.NORTH);
        add(actionArea, BorderLayout.SOUTH);
    }

    private JButton makeButton(FightAction action) {
        JButton button = new JButton(action.getAction());
        button.addActionListener(e -> {
            switch (action) {
                case FightAction.attack -> {
                    for (EntityPanel fighter : fightersPanel) {
                        fighter.getEntity().isTarget(3);
                        fighter.updateHP();
                    }
                }
                default -> System.out.println(action);
            }
        });
        return button;
    }
}


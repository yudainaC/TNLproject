package IHM;

import gameCore.GameFight.Fight;
import gameCore.GameFight.FightAction;
import gameCore.GameObjects.GameEntities.Single.Entity;
import gameCore.GameObjects.GameEntities.Single.Monster;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class FightPanel extends JPanel {

    private final List<EntityPanel> fightersPanel;
    private final List<Entity> fighters;
    private Entity currentFighter;
    private final Fight fight;

    private final JLabel turnArea;
    private JPanel buttonsArea;
    private final JPanel actionArea;

    public FightPanel(Fight theFight) {
        fightersPanel = new ArrayList<>();
        fighters = theFight.getOrder();
        currentFighter = fighters.getFirst();
        fight = theFight;

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

        // Panel du bas : zone d'actions
        actionArea = new JPanel(new BorderLayout());

        buttonsArea = new JPanel(new GridLayout(3, 2, 10, 10));
        resetButtonAction();

        turnArea = new JLabel("Tour "+ 1 + " : " + currentFighter.getName() + " agis");
        actionArea.add(turnArea, BorderLayout.NORTH);
        actionArea.add(buttonsArea, BorderLayout.SOUTH);

        // Affichage des Panels
        add(entityArea, BorderLayout.NORTH);
        add(actionArea, BorderLayout.SOUTH);
    }

    public void updateTurn(int turn, Entity newFighter) {
        currentFighter = newFighter;
        turnArea.setText("Tour "+ turn + " : " + currentFighter.getName() + " agis");
    }

    private JButton makeActionButton(FightAction action) {
        JButton button = new JButton(action.getAction());
        button.addActionListener(e -> {
            switch (action) {
                case FightAction.attack -> {
                    turnArea.setText(turnArea.getText()+ ", qui voulez-vous attaquer ?");
                    showTargetButtons(action);
                }
                default -> System.out.println(action);
            }
        });
        return button;
    }

    public void updateFighters() {
        for (EntityPanel entity : fightersPanel) {
            entity.updateHP();
        }
    }

    public List<Entity> getEnemiesOf() {
        List<Entity> listTarget = new ArrayList<>();
        for (Entity target : fighters) {
            if (target instanceof Monster) {
                listTarget.add(target);
            }
        }
        return listTarget;
    }


    public void showTargetButtons(FightAction actionType) {
        buttonsArea.removeAll();

        for (Entity cible : getEnemiesOf()) {
            JButton cibleButton = new JButton(cible.getName());
            cibleButton.addActionListener(e -> {
                if (actionType == FightAction.attack) {
                    cible.isTarget(currentFighter.getStrength());
                }
                updateFighters(); // met à jour les PV, logs...
                fight.nextTurn(); // on passe au suivant
            });
            buttonsArea.add(cibleButton);
        }

        // Bouton Annuler
        JButton annuler = new JButton("Annuler");
        annuler.addActionListener(e -> resetButtonAction());
        buttonsArea.add(annuler);

        actionArea.revalidate();
        actionArea.repaint();
    }

    public void resetButtonAction() {
        buttonsArea.removeAll();

        for (FightAction action : FightAction.getFightActions()) {
            buttonsArea.add(makeActionButton(action));
        }

        actionArea.revalidate();
        actionArea.repaint();
    }
}


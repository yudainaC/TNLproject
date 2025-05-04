package IHM;
import gameCore.GameObjects.GameEntities.Single.Entity;

import javax.swing.*;
import java.awt.*;

public class EntityPanel extends JPanel {
    private final JLabel nameLabel;
    private final JProgressBar hpBar;
    private final Entity entity;
    private final Boolean isMonster;

    // Constructeur
    public EntityPanel(Entity entity, Boolean type) {
        this.entity = entity;

        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        nameLabel = new JLabel(entity.getName());
        nameLabel.setHorizontalAlignment(SwingConstants.CENTER);

        hpBar = new JProgressBar(0, entity.getMaxLife());
        hpBar.setValue(entity.getLife());
        hpBar.setStringPainted(true); // Affiche "30 / 50"
        hpBar.setForeground(Color.GREEN);

        add(nameLabel, BorderLayout.NORTH);
        add(hpBar, BorderLayout.SOUTH);

        isMonster = type;
    }

    // Getter
    public Entity getEntity() { return entity; }

    public void updateHP() {

        int percentLife = entity.getLife()*100/entity.getMaxLife();
        System.out.println(percentLife);
        if (percentLife <= 25) hpBar.setForeground(Color.RED);
        else if (percentLife <= 50) hpBar.setForeground(Color.ORANGE);
        else if (percentLife <= 75) hpBar.setForeground(Color.YELLOW);
        else hpBar.setForeground(Color.GREEN);

        hpBar.setMaximum(entity.getMaxLife());
        hpBar.setValue(entity.getLife());
        hpBar.setString(entity.getLife() + " / " + entity.getMaxLife());
    }
}

package gameCore.GameObjects.GameEntities.Group;

import gameCore.GameObjects.GameElements.Items.Item;
import gameCore.GameObjects.GameEntities.Single.Entity;
import gameCore.GameObjects.GameEntities.Single.Monster;
import gameCore.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * Groupe de monstres. Sous classe de Groupe.
 */
public class MobGroup extends Group{
    private final List<Item> loots;

    // Constructeur
    public MobGroup(Entity[] entityGroup) {

        group = new ArrayList<>();
        group.addAll(List.of(entityGroup));

        loots = new ArrayList<>();
        for (Entity monster : group) loots.addAll(((Monster) monster).getLoot());
    }

    @Override
    public boolean addToTeam(Entity h1) {
        if (this.group.size() < this.maxSize) return group.add(h1);
        return false;
    }

    public void looting() {
        System.out.println("Liste des objets obtenus : " + loots);
        for (Item item : loots) Player.getInventory().addItem(item);
    }

}

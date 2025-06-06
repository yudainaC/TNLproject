package gameCore.GameObjects.GameEntities.Group;

import exceptions.TeamIsFullException;
import gameCore.GameObjects.GameEntities.Single.Entity;
import gameCore.GameObjects.GameEntities.Single.Monster;

import java.util.*;

/**
 * Super classe des différents groupes d'entité.
 */
public class Group {
    protected ArrayList<Entity> group;
    protected int maxSize;

    /**
     * Constructeur
     * permet de créer une équipe d'Entité de taille max 4.
     */
    public Group(){
        this.maxSize = 4;
        group = new ArrayList<>();
    }

    // Getters
    public int getMaxSize() { return maxSize; }
    public List<Entity> getGroup() { return group; }

    /**
     * Méthode
     * permet d'ajouter un membre à une équipe de hero
     * @param h1 (le hero qu'on veut ajouter)
     * @return vraie si le hero a bien été ajouté, faux si ce n'est pas le cas
     * @throws TeamIsFullException si le groupe est plein.
     */
    public boolean addToTeam(Entity h1) throws TeamIsFullException {
        if (this.group.size() == this.maxSize) throw new TeamIsFullException();
        return this.group.add(h1);
    }

    /**
     * Méthode
     * permet de retirer un membre à une équipe de hero
     * @param h2
     * Le héros à retirer.
     * @return vraie si le hero a bien été retire, faux si ce n'est pas le cas
     */
    public boolean removeToGroup(Entity h2) {
        return this.group.remove(h2);
    }

    // Affichage
    @Override
    public String toString() {
        String entities = "";
        for (Entity entity : this.group) entities += entity.getName() + "\n";
        return entities;
    }
}

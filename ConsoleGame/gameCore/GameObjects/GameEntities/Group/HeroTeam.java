package gameCore.GameObjects.GameEntities.Group;

import exceptions.TeamIsFullException;
import gameCore.GameObjects.GameEntities.Single.Entity;
import gameCore.GameObjects.GameEntities.Single.Hero;

import java.util.List;

/**
 * Groupe de héros. Sous classe de Groupe.
 */
public class HeroTeam extends Group {

    // Constructeur
    public HeroTeam() {
        super();
    }

    /**
     * Essaie d'ajouter un héros donné à l'équipe.
     * @param hero
     * Le héros.
     * @return
     * Vrai s'il a pu être ajouté, faux sinon.
     * @throws TeamIsFullException
     * Si l'équipe est pleine.
     */
    public boolean addToTeam(Hero hero) throws TeamIsFullException {
        if (this.group.contains(hero)) return false;
        if (this.group.size() == this.maxSize) throw new TeamIsFullException();
        this.group.add(hero);
        return true;
    }

    // Affichage
    @Override
    public String toString() {
        String entities = "";
        for (Entity entity : this.group) entities += entity.getName() + ": Nv " + ((Hero) entity).getLevel() + "\n";
        return entities;
    }
}

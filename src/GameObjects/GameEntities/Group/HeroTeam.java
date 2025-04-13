package GameObjects.GameEntities.Group;

import Exceptions.HeroAlreadyExistException;
import Exceptions.TeamIsFullException;
import GameObjects.GameEntities.Single.Entity;
import GameObjects.GameEntities.Single.Hero;

public class HeroTeam extends Group {

    public HeroTeam() {
        super();
    }

    public boolean addToTeam(Hero h1) throws TeamIsFullException, HeroAlreadyExistException {
        if (this.group.contains(h1)) throw new HeroAlreadyExistException();
        if (this.group.size() == this.maxSize) throw new TeamIsFullException();
        this.group.add(h1);
        return true;
    }

    public boolean removeToGroup(Hero h2) {
        return super.removeToGroup(h2);
    }

    // Affichage

    @Override
    public String toString() {
        String entities = "";
        for (Entity entity : this.group) entities += entity.getName() + ": " + ((Hero) entity).getLevel() + "\n";
        return entities;
    }
}

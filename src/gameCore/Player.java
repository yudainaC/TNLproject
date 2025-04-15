package gameCore;

import exceptions.TeamIsFullException;
import gameCore.GameObjects.GameElements.Skills.Skills;
import gameCore.GameObjects.GameEntities.Group.HeroTeam;
import gameCore.GameObjects.GameEntities.Single.Hero;

import java.util.HashSet;
import java.util.Set;

public class Player {
    private final HeroTeam team;
    private final Set<Skills> playerSkills;
    private final Set<Hero> followers;

    public Player() {
        this.team = new HeroTeam();
        this.playerSkills = new HashSet<>();
        this.followers = new HashSet<>();
    }

    // Getters
    public HeroTeam getTeam() { return this.team; }
    public Set<Skills> getPlayerSkills() { return this.playerSkills; }
    public Set<Hero> getFollowers() { return this.followers; }

    public Boolean putInMyTeam(Hero hero) throws TeamIsFullException {
        return this.team.addToTeam(hero);
    }

    public Boolean removeFromMyTeam(Hero hero) {
        this.followers.add(hero);
        return this.team.removeToGroup(hero);
    }

    public Boolean recruit(Hero hero) {
        for (Skills skill : hero.getSkills()) {
            if (!(this.playerSkills.contains(skill))) this.playerSkills.add(skill);
        }
        return this.followers.add(hero);
    }

    // Affichage
    public String showFollowers() {
        String heros = "Vos héros :\n";
        for (Hero hero : followers) heros += hero + "\n";
        return heros;
    }

}

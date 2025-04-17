package gameCore;

import exceptions.NotASkillsException;
import exceptions.TeamIsFullException;
import gameCore.GameObjects.GameElements.Skills.Skills;
import gameCore.GameObjects.GameEntities.Group.HeroTeam;
import gameCore.GameObjects.GameEntities.Single.Hero;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * La classe du joueur.
 */
public class Player {
    private final HeroTeam team;
    private final Set<Skills> playerSkills;
    private final Set<Hero> followers;

    // Constructeur
    public Player() {
        this.team = new HeroTeam();
        this.playerSkills = new HashSet<>();
        this.followers = new HashSet<>();
    }

    // Getters
    public HeroTeam getTeam() { return this.team; }
    public Set<Skills> getPlayerSkills() { return this.playerSkills; }
    public Set<Hero> getFollowers() { return this.followers; }

    /**
     * Methode qui permet d'ajouter une compétence à celles du héros.
     * @param skills
     * La compétence.
     * @return
     * Vrai si le skill est ajouté, faux sinon.
     */
    public boolean addSkill(Skills skills) throws NotASkillsException {

        if (this.playerSkills.size() == 4) {
            System.out.println("vous avez trop de skill equipé, voulez vous en enlever un (yes,no)");
            Scanner sc = new Scanner(System.in);
            String rep = sc.nextLine();
            if (rep.equals("yes")) {
                System.out.println("choisissez quel skill vous voulez supprimer (name skill)");
                System.out.println(this.getPlayerSkills());
                String choice = sc.nextLine();
                Skills suppr = Skills.parseSkills(choice);
                this.echangeSkill(suppr,skills);
                System.out.println(skills + " appris !");
                return true;
            }
            else if(rep.equals("no")){
                System.out.println("skill non appris");
                return false;
            }
            else {
                System.out.println("valeur incorrect");
                return false;
            }

        }

        System.out.println(skills + " appris !");
        return this.playerSkills.add(skills);
    }

    /**
     * Methode utilisé uniquement dans addSkill, permet d'échanger une compétence avec une autre.
     * @param remove
     * La compétence qu'il faut enlever.
     * @param skills
     * La compétence qu'il faut ajouter.
     * @return
     * vraie si les compétences ont bien été échangées.
     */
    public boolean echangeSkill(Skills remove, Skills skills) {
        this.playerSkills.remove(remove);
        System.out.println(remove + " supprimé !");
        return this.playerSkills.add(skills);
    }

    /**
     * Essaie d'ajouter un héros à son équipe.
     * @param hero
     * Le héros.
     * @return
     * Vrai s'il a été ajouté, faux sinon.
     * @throws TeamIsFullException
     * Si l'équipe est pleine.
     */
    public Boolean putInMyTeam(Hero hero) throws TeamIsFullException {
        return this.team.addToTeam(hero);
    }

    /**
     * Essaie d'enlever un héros donné de l'équipe.
     * @param hero
     * Le héros
     * @return
     * Vrai s'il a été enlevé, faux sinon.
     */
    public Boolean removeFromMyTeam(Hero hero) {
        this.followers.add(hero);
        return this.team.removeToGroup(hero);
    }

    /**
     * Essaie de recruter un héros donné de l'équipe.
     * @param hero
     * Le héros
     * @return
     * Vrai s'il a été recruté, faux sinon.
     */
    public Boolean recruit(Hero hero) {
        this.playerSkills.addAll(hero.getSkills());
        return this.followers.add(hero);
    }

    // Affichage
    public String showFollowers() {
        String heros = "Vos héros :\n";
        for (Hero hero : followers) heros += hero  + "\n<------->\n";
        return heros;
    }

    @Override
    public String toString() {
        return "Membre de l'équipe :\n" + team + "\n" +
                "Compétences de l'équipe :\n" + playerSkills + "\n\n" +
                this.showFollowers();
    }
}

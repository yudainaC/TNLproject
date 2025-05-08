package gameCore;

import exceptions.NotASkillsException;
import exceptions.TeamIsFullException;
import gameCore.GameObjects.GameElements.Inventory;
import gameCore.GameObjects.GameElements.Items.Item;
import gameCore.GameObjects.GameElements.Skills.Skills;
import gameCore.GameObjects.GameEntities.Group.HeroTeam;
import gameCore.GameObjects.GameEntities.Single.Hero;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * La classe du joueur.
 */
public abstract class Player {
    private static final HeroTeam team = new HeroTeam();
    private static final Set<Skills> playerSkills = new HashSet<>();
    private static final Set<Hero> followers = new HashSet<>();
    private static final Inventory inventory = new Inventory(0);
    private static int coins = 0;

    // Constructeur

    // Getters
    public static HeroTeam getTeam() { return team; }
    public static Set<Skills> getPlayerSkills() { return playerSkills; }
    public static Set<Hero> getFollowers() { return followers; }
    public static Inventory getInventory() { return inventory; }
    public static int getCoins() { return coins; }

    public static Boolean sellItem() {
        Scanner sc = new Scanner(System.in);
        int i = 0;
        System.out.println("Quel objet voulez-vous vendre ?");
        for (Item item : inventory.getInventory()) {
            System.out.println(i + ": " + item);
            i += 1;
        }
        System.out.println((i+1) + ": Annuler");
        int chosenOne = sc.nextInt();
        if (chosenOne >= 0 && chosenOne <= i) {
            Item item = inventory.getInventory().get(chosenOne);
            inventory.removeItem(item);
            coins += item.getValue();
            System.out.println(item.getName() + " a été vendu pour " + item.getValue() + " pièces.");
            return true;
        }
        System.out.println("Vous n'avez rien vendu.");
        return false;
    }

    /**
     * Methode qui permet d'ajouter une compétence à celles du héros.
     * @param skills
     * La compétence.
     * @return
     * Vrai si le skill est ajouté, faux sinon.
     */
    public static boolean addSkill(Skills skills) throws NotASkillsException {

        if (playerSkills.size() == 4) {
            System.out.println("vous avez trop de talent équipé, voulez vous en enlever un (yes,no)");
            Scanner sc = new Scanner(System.in);
            String rep = sc.nextLine();
            if (rep.equals("yes")) {
                System.out.println("choisissez quel skill vous voulez supprimer (name skill)");
                System.out.println(playerSkills);
                String choice = sc.nextLine();
                exchangeSkill(Skills.parseSkills(choice),skills);
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
        return playerSkills.add(skills);
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
    public static boolean exchangeSkill(Skills remove, Skills skills) {
        playerSkills.remove(remove);
        System.out.println(remove + " supprimé !");
        return playerSkills.add(skills);
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
    public static Boolean putInMyTeam(Hero hero) throws TeamIsFullException {
        return team.addToTeam(hero);
    }

    /**
     * Essaie d'enlever un héros donné de l'équipe.
     * @param hero
     * Le héros
     * @return
     * Vrai s'il a été enlevé, faux sinon.
     */
    public static Boolean removeFromMyTeam(Hero hero) {
        followers.add(hero);
        return team.removeToGroup(hero);
    }

    /**
     * Essaie de recruter un héros donné de l'équipe.
     * @param hero
     * Le héros
     * @return
     * Vrai s'il a été recruté, faux sinon.
     */
    public static Boolean recruit(Hero hero) {
        playerSkills.addAll(hero.getSkills());
        inventory.upMaxWeight(hero.getStrength()*10);
        return followers.add(hero);
    }


    // Affichage
    public String showFollowers() {
        StringBuilder heroes = new StringBuilder("Vos héros :\n");
        for (Hero hero : followers) heroes.append(hero).append("\n<------->\n");
        return heroes.toString();
    }

    @Override
    public String toString() {
        return "Membre de l'équipe :\n" + team + "\n" +
                "Compétences de l'équipe :\n" + playerSkills + "\n\n" +
                this.showFollowers();
    }
}

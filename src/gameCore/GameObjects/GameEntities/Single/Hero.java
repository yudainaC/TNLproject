package gameCore.GameObjects.GameEntities.Single;

import gameCore.GameFight.Fight;
import gameCore.GameFight.FightAction;
import gameCore.GameObjects.GameElements.Items.Bonus;
import gameCore.GameObjects.GameElements.Items.Consumable;
import gameCore.GameObjects.GameElements.Items.Item;
import gameCore.GameObjects.GameElements.Items.Weapon;
import gameCore.GameObjects.GameElements.Skills.Skills;
import gameCore.GameObjects.GameElements.Spells.Spell;
import gameCore.Player;

import java.util.*;

/**
 * Sous-classe de Entity, définie les héros jouables.
 */
public class Hero extends Entity {
    private int level;
    private int exp;
    private int spellSlot;
    private Weapon equippedWeapon;
    private List<Object[]> bonuses;
    private final Set<Skills> skills;

    /**
     * Constructeur
     * Définit les valeurs par défaut des attributs :
     * level = 0
     * exp = 0
     * la taille de l'inventaire est égale à la force du personnage multiplié par 10.
     * L'inventaire est vide.
     * La taille de la liste de sorts commence à 2. La liste est vide.
     * Voir le constructeur d'Entity pour le reste.
     */
    public Hero(String itName, String itDescription, Set<Skills> itSkills) {
        super(itName, itDescription);

        FightAction[] theActions = new FightAction[6];
        theActions[0] = FightAction.forfeit;
        theActions[1] = FightAction.attack;
        theActions[2] = FightAction.conjure;
        theActions[3] = FightAction.defend;
        theActions[4] = FightAction.use;
        theActions[5] = FightAction.recover;

        this.maxLife = 5;
        this.maxMana = 10;
        this.life = this.maxLife;
        this.mana = this.maxMana;
        this.strength = 2;
        this.actions = theActions;
        this.defense = 1;
        this.speed = 1;
        this.isReady = false;
        this.spellSlot = 2;
        this.spells = new ArrayList<>();

        this.bonuses = new ArrayList<>();
        this.equippedWeapon = null;

        this.level = 1;
        this.exp = 0;

        this.skills = new HashSet<>(4);
        this.skills.addAll(itSkills);
    }

    // Getters
    public int getLevel() { return this.level; }
    public int getExp() { return this.exp; }
    public int getSpellSlot() { return spellSlot; }
    public Weapon getEquippedWeapon() { return equippedWeapon; }
    public Set<Skills> getSkills() { return this.skills; }

    /**
     * Permet au Hero de récupérer les PV et le mana perdu.
     */
    public void regenerate() {
        this.mana = this.maxMana;
        this.life = this.maxLife;
    }

    /**
     * Monte de niveau le Hero : augmente ses stats et son niveau, réduit l'expérience et récupère les PV et le mana perdu.
     * Augmente le nombre d'emplacements de sort d'un tous les trois niveaux.
     */
    public void levelUp() {
        this.exp -= 10*this.level;
        this.level += 1;
        this.maxLife += 3;
        this.maxMana += 5;
        this.strength += 1;
        Player.getInventory().upMaxWeight(10);
        this.regenerate();
        if (level%3 == 0) {
            this.spellSlot += 1;
        }
        this.verifyLevel(0);
        System.out.println(this.name + " a atteint le niveau " + this.level + " !");
    }

    /**
     * Ajoute l'expérience gagnée et monte de niveau si possible
     * @param expDrop
     * Experience gagnée sur un monstre.
     */
    public void verifyLevel(int expDrop) {
        this.exp += expDrop;
        if (this.exp > (10*this.level)) this.levelUp();
    }

    /**
     * Déséquipe l'arme si la taille maximale de l'inventaire n'est pas dépassé en déséquipant l'arme.
     * @return
     * true si l'opération est réussi, false sinon.
     */
    public boolean unEquipWeapon() {
        if (this.equippedWeapon != null && Player.getInventory().addItem(equippedWeapon)) {
            this.strength -= this.equippedWeapon.getBonusStr();
            this.equippedWeapon = null;
            return true;
        }
        return false;
    }

    /**
     * Équipe l'arme si elle est présente dans l'inventaire et si aucune arme n'est équipé
     * ou que l'arme équipée est déséquipé avec succès.
     * @return
     * true si l'opération est réussi, false sinon.
     */
    public boolean equipWeapon(Weapon thisOne) {
        if (Player.getInventory().checkInventory(thisOne)) {
            this.unEquipWeapon();
            this.equippedWeapon = thisOne;
            this.strength += thisOne.getBonusStr();
            Player.getInventory().removeItem(thisOne);
            return true;
        }
        return false;
    }

    /**
     * Réalise l'action 'objet'. Affiche la liste des consommables dans la console et permet d'en choisir un.
     * @return
     * Le nom de l'objet consommé ou retour si aucun n'est choisi.
     */
    public String useObject() {
        Scanner sc = new Scanner(System.in);
        int i = 0;
        Consumable[] items = new Consumable[Player.getInventory().getInventory().size()];
        System.out.println("Quel objet voulez-vous utiliser ?");
        for (Item item : Player.getInventory().getInventory()) {
            if (item instanceof Consumable){
                items[i] = (Consumable) item;
                System.out.println(i + ": " + item.getName());
                i += 1;
            }
        }
        System.out.println((i+1) + ": Retour");
        int chosenOne = sc.nextInt();
        if (chosenOne >= 0 && chosenOne <= i) {
            Consumable item = items[chosenOne];
            System.out.println("Vous avez choisi : " + item.getName());
            Player.getInventory().removeItem(item);
            item.use(this);
            return this.name + FightAction.use;
        }
        return "retour";
    }

    /**
     * Voir méthode dans Entity.
     */
    @Override
    public String isGoingToDo(FightAction action, Fight fight) {

        return switch (action) {
            case forfeit -> {
                this.life = 0;
                fight.hasDied(this);
                yield this.name + FightAction.forfeit;
            }
            case attack -> {
                Entity opponent = whoIsTargeted(false, fight);
                if (opponent == null) yield "retour";
                boolean isAlive = opponent.isTarget(this.strength);
                if (isAlive) yield this.name + " attaque";
                else {
                    fight.hasDied(opponent);
                    yield this.name + " a tué " + opponent.getName();
                }
            }
            case conjure -> this.spellAction(fight);
            case defend -> {
                this.isReady = true;
                yield this.name + FightAction.defend;
            }
            case use -> this.useObject();
            default -> this.recoveryAction();
        };
    }

    /**
     * Applique ou enlève un bonus au personnage.
     * @param bonus
     * Le type de bonus appliqué.
     * @param howMuch
     * Le montant du bonus
     * @param tillWhen
     * La durée du bonus
     * @param apply
     * Vrai si le bonus doit être appliqué, faux s'il doit être enlevé.
     * @return
     * Un petit résumé.
     */
    public String applyEffect(Bonus bonus, int howMuch, int tillWhen, boolean apply) {
        Object[] theBonus = {bonus, howMuch, tillWhen};
        switch (bonus) {
            case life:
                this.life += howMuch;
                this.verifyHPMana();
                return howMuch + "PV récupéré";
            case maxLife:
                if (apply) bonuses.add(theBonus);
                this.maxLife += howMuch;
                this.life += howMuch;
                return "PV max augmentés de " + howMuch;
            case mana:
                this.mana += howMuch;
                this.verifyHPMana();
                return howMuch + "Mana récupéré";
            case maxMana:
                if (apply) bonuses.add(theBonus);
                this.maxMana += howMuch;
                this.mana += howMuch;
                return "Mana max augmentés de " + howMuch;
            case strength:
                if (apply) bonuses.add(theBonus);
                this.strength += howMuch;
                return "Force augmentée de " + howMuch;
            case speed:
                if (apply) bonuses.add(theBonus);
                this.speed += howMuch;
                return "Vitesse augmentée de " + howMuch;
            case defense:
            default:
                if (apply) bonuses.add(theBonus);
                this.defense += howMuch;
                return "Defense augmentée de " + howMuch;
        }
    }

    public void clearBonus() {
        this.bonuses = new ArrayList<>();
    }

    /**
     * Met à jour la durée des bonus appliqué sur le héros.
     */
    public void updateBonuses() {
        List<Integer> index = new ArrayList<>();
        for (int i = 0; i < this.bonuses.size(); i++) {
            int duration = (int) this.bonuses.get(i)[2];
            duration--;

            this.bonuses.get(i)[2] = duration;
            if (duration == 0) {
                Bonus bonusType = (Bonus) this.bonuses.get(i)[0];
                int bonus = (int) this.bonuses.get(i)[1];
                this.applyEffect(bonusType, bonus*(-1), duration, false);
                System.out.println(this.bonuses.get(i)[0]+" : dernier tour");
                index.addFirst(i);
            } else System.out.println(this.bonuses.get(i)[0]+" : "+duration+" tours restant");
        }
        for (int i : index) {
            this.bonuses.remove(i);
        }
    }

    // Affichage
    public String printSkills(){
        StringBuilder res = new StringBuilder();
        for(Skills talent : skills){
            res.append(talent);
        }
        return res.toString();
    }

    public String toString() {
        StringBuilder hero = new StringBuilder(this.name + " : niveau " + this.level + "\n" +
                this.life + "/" + this.maxLife + " PV \n" +
                this.mana + "/" + this.maxMana + " mana \n");

        int bonus = 0;
        String weapon = "" + this.equippedWeapon;
        if (!(this.equippedWeapon == null)) bonus = this.equippedWeapon.getBonusStr();
        else weapon = "aucune";
        hero.append("force : ").append(this.strength).append('(').append(this.strength - bonus).append('+').append(bonus).append(") \n").append("arme équipé : ").append(weapon).append('\n');

        if (!this.spells.isEmpty()) {
            hero.append("Sorts appris :\n");
            for (Spell spell : this.spells) {
                hero.append("   ").append(spell).append("\n");
            }
        } else {
            hero.append("Ne connais aucun sorts\n");
        }
        hero.append(this.printSkills());
        return hero + this.description;
    }
}

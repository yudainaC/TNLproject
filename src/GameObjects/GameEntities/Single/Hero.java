package GameObjects.GameEntities.Single;

import Exceptions.NonValidLifeException;
import Exceptions.NonValidManaException;
import Exceptions.NonValidStrengthException;
import GameObjects.FightActions.FightAction;
import GameObjects.GameElements.Inventory;
import GameObjects.GameElements.Items.Bonus;
import GameObjects.GameElements.Items.Weapon;
import GameObjects.GameElements.Spells.Spell;

/**
 * Sous-classe de Entity, définie les héros jouables.
 */
public class Hero extends Entity {
    private int level;
    private int exp;
    private int spellSlot;
    private Weapon equippedWeapon;
    private Inventory inventory;

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
    public Hero(String itName, String itDescription) {
        super(itName, itDescription);

        FightAction[] theActions = new FightAction[5];
        theActions[0] = FightAction.forfait;
        theActions[1] = FightAction.attaquer;
        theActions[2] = FightAction.conjurer;
        theActions[3] = FightAction.defendre;
        theActions[4] = FightAction.recuperer;

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
        this.spells = new Spell[this.spellSlot];

        this.equippedWeapon = null;
        this.inventory = new Inventory(this.strength*10);

        this.level = 0;
        this.exp = 0;
    }

    // Second Constructeur
    public Hero(String itName, String itDescription, int itLife, int itMana, int itStrength, Spell[] itSpells, int itDefense, int itSpeed)
            throws NonValidLifeException, NonValidManaException, NonValidStrengthException {
        super(itName, itDescription, itLife, itMana, itStrength, itSpells, itDefense, itSpeed);
        this.level = 0;
        this.exp = 0;
        this.inventory = new Inventory(this.strength*10);
    }

    // Getters
    public int getLevel() { return this.level; }
    public int getExp() { return this.exp; }
    public Inventory getInventory() { return this.inventory; }

    /**
     * Permet au Hero de récupérer les PV et le mana perdu.
     */
    public void regenerate() {
        this.mana = this.maxMana;
        this.life = this.maxLife;
    }

    /**
     * Monte de niveau le Hero : augmente ses stats et son niveau, réduit l'exp et récupère les PV et le mana perdu.
     * Augmente le nombre d'emplacements de sort d'un tous les trois niveaux.
     * @return
     * Renvoie une simple phrase.
     */
    public String levelUp() {
        this.level += 1;
        this.maxLife += 3;
        this.maxMana += 5;
        this.strength += 1;
        this.inventory.upMaxWeight(10);
        this.exp -= 10;
        this.regenerate();
        if (level%3 == 0) {
            this.spellSlot += 1;
            Spell[] tamp = this.spells;
            this.spells = new Spell[this.spellSlot];
            System.arraycopy(tamp, 0, this.spells, 0, tamp.length);
        }
        return this.name + " a atteint le niveau " + this.level + " !";
    }

    /**
     * Ajoute l'expérience gagnée et monte de niveau si possible
     * @param expDrop
     * Experience gagnée sur un monstre.
     */
    public void verifLevel(int expDrop) {
        this.level += expDrop;
        if (this.exp > (10*this.level)) this.levelUp();
    }

    /**
     * Déséquipe l'arme si la taille maximale de l'inventaire n'est pas dépassé en déséquipant l'arme.
     * @return
     * true si l'opération est réussi, false sinon.
     */
    public boolean desequipWeapon() {
        if (this.equippedWeapon != null && this.inventory.addItem(equippedWeapon)) {
            this.strength -= this.equippedWeapon.getBonusStr();
            this.equippedWeapon = null;
            return true;
        }
        return false;
    }

    /**
     * Equipe l'arme si elle est présente dans l'inventaire et si aucune arme n'est équipé
     * ou que l'arme équipée est déséquipé avec succés.
     * @return
     * true si l'opération est réussi, false sinon.
     */
    public boolean equipWeapon(Weapon thisOne) {
        if (this.inventory.checkInventory(thisOne)) {
            this.desequipWeapon();
            this.equippedWeapon = thisOne;
            this.strength += thisOne.getBonusStr();
            this.inventory.removeItem(thisOne);
            return true;
        }
        return false;
    }

    public String applyEffect(Bonus bonus, int howMuch) {
        switch (bonus) {
            case life:
                this.life += howMuch;
                this.verifHPMana();
                return howMuch + "PV récupéré";
            case maxLife:
                this.maxLife += howMuch;
                return "PV max augmentés de " + howMuch;
            case mana:
                this.mana += howMuch;
                this.verifHPMana();
                return howMuch + "Mana récupéré";
            case maxMana:
                this.maxMana += howMuch;
                return "Mana max augmentés de " + howMuch;
            case strentgh:
                this.strength += howMuch;
                return "Force augmentée de " + howMuch;
            case defense:
            default:
                this.defense += howMuch;
                return "Defense augmentée de " + howMuch;
        }
    }

    // Affichage
    public String toString() {
        System.out.println(this.name + " : niveau " + this.level);
        System.out.println(this.life + "/" + this.maxLife + " PV");
        System.out.println(this.mana + "/" + this.maxMana + " mana");

        int bonus = 0;
        String weapon = "" + this.equippedWeapon;
        if (!(this.equippedWeapon == null)) bonus = this.equippedWeapon.getBonusStr();
        else weapon = "aucune";
        System.out.println("force : " + this.strength + '(' + (this.strength-bonus) + '+' + bonus + ')');
        System.out.println("arme equipé : " + weapon);

        if (this.spells[0] != null) {
            System.out.println("Sorts appris :");
            for (Spell spell : this.spells) {
                System.out.println("   " + spell);
            }
        } else {
            System.out.println("Ne connais aucun sorts");
        }

        System.out.println("Inventaire :");
        System.out.println(this.inventory);
        return this.description;
    }
}

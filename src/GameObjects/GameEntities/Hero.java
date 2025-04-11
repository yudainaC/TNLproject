package GameObjects.GameEntities;

import Exceptions.NonValidLifeException;
import Exceptions.NonValidManaException;
import Exceptions.NonValidStrengthException;
import GameObjects.FightActions.FightAction;
import GameObjects.GameElements.Spells.Spell;

/**
 * Sous-classe de Entity, définie les héro jouables.
 */
public class Hero extends Entity {
    private int level;
    private int exp;
    private int spellSlot;
    private Object[] inventory;

    /**
     * Constructeur
     * Définit les valeurs par défaut des attribut :
     * level = 0
     * exp = 0
     * la taille de l'inventaire est égale a la force du personnage multiplié par 10.
     * l'inventaire est vide.
     * la taille de la liste de sorts commence a 2. La liste est vide.
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
        this.isReady = false;
        this.spellSlot = 2;
        this.spells = new Spell[this.spellSlot];

        this.level = 0;
        this.exp = 0;
        this.inventory = new Object[this.strength*10];
    }

    public Hero(String itName, String itDescription, int itLife, int itMana, int itStrength, Spell[] itSpells)
            throws NonValidLifeException, NonValidManaException, NonValidStrengthException {
        super(itName, itDescription, itLife, itMana, itStrength, itSpells);
        this.level = 0;
        this.exp = 0;
        this.inventory = new Object[this.strength*10];
    }

    // Getters
    public int getLevel() { return this.level; }
    public int getExp() { return this.exp; }
    public Object[] getInventory() { return this.inventory; }

    /**
     * Permet au Hero de récupèrer les PV et le mana perdu.
     */
    public void regenerate() {
        this.mana = this.maxMana;
        this.life = this.maxLife;
    }

    /**
     * Monte de niveau le Hero : augmente ses stats et son niveau, reduit l'exp et récupère les PV et le mana perdu.
     * Augmente le nombre d'emplacement de sort de 1 tout les 3 niveaux.
     * @return
     * Renvoie une simple phrase.
     */
    public String levelUp() {
        this.level += 1;
        this.maxLife += 3;
        this.maxMana += 5;
        this.strength += 1;
        this.exp -= 10;
        this.regenerate();
        if (level%3 == 0) {
            this.spellSlot += 1;
            Spell[] tamp = this.spells;
            this.spells = new Spell[this.spellSlot];
            for (int i = 0; i < tamp.length; i++) {
                this.spells[i] = tamp[i];
            }
        }
        return this.name + " a atteint le niveau " + this.level + " !";
    }

    public void verifLevel(int expDrop) {
        this.level += expDrop;
        if (this.exp > (10*this.level)) this.levelUp();
    }

    // Affichage
    public String toString() {
        System.out.println(this.name + " : niveau " + this.level);
        System.out.println(this.life + "/" + this.maxLife + " PV");
        System.out.println(this.mana + "/" + this.maxMana + " mana");
        System.out.println("force : " + this.strength);
        System.out.println("Actions :");
        for (FightAction action : this.actions) {
            System.out.println("   " + action.getAction());
        }
        return this.description;
    }
}

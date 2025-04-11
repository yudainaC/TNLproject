package GameObjects.GameEntities;

import Exceptions.NonValidLifeException;
import Exceptions.NonValidManaException;
import Exceptions.NonValidStrengthException;
import GameObjects.GameElements.Spells.Spell;

/**
 * Sous-classe de Entity, définie les héro jouables.
 */
public class Hero extends Entity {
    private int level;
    private int exp;
    private Object[] inventory;

    /**
     * Constructeur
     * Définit les valeurs par défaut des attribut :
     * level = 0
     * exp = 0
     * la taille de l'inventaire est égale a la force du personnage multiplié par 10.
     * l'inventaire est vide.
     *
     * Voir le constructeur d'Entity pour le reste.
     */
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
}

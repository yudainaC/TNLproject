package gameCore.GameObjects.GameElements.Items;

import exceptions.NonValidValueException;
import exceptions.NonValidWeightException;

public class Weapon extends Item {
    protected final int bonusStr;

    /**
     * Constructeur
     * Voir le constructeur de Item
     */
    public Weapon(String itName, String itDescription, int itValue, int itWeight, int itBonus)
            throws NonValidValueException, NonValidWeightException {

        super(itName, itDescription, itValue, itWeight);

        this.bonusStr = itBonus;
    }

    // getter
    public int getBonusStr() { return this.bonusStr; }

    // Affichage
    public String toString() {
        return this.name + " : +" + this.bonusStr + " de force, " +
                this.value + " Pieces" + ", " +
                this.weight + " Kg" + "\n" +
                "   " + this.description;
    }
}

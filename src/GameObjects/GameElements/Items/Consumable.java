package GameObjects.GameElements.Items;

import Exceptions.NonValidValueException;
import Exceptions.NonValidWeightException;
import GameObjects.GameEntities.Single.Entity;
import GameObjects.GameEntities.Single.Hero;

public class Consumable extends Item {
    private int bonus;
    private Bonus bonusType;

    /**
     * Constructeur
     * Voir Constructeur d'item.
     */
    public Consumable(String itName, String itDescription, int itValue, int itWeight, Bonus itType, int itBonus)
            throws NonValidValueException, NonValidWeightException {
        super(itName, itDescription, itValue, itWeight);
        this.bonus = itBonus;
        this.bonusType = itType;
    }

    public String use(Hero whoUseIt) {
        return whoUseIt.applyEffect(this.bonusType, this.bonus);
    }
}

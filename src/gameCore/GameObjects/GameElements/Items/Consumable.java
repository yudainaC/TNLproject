package gameCore.GameObjects.GameElements.Items;

import exceptions.NonValidValueException;
import exceptions.NonValidWeightException;
import gameCore.GameObjects.GameEntities.Single.Hero;

public class Consumable extends Item {
    private int bonus;
    private Bonus bonusType;
    private int duration;

    /**
     * Constructeur
     * Voir Constructeur d'item.
     */
    public Consumable(String itName, String itDescription, int itValue, int itWeight, Bonus itType, int itBonus, int itDuration)
            throws NonValidValueException, NonValidWeightException {
        super(itName, itDescription, itValue, itWeight);
        this.bonus = itBonus;
        this.bonusType = itType;
        this.duration = itDuration;
    }

    public String use(Hero whoUseIt) {
        return whoUseIt.applyEffect(this.bonusType, this.bonus, this.duration, true);
    }
}

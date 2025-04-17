package gameCore.GameObjects.GameElements.Items;

import exceptions.NotABonusException;

/**
 * Liste des bonus applicable.
 */
public enum Bonus {

    life, maxLife, mana, maxMana, strentgh, defense, vitesse;

    // Affichage
    public static Bonus parseBonus(String bonus) throws NotABonusException {
        return switch (bonus) {
            case "life" -> Bonus.life;
            case "maxLife" -> Bonus.maxLife;
            case "mana" -> Bonus.mana;
            case "maxMana" -> Bonus.maxMana;
            case "strength" -> Bonus.strentgh;
            case "defense" -> Bonus.defense;
            case "vitesse" -> Bonus.vitesse;
            default -> throw new NotABonusException();
        };
    }
}

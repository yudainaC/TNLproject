package gameCore.GameObjects.GameElements.Items;

import exceptions.NotABonusException;

public enum Bonus {

    life, maxLife, mana, maxMana, strentgh, defense;

    public static Bonus parseBonus(String bonus) throws NotABonusException {
        return switch (bonus) {
            case "life" -> Bonus.life;
            case "maxLife" -> Bonus.maxLife;
            case "mana" -> Bonus.mana;
            case "maxMana" -> Bonus.maxMana;
            case "strentgh" -> Bonus.strentgh;
            case "defense" -> Bonus.defense;
            default -> throw new NotABonusException();
        };
    }
}

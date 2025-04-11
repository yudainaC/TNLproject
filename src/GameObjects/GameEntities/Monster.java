package GameObjects.GameEntities;

import GameObjects.FightActions.Action;
import GameObjects.GameElements.Spells.Spell;

import java.util.Random;

public class Monster extends Entity {
    protected int xp;

    public Monster(String itName, String itDescription, int itLife, int itMana, int itStrength, Action[] itActions, Spell[] itSpells, int theXp) {
        super(itName, itDescription, itLife, itMana, itStrength, itActions, itSpells);
        this.xp=theXp;

    }

    /**
     * fonction qui permet de randomiser l'xp obtenu entre les personnages
     * @param bonusXp
     * @return l'xp obtenu par chaque perso
     */
    public int GetXp(Random bonusXp){
        int xpAdd = bonusXp.nextInt(10);
        bonusXp.ints();
        xp =xp + xpAdd;
        return xp;
    }

}

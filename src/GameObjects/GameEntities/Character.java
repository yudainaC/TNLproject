package GameObjects.GameEntities;

import GameObjects.FightActions.Action;
import GameObjects.GameElements.Spells.Spell;

public class Character extends Entity {
    private int level;
    private int exp;
    private Object[] inventory;

    public Character(String itName, String itDescription, int itLife, int itMana, int itStrength, Action[] itActions, Spell[] itSpells) {
        super(itName, itDescription, itLife, itMana, itStrength, itActions, itSpells);
        this.level = 0;
        this.exp = 0;
        this.inventory = new Object[this.strength*10];

    }

    public int getLevel() { return level; }
    public int getExp() { return exp; }
    public Object[] getInventory() { return inventory; }
}

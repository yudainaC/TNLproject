package GameFactory;

import Exceptions.NonValidManaException;
import Exceptions.NonValidValueException;
import Exceptions.NonValidWeightException;
import GameObjects.GameElements.Items.Bonus;
import GameObjects.GameElements.Items.Consumable;
import GameObjects.GameElements.Items.Item;
import GameObjects.GameElements.Items.Weapon;
import GameObjects.GameElements.Spells.Spell;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ItemFactory {

    public static HashMap<String, Item> loadAllItems() throws NonValidValueException, NonValidWeightException {
        HashMap<String, Item> items = new HashMap<>();
        items.put("Epée courte", new Weapon("Epée courte", "Une petite épée simple", 2, 5, 1));
        items.put("Potion de soin", new Consumable("Potion de soin", "Potion redonnant une petite quantité de PV", 1, 2, Bonus.life, 3));
        return items;
    }
}

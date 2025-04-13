package GameFactory;

import Exceptions.NonValidManaException;
import Exceptions.NonValidValueException;
import Exceptions.NonValidWeightException;
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
        return items;
    }
}

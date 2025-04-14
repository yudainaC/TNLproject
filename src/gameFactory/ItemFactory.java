package gameFactory;

import exceptions.NonValidValueException;
import exceptions.NonValidWeightException;
import gameCore.GameObjects.GameElements.Items.Bonus;
import gameCore.GameObjects.GameElements.Items.Consumable;
import gameCore.GameObjects.GameElements.Items.Item;
import gameCore.GameObjects.GameElements.Items.Weapon;

import java.util.HashMap;

public class ItemFactory {

    public static HashMap<String, Item> loadAllItems() throws NonValidValueException, NonValidWeightException {
        HashMap<String, Item> items = new HashMap<>();
        items.put("Epée courte", new Weapon("Epée courte", "Une petite épée simple", 2, 5, 1));
        items.put("Potion de soin", new Consumable("Potion de soin", "Potion redonnant une petite quantité de PV", 1, 2, Bonus.life, 3));
        return items;
    }
}

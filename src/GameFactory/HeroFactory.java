package GameFactory;

import Exceptions.NonValidManaException;
import Exceptions.NonValidValueException;
import Exceptions.NonValidWeightException;
import GameObjects.GameElements.Items.Item;
import GameObjects.GameElements.Items.Weapon;
import GameObjects.GameElements.Spells.Spell;
import GameObjects.GameEntities.Hero;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class HeroFactory {

    public static HashMap<String, Hero> loadAllHero() throws NonValidManaException {

        // Création des sorts :
        HashMap<String, Spell> spells = SpellFactory.loadAllSpells();

        // Création des héros :
        HashMap<String, Hero> heros = new HashMap<>();

        heros.put("Bobby", new Hero("Bobby le premier", "Bobby est le premier personnage", 2));
        heros.put("Conny", new Hero("Conny le premier", "Conny est l'antagoniste", 4));

        return heros;
    }
}

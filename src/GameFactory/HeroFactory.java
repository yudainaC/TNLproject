package GameFactory;

import Exceptions.NonValidManaException;
import GameObjects.GameElements.Spells.Spell;
import GameObjects.GameEntities.Single.Hero;

import java.util.HashMap;

public class HeroFactory {

    public static HashMap<String, Hero> loadAllHero() throws NonValidManaException {

        // Création des sorts :
        HashMap<String, Spell> spells = SpellFactory.loadAllSpells();

        // Création des héros :
        HashMap<String, Hero> heros = new HashMap<>();

        heros.put("Bobby", new Hero("Bobby le premier", "Bobby est le premier personnage"));
        heros.put("Conny", new Hero("Conny le premier", "Conny est l'antagoniste"));

        return heros;
    }
}

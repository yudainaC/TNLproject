package gameFactory;

import exceptions.NonValidManaException;
import gameCore.GameObjects.GameElements.Skills.Skills;
import gameCore.GameObjects.GameElements.Spells.Spell;
import gameCore.GameObjects.GameEntities.Single.Hero;

import java.util.*;

public class HeroFactory {

    public static HashMap<String, Hero> loadAllHero() throws NonValidManaException {

        // Création des sorts :
        HashMap<String, Spell> spells = SpellFactory.loadAllSpells();

        // Création des héros :
        HashMap<String, Hero> heros = new HashMap<>();

        Set<Skills> bobbySkills = new HashSet<>();
        bobbySkills.add(Skills.artisanat);
        heros.put("Bobby", new Hero("Bobby le premier", "Bobby est le premier personnage", bobbySkills));
        Set<Skills> connySkills = new HashSet<>();
        connySkills.add(Skills.artisanat);
        connySkills.add(Skills.cuisine);
        heros.put("Conny", new Hero("Conny le premier", "Conny est l'antagoniste", connySkills));

        return heros;
    }
}

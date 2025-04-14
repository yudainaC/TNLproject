package gameFactory;

import exceptions.NonValidLifeException;
import exceptions.NonValidManaException;
import exceptions.NonValidStrengthException;
import gameCore.GameObjects.GameElements.Spells.Spell;
import gameCore.GameObjects.GameEntities.Single.Monster;

import java.util.HashMap;

public class MonsterFactory {

    public static HashMap<String, Monster> loadAllMonster() throws NonValidManaException, NonValidLifeException, NonValidStrengthException {

        // Création des sorts :
        HashMap<String, Spell> spells = SpellFactory.loadAllSpells();

        // Création des héros :
        HashMap<String, Monster> monsters = new HashMap<>();

        Spell[] blobSpells = new Spell[1];
        monsters.put("Blob", new Monster("blob","Petit monstre visqueux sans réel enveloppe charnel",4,5, 2, blobSpells, 5, 2, 1));

        return monsters;
    }
}

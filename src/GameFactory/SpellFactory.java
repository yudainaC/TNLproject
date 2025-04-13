package GameFactory;

import Exceptions.NonValidManaException;
import GameObjects.GameElements.Spells.DamageSpell;
import GameObjects.GameElements.Spells.Spell;
import GameObjects.GameElements.Spells.SupportSpell;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SpellFactory {

    public static HashMap<String, Spell> loadAllSpells() throws NonValidManaException {
        HashMap<String, Spell> spells = new HashMap<>();
        spells.put("Tueur de Bobby", new DamageSpell("Tueur de Bobby", "Un sort crée pour blesser Bobby", 10, 3));
        spells.put("Soigner Bobby", new SupportSpell("Soigner Bobby", "Un sort crée pour rétablir Bobby", 6, -5));
        return spells;
    }
}

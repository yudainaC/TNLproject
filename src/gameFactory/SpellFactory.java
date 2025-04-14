package gameFactory;

import exceptions.NonValidManaException;
import gameCore.GameObjects.GameElements.Spells.DamageSpell;
import gameCore.GameObjects.GameElements.Spells.Spell;
import gameCore.GameObjects.GameElements.Spells.SupportSpell;

import java.util.HashMap;

public class SpellFactory {

    public static HashMap<String, Spell> loadAllSpells() throws NonValidManaException {
        HashMap<String, Spell> spells = new HashMap<>();
        spells.put("Tueur de Bobby", new DamageSpell("Tueur de Bobby", "Un sort crée pour blesser Bobby", 10, 3));
        spells.put("Soigner Bobby", new SupportSpell("Soigner Bobby", "Un sort crée pour rétablir Bobby", 6, -5));
        return spells;
    }
}

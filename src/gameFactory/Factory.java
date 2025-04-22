package gameFactory;

import exceptions.*;
import gameCore.GameObjects.GameElements.Items.*;
import gameCore.GameObjects.GameElements.Skills.Skills;
import gameCore.GameObjects.GameElements.Spells.DamageSpell;
import gameCore.GameObjects.GameElements.Spells.Spell;
import gameCore.GameObjects.GameElements.Spells.SupportSpell;
import gameCore.GameObjects.GameEntities.Single.Hero;
import gameCore.GameObjects.GameEntities.Single.Monster;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import static java.lang.Integer.parseInt;

/**
 * Méthodes permettant de récupérer les différents objets créés.
 */
public abstract class Factory {

    /**
     * Crée la liste des Item.
     * @return
     * La liste des Item.
     * @throws IOException
     * Si le csv est obsolète ?
     * @throws NonValidValueException
     * Voir Item.
     * @throws NonValidWeightException
     * Voir Item.
     * @throws NotABonusException
     * Si le bonus du consommable est mal écrit.
     */
    public static HashMap<String, Item> parseItem()
            throws IOException, NonValidValueException, NonValidWeightException, NotABonusException {

        BufferedReader in = new BufferedReader(new FileReader("ressources/items.csv"));
        String line;
        HashMap<String, Item> items = new HashMap<>();

        while (true) {

            line = in.readLine();
            if (line == null) break;
            String[] itemAttribute = line.split(",");

            switch (itemAttribute[0]) {
                case "Weapon":
                    items.put(itemAttribute[1], new Weapon(itemAttribute[1], itemAttribute[2], parseInt(itemAttribute[3]),
                            parseInt(itemAttribute[4]), parseInt(itemAttribute[5])));
                    break;
                case "Consumable":
                    items.put(itemAttribute[1], new Consumable(itemAttribute[1], itemAttribute[2], parseInt(itemAttribute[3]),
                            parseInt(itemAttribute[4]), Bonus.parseBonus(itemAttribute[5]), parseInt(itemAttribute[6]), parseInt(itemAttribute[7])));
                    break;
            }
        }

        return items;
    }

    /**
     * Crée la liste des Spell.
     * @return
     * La liste des Spell.
     * @throws IOException
     * Si le csv est obsolète ?
     * @throws NonValidManaException
     * Voir Spell.
     */
    public static HashMap<String, Spell> parseSpell() throws IOException, NonValidManaException {

        BufferedReader in = new BufferedReader(new FileReader("ressources/spells.csv"));
        String line;
        HashMap<String, Spell> spells = new HashMap<>();

        while (true) {

            line = in.readLine();
            if (line == null) break;
            String[] spellAttribute = line.split(",");

            switch (spellAttribute[0]) {
                case "DamageSpell":
                    spells.put(spellAttribute[1], new DamageSpell(spellAttribute[1], spellAttribute[2], parseInt(spellAttribute[3]),
                            parseInt(spellAttribute[4])));
                    break;
                case "SupportSpell":
                    spells.put(spellAttribute[1], new SupportSpell(spellAttribute[1], spellAttribute[2], parseInt(spellAttribute[3]),
                            parseInt(spellAttribute[4])));
                    break;
            }
        }

        return spells;
    }

    /**
     * Crée la liste des Monster.
     * @return
     * La liste des Monster.
     * @throws IOException
     * Si le csv est obsolète ?
     * @throws NonValidManaException
     * Voir Monster.
     * @throws NonValidLifeException
     * Voir Monster.
     * @throws NonValidStrengthException
     * Voir Monster.
     */
    public static HashMap<String, Monster> parseMonster() throws IOException, NonValidManaException, NonValidLifeException, NonValidStrengthException, NotAnItemException {

        BufferedReader in = new BufferedReader(new FileReader("ressources/monsters.csv"));
        String line;
        HashMap<String, Monster> monsters = new HashMap<>();

        while (true) {

            line = in.readLine();
            if (line == null) break;
            String[] monsterAttribute = line.split(",");

            String[] spellsKeys = monsterAttribute[5].split(";");
            Spell[] monsterSpells = new Spell[spellsKeys.length];
            int i = 0;
            for (String spellKey : spellsKeys) {
                monsterSpells[i] = Factory.parseSpell().get(spellKey);
                i++;
            }
            // TODO : Changer la gestion des spells dans Entity, Monster et Hero ?

            String[] itemsKeys = monsterAttribute[9].split(";");
            Item[] monsterLoot = new Item[itemsKeys.length];
            i = 0;
            for (String itemKey : itemsKeys) {
                try { monsterLoot[i] = Factory.parseItem().get(itemKey); }
                catch (NonValidValueException | NonValidWeightException | NotABonusException e) {throw new NotAnItemException(); }
                i++;
            }

            monsters.put(monsterAttribute[0], new Monster(monsterAttribute[0], monsterAttribute[1], parseInt(monsterAttribute[2]),
                    parseInt(monsterAttribute[3]), parseInt(monsterAttribute[4]), monsterSpells, parseInt(monsterAttribute[6]), parseInt(monsterAttribute[7]), parseInt(monsterAttribute[8]), monsterLoot));
        }
        return monsters;
    }

    /**
     * Crée la liste des Hero.
     * @return
     * La liste des Hero.
     * @throws IOException
     * Si le csv est obsolète ?
     * @throws NotASkillsException
     * Voir Hero.
     */
    public static HashMap<String, Hero> parseHeroSimple() throws IOException, NotASkillsException {

        BufferedReader in = new BufferedReader(new FileReader("ressources/heroes.csv"));
        String line;
        HashMap<String, Hero> heroes = new HashMap<>();

        while (true) {

            line = in.readLine();
            if (line == null) break;
            String[] heroAttribute = line.split(",");

            String[] skillsKeys = heroAttribute[2].split(";");
            Set<Skills> heroSkills = new HashSet<>();
            for (String skillKey : skillsKeys) {

                heroSkills.add(Skills.parseSkills(skillKey));
            }
            // TODO : Changer la gestion des spells dans Entity, Monster et Hero ?

            heroes.put(heroAttribute[0], new Hero(heroAttribute[0], heroAttribute[1], heroSkills));
        }
        return heroes;
    }
}

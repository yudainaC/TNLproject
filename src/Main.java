import Exceptions.*;
import GameFactory.HeroFactory;
import GameFactory.ItemFactory;
import GameFactory.MonsterFactory;
import GameFactory.SpellFactory;
import GameFight.Fight;
import GameObjects.GameElements.Items.Item;
import GameObjects.GameElements.Items.Weapon;
import GameObjects.GameEntities.Hero;
import GameObjects.GameEntities.Monster;
import GameObjects.GameElements.Spells.DamageSpell;
import GameObjects.GameElements.Spells.Spell;
import GameObjects.GameElements.Spells.SupportSpell;

import java.util.HashMap;
import java.util.List;

//***** La classe Main g�re le flux principal et ex�cute la m�thode main() qui lance l'application *****//
public class Main {
	
	//**** METHODES **//
	public static void main(String[] args) throws NonValidLifeException, NonValidManaException, NonValidStrengthException, YouAreTargetingYourselfDumbBoyException, NonValidValueException, NonValidWeightException {

		// Import des armes
		HashMap<String, Item> items = ItemFactory.loadAllItems();
		Item shortSword = items.get("Epée courte");

		// Import des Entités
		// Import des Monstres
		HashMap<String, Monster> monsters = MonsterFactory.loadAllMonster();

		Monster blob = monsters.get("Blob");
		System.out.println(blob);
		System.out.println();

		// Import des Heros
		HashMap<String, Hero> heros = HeroFactory.loadAllHero();
		// Bobby
		Hero bobby = heros.get("Bobby");
		System.out.println(bobby);
		System.out.println(bobby.getInventory().addItem(shortSword));
		System.out.println(bobby.equipWeapon((Weapon) shortSword));
		System.out.println(bobby);
		System.out.println(bobby.getInventory().addItem(shortSword));
		System.out.println(bobby.equipWeapon((Weapon) shortSword));
		System.out.println(bobby);
		System.out.println(bobby.getInventory().removeItem(shortSword));
		System.out.println(bobby);
		System.out.println();

		// Conny
		Hero conny = heros.get("Conny");;
		System.out.println(conny);
		System.out.println();

		// Combats
		Fight firstFight = new Fight();
		System.out.println(firstFight.fullFight(conny, bobby));

		System.exit(1);
	}
}
import Exceptions.*;
import GameFactory.HeroFactory;
import GameFactory.ItemFactory;
import GameFactory.MonsterFactory;
import GameObjects.GameElements.Items.Item;
import GameObjects.GameElements.Items.Weapon;
import GameObjects.GameEntities.Group.HeroTeam;
import GameObjects.GameEntities.Single.Hero;
import GameObjects.GameEntities.Single.Monster;
import GameObjects.GameEntities.Group.Group;

import java.util.HashMap;

//***** La classe Main g�re le flux principal et ex�cute la m�thode main() qui lance l'application *****//
public class Main {
	
	//**** METHODES **//
	public static void main(String[] args) throws NonValidLifeException, NonValidManaException, NonValidStrengthException, YouAreTargetingYourselfDumbBoyException, NonValidValueException, NonValidWeightException, TeamIsFullException, HeroAlreadyExistException {

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
		/*Fight firstFight = new Fight();
		System.out.println(firstFight.fullFight(conny, bobby));

		System.exit(1);*/

		//Test Equipe
		HeroTeam E1 = new HeroTeam();
		E1.addToTeam(bobby);
		System.out.println(E1);
		System.out.println(E1.addToTeam(conny));
		System.out.println(E1);
		System.out.println(E1.addToTeam(bobby));
		System.out.println(E1);
	}
}
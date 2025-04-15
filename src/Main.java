import exceptions.*;
import gameCore.Player;
import gameFactory.HeroFactory;
import gameFactory.ItemFactory;
import gameFactory.MonsterFactory;
import gameCore.GameFight.Fight;
import gameCore.GameObjects.GameElements.Items.Item;
import gameCore.GameObjects.GameEntities.Group.HeroTeam;
import gameCore.GameObjects.GameEntities.Single.Hero;
import gameCore.GameObjects.GameEntities.Single.Monster;

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
		//System.out.println(blob);
		//System.out.println();

		// Import des Heros
		HashMap<String, Hero> heros = HeroFactory.loadAllHero();
		// Bobby
		Hero bobby = heros.get("Bobby");
		bobby.getInventory().addItem(items.get("Potion de soin"));

		// Conny
		Hero conny = heros.get("Conny");;
		//System.out.println(conny);
		//System.out.println();

		// Combats
		/*Fight firstFight = new Fight();
		System.out.println(firstFight.fullFight(conny, bobby));

		System.exit(1);*/

		//Test Equipe
		Player player = new Player();
		player.recruit(bobby);
		player.recruit(conny);
		player.recruit(bobby);
		player.putInMyTeam(bobby);
		player.putInMyTeam(bobby);
		player.putInMyTeam(conny);
		player.removeFromMyTeam(bobby);
		player.putInMyTeam(bobby);
		System.out.println(player);
	}
}
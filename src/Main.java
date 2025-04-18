import exceptions.*;
import exceptions.NotABonusException;
import gameCore.GameFight.Fight;
import gameCore.GameObjects.GameElements.Items.Weapon;
import gameCore.GameObjects.GameElements.Skills.Skills;
import gameCore.Player;
import gameFactory.Factory;
import gameCore.GameObjects.GameElements.Items.Item;
import gameCore.GameObjects.GameEntities.Single.Hero;
import gameCore.GameObjects.GameEntities.Single.Monster;

import java.io.IOException;
import java.util.HashMap;

//***** La classe Main g�re le flux principal et ex�cute la m�thode main() qui lance l'application *****//
public class Main {
	
	//**** METHODES **//
	public static void main(String[] args) throws NonValidLifeException, NonValidManaException, NonValidStrengthException,
            YouAreTargetingYourselfDumbBoyException, NonValidValueException, NonValidWeightException, TeamIsFullException,
            IOException, NotABonusException, NotASkillsException {

		//Import des armes
		HashMap<String, Item> items = Factory.parseItem();
		Weapon shortSword = (Weapon) items.get("Epée courte");
		System.out.println(shortSword);

		// Import des Entités
		// Import des Monstres
		HashMap<String, Monster> monsters = Factory.parseMonster();

		Monster blob = monsters.get("blob");
		System.out.println(blob);
		//System.out.println();

		// Import des Heros
		HashMap<String, Hero> heros = Factory.parseHeroSimple();
		// Bobby
		Hero bobby = heros.get("Bobby le premier");
		bobby.getInventory().addItem(shortSword);
		bobby.getInventory().addItem(items.get("Potion de soin"));
		bobby.getInventory().addItem(items.get("Potion de force"));
		bobby.equipWeapon(shortSword);

		// Conny
		Hero conny = heros.get("Conny le premier");
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
		System.out.println(bobby);


	}

}
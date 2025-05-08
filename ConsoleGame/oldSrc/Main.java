import exceptions.*;
import exceptions.NotABonusException;
import gameCore.GameObjects.GameElements.Items.Weapon;
import gameCore.Player;
import gameFactory.Factory;
import gameCore.GameObjects.GameElements.Items.Item;
import gameCore.GameObjects.GameEntities.Single.Hero;
import gameCore.GameObjects.GameEntities.Single.Monster;

import java.io.IOException;
import java.util.HashMap;

//***** La classe Main gère le flux principal et exécute la méthode main() qui lance l'application *****//
public class Main {
	
	//**** METHODS **//
	public static void main(String[] args) throws NonValidLifeException, NonValidManaException, NonValidStrengthException,
			NonValidValueException, NonValidWeightException, IOException, NotABonusException, NotASkillsException, NotAnItemException {

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

		// Import des héros
		HashMap<String, Hero> heroes = Factory.parseHeroSimple();
		// Bobby
		Hero bobby = heroes.get("Bobby le premier");
		Player.getInventory().addItem(shortSword);
		Player.getInventory().addItem(items.get("Potion de soin"));
		Player.getInventory().addItem(items.get("Potion de force"));
		bobby.equipWeapon(shortSword);

		// Conny
		Hero conny = heroes.get("Conny le premier");
		//System.out.println(conny);
		//System.out.println();

		// Combats
		/* Fight firstFight = new Fight();
		System.out.println(firstFight.fullFight(conny, bobby));

		System.exit(1); */


	}

}
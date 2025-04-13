import Exceptions.*;
import GameFight.Fight;
import GameObjects.GameElements.Items.Weapon;
import GameObjects.GameEntities.Hero;
import GameObjects.GameEntities.Monster;
import GameObjects.GameElements.Spells.DamageSpell;
import GameObjects.GameElements.Spells.Spell;
import GameObjects.GameElements.Spells.SupportSpell;
import GameObjects.GameEntities.Team;

//***** La classe Main g�re le flux principal et ex�cute la m�thode main() qui lance l'application *****//
public class Main {
	
	//**** METHODES **//
	public static void main(String[] args) throws NonValidLifeException, NonValidManaException, NonValidStrengthException, YouAreTargetingYourselfDumbBoyException, NonValidValueException, NonValidWeightException {

		// Création des sorts : à déplacer autre-part ?
		DamageSpell bobbyKiller = new DamageSpell("Tueur de Bobby", "Un sort crée pour blesser Bobby", 10, 3);
		SupportSpell healBobby = new SupportSpell("Soigner Bobby", "Un sort crée pour rétablir Bobby", 6, -5);

		// Sorts de bobby
		Spell[] BobbySpells = new Spell[1];
		BobbySpells[0] = healBobby;
		//System.out.println(healBobby);

		// Sorts de Conny
		Spell[] ConnySpells = new Spell[1];
		ConnySpells[0] = bobbyKiller;
		//System.out.println(bobbyKiller);

		// Sorts du Blob
		Spell[] blobSpells = new Spell[1];

		// Creation des armes
		Weapon shortSword = new Weapon("Epée courte", "Une petite épée simple", 2, 5, 1);
		System.out.println(shortSword);

		// Création des Entités
		// Blob
		Monster blob = new Monster("blob","Petit monstre visqueux sans réel enveloppe charnel",4,5, 2, blobSpells, 5, 2);
		/*System.out.println(blob);
		System.out.println();*/

		// Bobby
		Hero bobby = new Hero("Bobby le premier", "Bobby est le premier personnage", 5, 11, 2, BobbySpells, 1);
		System.out.println(bobby);
		System.out.println(bobby.getInventory().addItem(shortSword));
		System.out.println(bobby.equipWeapon(shortSword));
		System.out.println(bobby);
		System.out.println(bobby.getInventory().addItem(shortSword));
		System.out.println(bobby.equipWeapon(shortSword));
		System.out.println(bobby);
		System.out.println(bobby.getInventory().removeItem(shortSword));
		System.out.println(bobby);
		System.out.println();

		// Conny
		Hero conny = new Hero("Conny le premier", "Conny est l'antagoniste", 6, 16, 2, ConnySpells, 4);
		/*System.out.println(conny);
		System.out.println();

		// Combats
		Fight firstFight = new Fight();
		System.out.println(firstFight.fullFight(conny, bobby));
		Fight secondFight = new Fight();
		System.out.println(secondFight.fullFight(blob, bobby));*/

		System.exit(1);

		//Test Equipe
		Team E1 = new Team(1);
	}
}
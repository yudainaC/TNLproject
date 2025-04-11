import Exceptions.NonValidLifeException;
import Exceptions.NonValidManaException;
import Exceptions.NonValidStrengthException;
import Exceptions.YouAreTargetingYourselfDumbBoyException;
import GameFight.Fight;
import GameObjects.GameEntities.Hero;
import GameObjects.GameEntities.Monster;
import GameObjects.GameElements.Spells.DamageSpell;
import GameObjects.GameElements.Spells.Spell;
import GameObjects.GameElements.Spells.SupportSpell;

//***** La classe Main g�re le flux principal et ex�cute la m�thode main() qui lance l'application *****//
public class Main {
	
	//**** METHODES **//
	public static void main(String[] args) throws NonValidLifeException, NonValidManaException, NonValidStrengthException, YouAreTargetingYourselfDumbBoyException {

		// Création des sorts : a déplacer autre-part ?
		DamageSpell bobbyKiller = new DamageSpell("Tueur de Bobby", "Un sort crée pour blesser Bobby", 10, 3);
		SupportSpell healBobby = new SupportSpell("Booby se soigne", "Un sort crée pour rétablir Bobby", 6, -5);

		// Sorts de bobby
		Spell[] BobbySpells = new Spell[1];
		BobbySpells[0] = healBobby;
		System.out.println(healBobby);

		// Sorts de Conny
		Spell[] ConnySpells = new Spell[1];
		ConnySpells[0] = bobbyKiller;
		System.out.println(bobbyKiller);

		// Sorts du Blob
		Spell[] blobSpells = new Spell[1];

		// Création des Entités
		// Blob
		Monster blob = new Monster("blob","Petit monstre visqueux sans réel enveloppe charnel",4,5, 2, blobSpells, 5);
		System.out.println(blob);
		System.out.println();

		// Bobby
		Hero bobby = new Hero("Bobby le premier", "Bobby le premier personnage", 5, 11, 2, BobbySpells);
		System.out.println(bobby);
		System.out.println();

		// Conny
		Hero conny = new Hero("Conny le premier", "Conny est l'antagoniste", 6, 16, 2, ConnySpells);
		System.out.println(conny);
		System.out.println();

		// Combats
		Fight firstFight = new Fight();
		System.out.println(firstFight.fullFight(conny, bobby));
		Fight secondFight = new Fight();
		System.out.println(secondFight.fullFight(blob, bobby));

		System.exit(1);
	}
}
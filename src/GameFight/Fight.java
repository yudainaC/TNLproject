package GameFight;
import Exceptions.YouAreTargetingYourselfDumbBoyException;
import GameObjects.GameEntities.Entity;
import GameObjects.FightActions.FightAction;
import java.util.Scanner;

/**
 * Represente un combat
 */
public class Fight {
	
	private int turn;

	// Constructeur
	public Fight() {
		this.turn = 1;
	}

	// Getters
	public int getTurn() { return this.turn; }

	/**
	 * Un tour de jeu. Affiche le menu de selection d'actions dans la console.
	 * @param fighter1
	 * Le combattant dont c'est le tour
	 * @param fighter2
	 * Le combattant visé. (Pour l'instant les combat sont des 1v1)
	 * @return
	 * Renvoie l'appel de la methode "isGoingToDo" avec l'action choisi en paramêtre.
	 * @throws YouAreTargetingYourselfDumbBoyException
	 * Si les deux combattant son les mêmes Entité.
	 */
	public String fightTurn(Entity fighter1, Entity fighter2) throws YouAreTargetingYourselfDumbBoyException {

		if (fighter1 == fighter2) throw new YouAreTargetingYourselfDumbBoyException();

		if(fighter1.getSpeed() >= fighter2.getSpeed()) {
			Scanner sc = new Scanner(System.in);
			System.out.println(fighter1.getName() + ", que voulez-vous faire ?");
			for (int i = 0; i < fighter1.getActions().length; i++) {
				System.out.println((i) + ": " + fighter1.getActions()[i].getAction());
			}
			int chosenOne = sc.nextInt();

			FightAction action = FightAction.recuperer;
			if (chosenOne > -1 && chosenOne < fighter1.getActions().length) {
				action = fighter1.getActions()[chosenOne];
			}
			System.out.println("Vous avez choisi : " + action.getAction());
			return fighter1.isGoingToDo(action, fighter2);
		}
		else{
			Scanner sc = new Scanner(System.in);
			System.out.println(fighter2.getName() + ", que voulez-vous faire ?");
			for (int i = 0; i < fighter2.getActions().length; i++) {
				System.out.println((i) + ": " + fighter2.getActions()[i].getAction());
			}
			int chosenOne = sc.nextInt();

			FightAction action = FightAction.recuperer;
			if (chosenOne > -1 && chosenOne < fighter2.getActions().length) {
				action = fighter2.getActions()[chosenOne];
			}
			System.out.println("Vous avez choisi : " + action.getAction());
			return fighter2.isGoingToDo(action, fighter1);
		}

	}

	/**
	 * Combat Complet, appel de FightTurn dans un while, le combat (boucle) s'arrète lorsqu'un joueur n'a plus de PV.
	 * Affiche le nombre de tour et les PV actuels des combattant a chaque passage dans la boucle
	 * @param fighter1
	 * Joue lorsque le tour est pair.
	 * @param fighter2
	 * Joue lorsque le tour est impair.
	 * @return
	 * Renvoie une courte phrase déclarant le vaiqueur.
	 * @throws YouAreTargetingYourselfDumbBoyException
	 * Si les deux combattant son les mêmes Entité.
	 */
	public String fullFight(Entity fighter1, Entity fighter2) throws YouAreTargetingYourselfDumbBoyException {

		if (fighter1 == fighter2) throw new YouAreTargetingYourselfDumbBoyException();

		System.out.println("tour : " + this.turn);
		while (fighter1.getLife() > 0 && fighter2.getLife() > 0) {
			String whichOne = "";
			System.out.println(fighter1.getName() + " : " + fighter1.getLife() + " PV, " + fighter1.getMana() + " Mana");
			System.out.println(fighter2.getName() + " : " + fighter2.getLife() + " PV, " + fighter2.getMana() + " Mana");
			if (this.turn%2 == 0) {
				whichOne = this.fightTurn(fighter1, fighter2);
			} else {
				whichOne = this.fightTurn(fighter2, fighter1);
			}
			if (!whichOne.equals("retour")) {
				this.turn += 1;
				System.out.println("tour : " + this.turn);
			}
		}
		
		String winner;
		if (fighter1.getLife()>0) {
			winner = fighter1.getName();
			System.out.println(fighter1.getName() + " : " + fighter1.getLife() + " PV");
			System.out.println(fighter2.getName() + " : 0 PV \n");
		} else {
			winner = fighter2.getName();
			System.out.println(fighter1.getName() + " : 0 PV");
			System.out.println(fighter2.getName() + " : " + fighter2.getLife() + " PV \n");
		}
		
		return "Fin du jeu, " + winner + " a gagné !";
	}
}

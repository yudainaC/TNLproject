package GameFight;
import GameObjects.GameEntities.Entity;
import GameObjects.FightActions.Action;
import java.util.Scanner;

public class Jeu {
	
	private int turn;
	
	public Jeu() {
		this.turn = 1;
	}
	
	public int getTurn() { return this.turn; }
	
	public String gameTurn(Entity player1, Entity player2) {
		Scanner sc = new Scanner(System.in);
		System.out.println(player1.getName() + ", que voulez-vous faire ?");
		for (int i=0; i < player1.getActions().length; i++) {
			System.out.println((i) + ": " + player1.getActions()[i].getAction());
		}
		int chosenOne = sc.nextInt();
		Action action = Action.recuperer;
		if (chosenOne > -1 && chosenOne < player1.getActions().length) {
			action = player1.getActions()[chosenOne];
		}
		System.out.println("Vous avez choisi : " + action.getAction());
		return player1.isGoingToDo(action, player2);
		
	}
	
	public String fullGame(Entity player1, Entity player2) {
		System.out.println("tour : " + this.turn);
		while (player1.getLife() > 0 && player2.getLife() > 0) {
			String whichOne = "";
			System.out.println(player1.getName() + " : " + player1.getLife() + " PV, " + player1.getMana() + " Mana");
			System.out.println(player2.getName() + " : " + player2.getLife() + " PV, " + player2.getMana() + " Mana");
			if (this.turn%2 == 0) {
				whichOne = this.gameTurn(player1, player2);
			} else {
				whichOne = this.gameTurn(player2, player1);
			}
			if (!whichOne.equals("retour")) {
				this.turn += 1;
				System.out.println("tour : " + this.turn);
			}
		}
		
		String winner = "";
		if (player1.getLife()>0) {
			winner = player1.getName();
			System.out.println(player1.getName() + " : " + player1.getLife() + " PV");
			System.out.println(player2.getName() + " : 0 PV \n");
		} else {
			winner = player2.getName();
			System.out.println(player1.getName() + " : 0 PV");
			System.out.println(player2.getName() + " : " + player2.getLife() + " PV \n");
		}
		
		return "Fin du jeu, " + winner + " a gagné !";
	}
}

package gameCore.GameFight;
import exceptions.YouAreFightingYourselfDumbPlayerException;
import exceptions.YouAreTargetingYourselfDumbBoyException;
import gameCore.GameObjects.GameEntities.Group.Group;
import gameCore.GameObjects.GameEntities.Group.HeroTeam;
import gameCore.GameObjects.GameEntities.Single.Entity;
import gameCore.GameObjects.GameEntities.Single.Hero;

import java.util.*;

/**
 * Represente un combat
 */
public class Fight {
	private final HeroTeam heroes;
	private final Group opponents;
	private int opponentsAlive;
	private int heroesAlive;
	private final List<Entity> order;
	private List<Entity> diedThisTurn;
	private int turn;

	// Constructeur
	public Fight(HeroTeam fightingHeroes, Group fightingOpponents) throws YouAreFightingYourselfDumbPlayerException {

		this.heroes = fightingHeroes;
		this.opponents = fightingOpponents;

		if (heroes == opponents) throw new YouAreFightingYourselfDumbPlayerException();

		this.heroesAlive = heroes.getGroup().size();
		this.opponentsAlive = opponents.getGroup().size();

		this.turn = 1;
		this.order = new ArrayList<>();
		order.addAll(this.heroes.getGroup());
		order.addAll(this.opponents.getGroup());
		order.sort((e1, e2) -> Integer.compare(e2.getSpeed(), e1.getSpeed()));
		this.diedThisTurn = new ArrayList<>();
	}

	// Getters
	public int getTurn() { return this.turn; }
	public HeroTeam getHeroes() { return heroes; }
	public Group getOpponents() { return opponents; }
	public int getOpponentsAlive() { return opponentsAlive; }
	public int getHeroesAlive() { return heroesAlive; }

	/**
	 * Un tour de jeu. Affiche le menu de selection d'actions dans la console.
	 * @param fighter Le combattant dont c'est le tour
	 * @return Renvoie l'appel de la methode "isGoingToDo" avec l'action choisi en paramêtre.
	 * @throws YouAreTargetingYourselfDumbBoyException
	 * Si le combattant ciblé est le même que le combattant dont c'est le tour
	 */
	public String fightTurn(Entity fighter) throws YouAreTargetingYourselfDumbBoyException {

		if (fighter instanceof Hero) ((Hero) fighter).updateBonuses();
		if (fighter.getLife() != 0) {
			Scanner sc = new Scanner(System.in);
			System.out.println(fighter.getName() + ", que voulez-vous faire ?");
			for (int i = 0; i < fighter.getActions().length; i++) {
				System.out.println((i) + ": " + fighter.getActions()[i].getAction());
			}
			int chosenOne = sc.nextInt();

			FightAction action = FightAction.recuperer;
			if (chosenOne > -1 && chosenOne < fighter.getActions().length) {
				action = fighter.getActions()[chosenOne];
			}
			System.out.println("Vous avez choisi : " + action.getAction());
			return fighter.isGoingToDo(action, this);
		}
		return "pass";
	}

	/**
	 * Ajoute l'entité à la liste des entités mortes ce tour-ci.
	 * @param entity
	 * L'entité morte.
	 */
	public void hasDied(Entity entity) {
		diedThisTurn.add(entity);
	}

	/**
	 * Enlève les entités mortes du combat.
	 */
	public void deleteDead() {
		for (Entity entity : diedThisTurn) {
			int index = 0;
			for (int i = 0; i < order.size(); i++) if (this.order.get(i)==entity) index = i;
			if (entity instanceof Hero) this.heroesAlive--;
			else this.opponentsAlive--;
			order.remove(index);
		}
		diedThisTurn = new ArrayList<>();
	}

	/**
	 * Combat Complet, appel de FightTurn dans un while,
	 * le combat (boucle) s'arrète lorsqu'une équipe n'a plus de membre.
	 * Affiche le nombre de tours à chaque passage dans la boucle
	 * @return
	 * Renvoie une courte phrase déclarant le vainqueur.
	 */
	public String fullTeamFight() throws YouAreTargetingYourselfDumbBoyException {

		while (true) {
			System.out.println("Ordre : "+this.getOrder());
			System.out.println("Tour actuel : " + this.turn);
            for (Entity entity : this.order) {
				while (true) if (!Objects.equals(this.fightTurn(entity), "retour")) break;
			}
			this.turn++;
			this.deleteDead();
			if (this.opponentsAlive == 0 || this.heroesAlive == 0) break;
		}

		if (this.heroesAlive > 0) {
			return "Victoire !";
		} else {
			return "Votre équipe a péri";
		}
	}

	// Affichage
	public List<String> getOrder() {
		List<String> parseOrder = new ArrayList<>();
		for (Entity entities : order) parseOrder.add(entities.getName());
		return parseOrder;
	}
}
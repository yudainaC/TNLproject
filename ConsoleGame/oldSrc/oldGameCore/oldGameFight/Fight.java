package gameCore.GameFight;

import exceptions.YouAreTargetingYourselfDumbBoyException;
import gameCore.GameObjects.GameEntities.Group.Group;
import gameCore.GameObjects.GameEntities.Group.HeroTeam;
import gameCore.GameObjects.GameEntities.Group.MobGroup;
import gameCore.GameObjects.GameEntities.Single.Entity;
import gameCore.GameObjects.GameEntities.Single.Hero;
import gameCore.GameObjects.GameEntities.Single.Monster;

import java.util.*;

/**
 * Représente un combat
 */
public class Fight {

	private final HeroTeam heroes;
	private int heroesAlive;

	private final MobGroup opponents;
	private int opponentsAlive;

	private List<Entity> diedThisTurn;

	private final List<Entity> order;
	private int turn;

	// Constructeur
	public Fight(HeroTeam fightingHeroes, MobGroup fightingOpponents) {

		heroes = fightingHeroes;
		heroesAlive = heroes.getGroup().size();

		opponents = fightingOpponents;
		opponentsAlive = opponents.getGroup().size();

		diedThisTurn = new ArrayList<>();

		order = new ArrayList<>();
		order.addAll(heroes.getGroup());
		order.addAll(opponents.getGroup());
		order.sort((e1, e2) -> Integer.compare(e2.getSpeed(), e1.getSpeed()));
		turn = 1;
	}

	/* Getters
	public int getTurn() { return this.turn; }
	public int getOpponentsAlive() { return opponentsAlive; }
	public int getHeroesAlive() { return heroesAlive; } */
	public HeroTeam getHeroes() { return heroes; }
	public Group getOpponents() { return opponents; }
	public List<Entity> getOrder() { return order; }

	/**
	 * Un tour de jeu. Affiche le menu de selection d'actions dans la console.
	 * @param fighter Le combattant dont c'est le tour
	 * @return Renvoie l'appel de la methode "isGoingToDo" avec l'action choisi en paramètre.
	 * @throws YouAreTargetingYourselfDumbBoyException
	 * Si le combattant ciblé est le même que le combattant dont c'est le tour
	 */
	public String fightTurn(Entity fighter) throws YouAreTargetingYourselfDumbBoyException {

		if (fighter.getLife() > 0) {

			if (fighter instanceof Hero) {
				((Hero) fighter).updateBonuses();
				Scanner sc = new Scanner(System.in);
				System.out.println(fighter.getName() + ", que voulez-vous faire ?");
				for (Entity entity : order)
					System.out.println(entity.getName() + " : " + entity.getLife() + "/" + entity.getMaxLife());
				for (int i = 0; i < fighter.getActions().length; i++) {
					System.out.println((i) + ": " + fighter.getActions()[i].getAction());
				}
				int chosenOne = sc.nextInt();

				FightAction action = FightAction.recover;
				if (chosenOne > -1 && chosenOne < fighter.getActions().length) {
					action = fighter.getActions()[chosenOne];
				}
				System.out.println("Vous avez choisi : " + action.getAction());
				return fighter.isGoingToDo(action, this);
			} else {
				String whatHeDo = ((Monster) fighter).isGoingToDo(this);
				System.out.println(whatHeDo);
				return whatHeDo;
			}

		}
		return "pass";
	}

	/**
	 * Ajoute l'entité à la liste des entités mortes ce tour-ci.
	 * L'entité morte.
	 */
	public void checkDead() {
		for (Entity entity: order) {
			if (entity.getLife() <= 0 && !diedThisTurn.contains(entity)) {
				if (entity instanceof Hero) this.heroesAlive--;
				else this.opponentsAlive--;
				System.out.println("ok");
				diedThisTurn.add(entity);
			}
		}
	}

	/**
	 * Enlève les entités mortes du combat.
	 */
	public void deleteDead() {
		for (Entity entity : diedThisTurn) {
			int index = 0;
			for (int i = 0; i < order.size(); i++) if (this.order.get(i)==entity) index = i;
			order.remove(index);
		}
		diedThisTurn = new ArrayList<>();
	}

	/**
	 * Combat Complet, appel de FightTurn dans un while,
	 * le combat (boucle) s'arrête lorsqu'une équipe n'a plus de membre.
	 * Affiche le nombre de tours à chaque passage dans la boucle
	 */
	public void fullTeamFight() throws YouAreTargetingYourselfDumbBoyException {

		while (this.opponentsAlive != 0 && this.heroesAlive != 0) {
			System.out.println("Ordre : "+this.getNamesOrder());
			System.out.println("Tour actuel : " + this.turn);
			for (Entity entity : this.order) {
				System.out.println("<---------------->");
				System.out.println();
				System.out.println("adv " + opponentsAlive + " you " + heroesAlive);
				if (this.opponentsAlive > 0 && this.heroesAlive > 0) {
					System.out.println(entity.getName());
					while (true) if (!Objects.equals(this.fightTurn(entity), "retour")) break;
					checkDead();
				}
			}
			this.turn++;
			this.deleteDead();
		}

		if (this.heroesAlive > 0) {
			this.victory();
		} else {
			System.out.println("Votre équipe a péri");
		}
	}

	public void victory() {
		System.out.println("Victoire !");
		System.out.println();
		System.out.println("Calcule de l'expérience :");
		for (Entity monster : this.opponents.getGroup()) {
			for (Entity hero : this.heroes.getGroup()) {
				((Monster) monster).getKilled((Hero) hero);
				((Hero) hero).clearBonus();
			}
		}
		System.out.println();
		System.out.println("Loot obtenu :");
		this.opponents.looting();
	}

	// Affichage
	public List<String> getNamesOrder() {
		List<String> parseOrder = new ArrayList<>();
		for (Entity entities : order) parseOrder.add(entities.getName());
		return parseOrder;
	}
}
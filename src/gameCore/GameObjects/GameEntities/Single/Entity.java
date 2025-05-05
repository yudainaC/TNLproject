package gameCore.GameObjects.GameEntities.Single;

import IHM.FightPanel;
import exceptions.*;
import gameCore.GameFight.Fight;
import gameCore.GameFight.FightAction;
import gameCore.GameObjects.GameElements.Spells.DamageSpell;
import gameCore.GameObjects.GameElements.Spells.Spell;
import gameCore.GameObjects.Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Le model plus spécifique aux entités. Super-Classe.
 */
public class Entity extends Model {
	
	protected int maxLife;
	protected int life;
	protected int strength;
	protected int maxMana;
	protected int mana;
	protected int speed;
	protected int defense;
	protected boolean isReady;
	protected FightAction[] actions;
	protected List<Spell> spells;


	/**
	 * Constructeur
	 * Génère la liste des actions et met l'attribut isReady à faux.
	 * Les PV max de l'entité sont initiés à 5, on part du principe qu'elle est créé avec ses PV au maximum.
	 * Le Mana max de l'entité est initié à 10, on part du principe qu'elle est créé avec son Mana au maximum.
	 * La force de l'entité est initié à 2, elle influe sur ses dégâts et la taille de son inventaire.
	 * La taille de départ de la liste de ses sorts est 1.
	*/
	public Entity(String itName, String itDescription) {
		super(itName, itDescription);

		FightAction[] theActions = new FightAction[5];
		theActions[0] = FightAction.forfeit;
		theActions[1] = FightAction.attack;
		theActions[2] = FightAction.conjure;
		theActions[3] = FightAction.defend;
		theActions[4] = FightAction.recover;

		this.maxLife = 5;
		this.maxMana = 10;
		this.life = this.maxLife;
		this.mana = this.maxMana;
		this.strength = 2;
		this.speed = 1;
		this.defense = 1;
		this.actions = theActions;
		this.spells = new ArrayList<>();
		this.isReady = false;
	}

	/**
	 * Constructeur
	 * Génère la liste des actions et met l'attribut isReady à faux.
	 * @param itLife
	 * Les PV max de l'entité, on part du principe qu'elle est créé avec ses PV au maximum.
	 * Ne peut pas être initié à moins de 1.
	 * De type entier.
	 * @param itMana
	 * Le Mana max de l'entité, on part du principe qu'elle est créé avec son Mana au maximum.
	 * Ne peut pas être initié à moins de 0.
	 * De type entier.
	 * @param itStrength
	 * La force de l'entité, elle influe sur ses dégâts et la taille de son inventaire.
	 * Ne peut pas être initié à moins de 1.
	 * De type entier.
	 * @param itSpells
	 * La liste de ses sorts.
	 * De type Spell.
	 * @throws NonValidLifeException
	 * Si itLife est inférieur à 1.
	 * @throws NonValidManaException
	 * Si itMana est inférieur à 0.
	 * @throws NonValidStrengthException
	 * Si itStrength est inférieur a 1.
	 */
	public Entity(String itName, String itDescription, int itLife, int itMana, int itStrength, List<Spell> itSpells, int itDefense, int itSpeed)
			throws NonValidLifeException, NonValidManaException, NonValidStrengthException {

		super(itName, itDescription);

		if (itLife < 1) throw new NonValidLifeException();
		if (itMana < 0) throw new NonValidManaException();
		if (itStrength < 1) throw new NonValidStrengthException();

		FightAction[] theActions = new FightAction[5];
		theActions[0] = FightAction.forfeit;
		theActions[1] = FightAction.attack;
		theActions[2] = FightAction.conjure;
		theActions[3] = FightAction.defend;
		theActions[4] = FightAction.recover;

		this.maxLife = itLife;
		this.life = itLife;
		this.maxMana = itMana;
		this.mana = itMana;
		this.strength = itStrength;
		this.actions = theActions;
		this.spells = itSpells;
		this.defense = itDefense;
		this.isReady = false;
		this.speed = itSpeed;
	}

	// Getters
	public int getLife() { return this.life; }
	public int getMana() { return this.mana; }
	public int getStrength() { return this.strength; }
	public FightAction[] getActions() { return this.actions; }
	public List<Spell> getSpells() { return this.spells; }
	public int getSpeed(){return this.speed;}

	public int getMaxLife() {
		return maxLife;
	}

	/**
	 * Affiche la liste des sorts, et permet d'en lancé un si la cible est correcte et si le mana est suffisant.
	 * @param fight
	 * Le combat actuel.
	 * @return
	 * Le nom du sort choisi ou retour.
	 */
	public String spellAction(Fight fight) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Votre mana : " + this.mana);
		System.out.println("Quel sort voulez-vous lancer ?");
		if (!this.spells.isEmpty()) {
			for (int i = 0; i < this.spells.size(); i++) {
				System.out.println((i + 1) + ": " + this.spells.get(i).getName() + ", " + this.spells.get(i).getMana() + " Mana");
			}
		}
		System.out.println((this.spells.size()+1) + ": Retour");
		int chosenOne = sc.nextInt();
		if (chosenOne > 0 && chosenOne < this.spells.size()+1 && this.mana >= this.spells.get(chosenOne-1).getMana()) {
			Spell spell = this.spells.get(chosenOne-1);
			System.out.println("Vous avez choisi : " + spell.getName());
			this.mana -= spell.getMana();
			Entity target;
			if (spell instanceof DamageSpell) {
				target = whoIsTargeted(false, fight);
			} else {
				target = whoIsTargeted(true, fight);
			}
			boolean isAlive = true;
			if (target != null) isAlive = spell.cast(target);
			if (!isAlive) fight.hasDied(target);
			return this.name + FightAction.conjure;
		}
		return "retour";
	}

	/**
	 * Fait l'action 'récupérer' : récupère deux manas et 1 PV.
	 * @return
	 * Un petit résumé.
	 */
	public String recoveryAction() {
		System.out.println(FightAction.recover);
		String what = this.name;
		if (this.life < this.maxLife) {
			this.life += 1;
			what = this.name + " a récupérer 1 PV";
		} else {
			what += " a déjà tout ses PV et";
		}
		if (this.mana < this.maxMana) {
			int manaDiff = this.maxMana - this.mana;
			if (manaDiff <= 2) {
				this.mana += manaDiff;
				what = this.name + " a récupérer " + manaDiff + " mana";
			}
			else {
				what = this.name + " a récupérer 2 mana";
				this.mana += 2;
			}
		} else {
			what += " a déjà tout son mana";
		}
		return what;
	}

	/**
	 * Permet de choisir une cible. Affiche toutes les Entités de l'équipe adverse en cas d'action négatives,
	 * et les membres de l'équipe (this non compris) alliée en cas d'action positive. Renvoie null si la cible
	 * est déjà à terre ou incorrecte.
	 * @param allies
	 * Un booléen, vrai s'il faut viser un allié, faux sinon.
	 * @param fight
	 * Le combat actuel.
	 * @return
	 * La cible.
	 */
	public Entity whoIsTargeted(Boolean allies, Fight fight) {
		//if (fightPanel == null) {
			Scanner sc = new Scanner(System.in);
			System.out.println("Qui voulez-vous viser ?");
			List<Entity> groupTarget = fight.getOpponents().getGroup();
			if (allies) {
				groupTarget = fight.getHeroes().getGroup();
				int index = 0;
				for (int i = 0; i < groupTarget.size(); i++) if (groupTarget.get(i) == this) index = i;
				groupTarget.remove(index);
			}

			for (int i = 0; i < groupTarget.size(); i++) {
				Entity target = groupTarget.get(i);
				System.out.println((i + 1) + ": " + target.name + ", " + target.life + "/" + target.maxLife + " PV restant");
			}
			System.out.println((groupTarget.size() + 1) + ": Retour");

			int chosenOne = sc.nextInt();

			if (chosenOne > 0 && chosenOne < groupTarget.size() + 1 && groupTarget.get(chosenOne - 1).life > 0) {
				Entity target = groupTarget.get(chosenOne - 1);
				System.out.println("Vous avez ciblé : " + target.name);
				return target;
			}
		//} else {

		//}
		return null;
	}

	/**
	 * Méthode associant une Action à son effet, sur la base d'un 'selon' l'action.
	 * L'action 'déclarer forfait' fait perdre le combat,
	 * L'action 'attaquer' inflige à l'adversaire un nombre de dégâts égale à la force de l'attaquant.
	 * L'action 'conjurer' ouvre le menu des sorts, affichant la liste des sorts,
	 * qui peuvent être lancé en échange d'une quantité de mana. Si aucun sort ou que le mot retour est choisi,
	 * rouvre le menu des actions.
	 * L'action 'se défendre' bloque deux dégâts de la prochaine attaque reçue.
	 * L'action 'récupérer' et l'action par défaut et régénère un pv et deux manas.
	 * @param action
	 * L'action choisie.
	 * @param fight
	 * Le combat actuel. Permet de récupérer la liste des adversaires/alliés.
	 * @return
	 * Renvoie une petite phrase résumant l'action choisie.
	 * @throws YouAreTargetingYourselfDumbBoyException
	 * Si la cible est l'entité faisant l'action.
	 */
	public String isGoingToDo(FightAction action, Fight fight)
			throws YouAreTargetingYourselfDumbBoyException {

		Entity opponent;

        switch (action) {
            case forfeit -> {
                this.life = 0;
                return this.name + FightAction.forfeit;
            }
            case attack -> {
                return this.name + " attaque";
            }
            case conjure -> {
                return this.spellAction(fight);
            }
            case defend -> {
                this.isReady = true;
                return this.name + FightAction.defend;
            }
            default -> {
                return this.recoveryAction();
            }
        }
	}

	/**
	 * Gère la modification des PV en fonction de leur montant :
	 * Négatifs pour du soin, Positifs pour une attaque.
	 * @param howMuch
	 * La quantité de PV à modifier.
	 * @return
	 * Renvoie un bref résumé de la modification.
	 */
	public boolean isTarget(int howMuch) {

		String result = this.name;
		boolean isAlive = true;

		if (howMuch > 0) {
			result += " prend " + howMuch + " dégâts.";
			if (this.isReady) {
				this.isReady = false; 
				howMuch -= this.defense;
				if (howMuch < 0) howMuch = 0;
				result = this.name + " se protégeait, il a pris " + (howMuch - this.defense) + " dégâts";
			}
		} else if (howMuch < 0) {
			result += " se soigne de " + howMuch + " PV";
		} else {
			result = "Fonction a terminer";
		}
		this.life -= howMuch;
		if (this.life <= 0) {
			result = this.name + " est mort";
			isAlive = false;
		}
		this.verifyHPMana();
		System.out.println(result);
		return isAlive;
	}

	/**
	 * Remet les pv à leur maximum s'ils l'ont dépassé après l'appel de 'isTarget'.
	 */
	public void verifyHPMana() {
		if (this.life > this.maxLife) this.life = this.maxLife;
		if (this.mana > this.maxMana) this.mana = this.maxMana;
	}

	// Affichage
	public String toString() {
		System.out.println(this.name + " : " + this.life + "/" + this.maxLife + " PV");
		System.out.println(this.mana + "/" + this.maxMana + " mana");
		if (this.spells != null) {
			System.out.println("Sorts appris :");
			for (Spell spell : this.spells) {
				System.out.println("   " + spell);
			}
		}
		return this.description;
	}
}

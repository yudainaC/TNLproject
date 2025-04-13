package GameObjects.GameEntities.Single;

import Exceptions.*;
import GameObjects.*;
import GameObjects.FightActions.FightAction;
import GameObjects.GameElements.Spells.DamageSpell;
import GameObjects.GameElements.Spells.Spell;
import GameObjects.GameElements.Spells.SupportSpell;

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
	protected Spell[] spells;


	/**
	 * Constructeur
	 * Génère la liste des actions et met l'attribut isReady à faux.
	 * Les PV max de l'entité sont initiés à 5, on part du principe qu'elle est créé avec ses PV au maximum.
	 * Le Mana max de l'entité est initié à 10, on part du principe qu'elle est créé avec son Mana au maximum.
	 * La force de l'entité est initié à 2, elle influe sur ses dégats et la taille de son inventaire.
	 * La taille de départ de la liste de ses sorts est 1.
	*/
	public Entity(String itName, String itDescription) {
		super(itName, itDescription);

		FightAction[] theActions = new FightAction[5];
		theActions[0] = FightAction.forfait;
		theActions[1] = FightAction.attaquer;
		theActions[2] = FightAction.conjurer;
		theActions[3] = FightAction.defendre;
		theActions[4] = FightAction.recuperer;

		this.maxLife = 5;
		this.maxMana = 10;
		this.life = this.maxLife;
		this.mana = this.maxMana;
		this.strength = 2;
		this.speed = 1;
		this.defense = 1;
		this.actions = theActions;
		this.spells = new Spell[1];
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
	 * Le Mana max de l'entité, on part du principe qu'elle est créé avec sont Mana au maximum.
	 * Ne peut pas être initié à moins de 0.
	 * De type entier.
	 * @param itStrength
	 * La force de l'entité, elle influe sur ses dégats et la taille de son inventaire.
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
	public Entity(String itName, String itDescription, int itLife, int itMana, int itStrength, Spell[] itSpells, int itDefense, int itSpeed)
			throws NonValidLifeException, NonValidManaException, NonValidStrengthException {

		super(itName, itDescription);

		if (itLife < 1) throw new NonValidLifeException();
		if (itMana < 0) throw new NonValidManaException();
		if (itStrength < 1) throw new NonValidStrengthException();

		FightAction[] theActions = new FightAction[5];
		theActions[0] = FightAction.forfait;
		theActions[1] = FightAction.attaquer;
		theActions[2] = FightAction.conjurer;
		theActions[3] = FightAction.defendre;
		theActions[4] = FightAction.recuperer;

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
	public Spell[] getSpells() { return this.spells; }
	public int getSpeed(){return this.speed;}

	public String spellAction(Entity opponent) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Votre mana : " + this.mana);
		System.out.println("Quel sort voulez-vous lancer ?");
		if (this.spells[0] != null) {
			for (int i = 0; i < this.spells.length; i++) {
				System.out.println((i + 1) + ": " + this.spells[i].getName() + ", " + this.spells[i].getMana() + " Mana");
			}
		}
		System.out.println((this.spells.length+1) + ": Retour");
		int chosenOne = sc.nextInt();
		if (chosenOne > 0 && chosenOne < this.spells.length+1 && this.mana >= this.spells[chosenOne-1].getMana()) {
			Spell spell = this.spells[chosenOne-1];
			System.out.println("Vous avez choisi : " + spell.getName());
			this.mana -= spell.getMana();
			if (spell instanceof DamageSpell) {
				((DamageSpell) spell).cast(opponent);
			} else if (spell instanceof SupportSpell) {
				((SupportSpell) spell).cast(this);
			}
			return this.name + FightAction.conjurer;
		}
		return "retour";
	}

	public String recupAction() {
		System.out.println(FightAction.recuperer);
		String what = this.name;
		if (this.life < this.maxLife) {
			this.life += 1;
			what = this.name + " a récupérer 1 PV";
		} else {
			what += " a déjà tout ses PV et";
		}
		if (this.mana < this.maxMana-1) {
			this.mana += 2;
			what = this.name + " a récupérer 2 mana";
		} else if (this.mana < this.maxMana) {
			this.mana += 1;
			what = this.name + " a récupérer 1 mana";
		} else {
			what += " a déjà tout son mana";
		}
		return what;
	}

	/**
	 * Méthode associant une Action à son effet, sur la base d'un 'selon' l'action.
	 * L'action 'déclarer forfait' fait perdre le combat,
	 * L'action 'attaquer' inflige à l'adversaire un nombre de dégat éguale à la force de l'attaquant.
	 * L'action 'conjurer' ouvre le menu des sorts, affichant la liste des sorts,
	 * qui peuvent être lancé en échange d'une quantité de mana. Si aucun sort ou que le mot retour est choisi,
	 * rouvre le menu des actions.
	 * L'action 'se défendre' bloque deux dégats de la prochaine attaque reçue.
	 * L'action 'récupérer' et l'action par défaut et régénère un pv et deux manas.
	 * @param action
	 * L'action choisie.
	 * @param opponent
	 * Le joueur ciblé. Doit être différent du joueur faisant l'action.
	 * @return
	 * Renvoie une petite phrase résumant l'action choisie.
	 * @throws YouAreTargetingYourselfDumbBoyException
	 * Si la cible est l'entité faisant l'action.
	 */
	public String isGoingToDo(FightAction action, Entity opponent)
			throws YouAreTargetingYourselfDumbBoyException {

		if (opponent == this) throw new YouAreTargetingYourselfDumbBoyException();

		switch(action) {

			case forfait:
				this.life = 0;
				return this.name + FightAction.forfait;

			case attaquer :
				opponent.isTarget(this.strength);
				return this.name + " attaque";
				
			case conjurer :
				return this.spellAction(opponent);

			case defendre :
				this.isReady = true;
				return this.name + FightAction.defendre;
				
			case recuperer :
			default :
				return this.recupAction();
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
	public String isTarget(int howMuch) {

		String result = this.name;

		if (howMuch > 0) {
			result += " prend" + howMuch + " dégats.";
			if (this.isReady) {
				this.isReady = false; 
				howMuch -= this.defense;
				if (howMuch < 0) howMuch = 0;
				result = this.name + " se protégeait, il a pris " + (howMuch - this.defense) + " dégats";
			}
		} else if (howMuch < 0) {
			result += " se soigne de " + howMuch + " PV";
		} else {
			result = "Fonction a terminer";
		}
		this.life -= howMuch;
		this.verifHPMana();
		return result;
	}

	/**
	 * Remet les pv à leur maximum s'ils l'ont dépassé après l'appel de 'isTarget'.
	 */
	public void verifHPMana() {
		if (this.life > this.maxLife) this.life = this.maxLife;
		if (this.mana > this.maxMana) this.mana = this.maxMana;
	}

	// Affichage
	public String toString() {
		System.out.println(this.name + " : " + this.life + "/" + this.maxLife + " PV");
		System.out.println(this.mana + "/" + this.maxMana + " mana");
		System.out.println("Sorts appris :");
		for (Spell spell : this.spells) {
			System.out.println("   " + spell);
		}
		return this.description;
	}
}

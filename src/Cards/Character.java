package Cards;

import java.util.Scanner;

public class Character extends Card {
	
	private int maxLife;
	private int life;
	private int maxMana;
	private int mana;
	private boolean isReady;
	private Action[] actions;
	private Spell[] spells;
	private int strength;
	
	public Character(String itName, String itDescription) {
		super(itName, itDescription);
		// TODO Auto-generated constructor stub
	}
	
	public Character(String itName, String itDescription, int itLife, int itMana, int itStrength, Action[] itActions, Spell[] itSpells) {
		super(itName, itDescription);
		this.maxLife = itLife;
		this.life = itLife;
		this.maxMana = itMana;
		this.mana = itMana;
		this.strength = itStrength;
		this.actions = itActions;
		this.spells = itSpells;
		this.isReady = false;
	}
	
	public int getLife() { return this.life; }
	public int getMana() { return this.mana; }
	public int getStrength() { return this.strength; }
	public Action[] getActions() { return this.actions; }
	public Spell[] getSpells() { return this.spells; }
	
	public void setLife(int thatMuch) {
		this.life += thatMuch;
	}
	
	public String isGoingToDo(Action action, Character opponent) {
		switch(action) {
			case forfait:
				this.life = 0;
				return this.name + Action.forfait;
			case attaquer :
				opponent.isTarget(this.strength);
				return this.name + " attaque";
				
			case conjurer :
				Scanner sc = new Scanner(System.in);
				System.out.println("Votre mana : " + this.mana);
				System.out.println("Quel sort voulez-vous lancer ?");
				for (int i=0; i < this.spells.length; i++) {
					System.out.println((i+1) + ": " + this.spells[i].getName() + ", " + this.spells[i].getValue() + " Mana");
				}
				System.out.println((this.spells.length+1) + ": Retour");
				int chosenOne = sc.nextInt();
				if (chosenOne > -1 && chosenOne < this.spells.length+1 && this.mana >=  this.spells[chosenOne].getValue()) {
					Object spell = this.spells[chosenOne];
					System.out.println("Vous avez choisi : " + ((Card) spell).getName());
					this.mana -= ((Spell) spell).getValue();
					if (spell instanceof DamageSpell) {
						((DamageSpell) spell).cast(opponent);
					} else if (spell instanceof SupportSpell) {
						((SupportSpell) spell).cast(this);
					}
					return this.name + Action.conjurer;
				}
				return "retour";
				
				
			case defendre :
				this.isReady = true;
				return this.name + Action.defendre;
				
			case recuperer :
			default :
				System.out.println(Action.recuperer);
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
	}
	
	public String isTarget(int howMuch) {
		String result = this.name;
		if (howMuch > 0) {
			result += " prend" + howMuch + " dégats.";
			if (this.isReady) {
				this.isReady = false; 
				howMuch -= 2;
				if (howMuch < 0) howMuch = 0;
				result = this.name + " se protégeait";
			}
		} else if (howMuch < 0) {
			result += " se soigne de " + howMuch + " PV";
		}
		this.life -= howMuch;
		this.verifHP();
		return result;
	}
	
	public void verifHP() {
		if (this.life > this.maxLife) {
			this.life = this.maxLife;
		} 
	}
	
	public String toString() {
		System.out.println(this.name + " : " + this.life + "/" + this.maxLife + " PV");
		System.out.println(this.mana + "/" + this.maxMana + " mana");
		System.out.println("Actions :");
		for (Action action : this.actions) {
			System.out.println("   " + action.getAction());
		}
		return this.description;
	}
}

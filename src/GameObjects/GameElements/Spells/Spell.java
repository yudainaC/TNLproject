package GameObjects.GameElements.Spells;

import Exceptions.NonValidManaException;
import GameObjects.GameEntities.Entity;
import GameObjects.Model;

/**
 * Le model plus spécifique aux sorts. Super-classe.
 */
public class Spell extends Model {

	protected int mana;

	// Constructeur
	public Spell(String itName, String itDescription, int itValue) throws NonValidManaException {
		super(itName, itDescription);
		if (itValue < 0) throw new NonValidManaException();
		this.mana = itValue;
	}

	// Getter
	public int getMana() { return this.mana; }

	// Affichage
	public String toString() {
		String theCard = this.name + " : " + this.mana + " Mana";
		System.out.println(theCard);
		return this.description;
	}
}

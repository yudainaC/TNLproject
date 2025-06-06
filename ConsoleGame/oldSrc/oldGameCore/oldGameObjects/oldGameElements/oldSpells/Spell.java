package gameCore.GameObjects.GameElements.Spells;

import exceptions.NonValidManaException;
import gameCore.GameObjects.GameEntities.Single.Entity;
import gameCore.GameObjects.Model;

/**
 * Le model plus spécifique aux sorts. Super-classe.
 */
public class Spell extends Model {

	protected int mana;

	// Constructeur
	public Spell(String itName, String itDescription, int itManaValue) throws NonValidManaException {
		super(itName, itDescription);
		if (itManaValue < 0) throw new NonValidManaException();
		this.mana = itManaValue;
	}

	// Getter
	public int getMana() { return this.mana; }

	// Affichage
	public String toString() {
		System.out.println(this.name + " : " + this.mana + " Mana");
		return this.description;
	}

	public void cast(Entity target) {}
}

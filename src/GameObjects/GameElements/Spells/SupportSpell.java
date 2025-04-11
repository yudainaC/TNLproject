package GameObjects.GameElements.Spells;

import Exceptions.NonValidManaException;
import GameObjects.GameEntities.Entity;

public class SupportSpell extends Spell {
	
	private final int healPoint;

	// Constructeur
	public SupportSpell(String itName, String itDescription, int itValue, int itHealPoint) throws NonValidManaException {
		super(itName, itDescription, itValue);
		this.healPoint = itHealPoint;
	}

	// Getter
	public int getHealPoint() { return this.healPoint; }

	/**
	 * Permet de modifier les paramètre de la cible en passant par sa classe et non ses attributs.
	 * @param target
	 * La cible visé par le sort.
	 * @return
	 * Renvoie l'appel de la fonction "isTarget" sur la cible avec en paramêtre le montant de PV restoré.
	 */
	public String cast(Entity target) {
		return target.isTarget(this.healPoint);
	}

	// Affichage
	public String toString() {
		String theCard = this.name + " : " + this.mana + " Mana";
		System.out.println(theCard);
		System.out.println("Se sort soigne " + this.healPoint + " PV.");
		return this.description;
	}
}

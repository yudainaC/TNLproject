package gameCore.GameObjects.GameElements.Spells;

import exceptions.NonValidManaException;
import gameCore.GameObjects.GameEntities.Single.Entity;

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
	 *
	 * @param target La cible visé par le sort.
	 * @return Renvoie l'appel de la fonction "isTarget" sur la cible avec en paramètre le montant de PV restoré.
	 */
	@Override
	public void cast(Entity target) {
		target.isTarget(this.healPoint);
	}

	// Affichage
	public String toString() {
		System.out.println(this.name + " : " + this.mana + " Mana");
		System.out.println("   Ce sort soigne " + -1*this.healPoint + " PV.");
		return this.description;
	}
}

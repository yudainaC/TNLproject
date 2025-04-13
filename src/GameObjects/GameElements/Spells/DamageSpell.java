package GameObjects.GameElements.Spells;

import Exceptions.NonValidManaException;
import GameObjects.GameEntities.Single.Entity;

/**
 * Sorts spécialisé dans les dégats.
 */
public class DamageSpell extends Spell {
	
	private final int damage;

	// Constructeur
	public DamageSpell(String itName, String itDescription, int itValue, int itDamage) throws NonValidManaException {
		super(itName, itDescription, itValue);
		this.damage = itDamage;
		// TODO Auto-generated constructor stub
	}

	// Getter
	public int getDamage() { return this.damage; }

	/**
	 * Permet de modifier les paramètre de la cible en passant par sa classe et non ses attributs.
	 * @param target
	 * La cible visé par le sort.
	 * @return
	 * Renvoie l'appel de la fonction "isTarget" sur la cible avec en paramêtre le montant de PV perdu.
	 */
	public String cast(Entity target) {
		return target.isTarget(this.damage);
	}

	// Affichage
	public String toString() {
		System.out.println(this.name + " : " + this.mana + " Mana");
		System.out.println("   Ce sort inflige " + this.damage + " dégats.");
		return this.description;
	}

}

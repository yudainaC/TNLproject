package GameObjects.GameElements.Spells;

import GameObjects.GameEntities.Entity;

public class SupportSpell extends Spell {
	
	private int healPoint;
	
	public SupportSpell(String itName, String itDescription, int itValue, int itHealPoint) {
		super(itName, itDescription, itValue);
		this.healPoint = itHealPoint;
	}
	
	public String cast(Entity target) {
		return target.isTarget(this.healPoint);
	}
}

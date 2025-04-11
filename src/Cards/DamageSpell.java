package Cards;

public class DamageSpell extends Spell{
	
	private int damage;
	
	public DamageSpell(String itName, String itDescription, int itValue, int itDamage) {
		super(itName, itDescription, itValue);
		this.damage = itDamage;
		// TODO Auto-generated constructor stub
	}
	
	public String cast(Character target) {
		return target.isTarget(this.damage);
	}
	
	public String toString() {
		String theCard = this.name + " : " + this.value + " Mana";
		System.out.println(theCard);
		System.out.println("Se sort inflige " + this.damage + " dégats.");
		return this.description;
	}

}

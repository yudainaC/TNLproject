package Cards;

public class Spell extends Card {

	protected int value;
	
	public Spell(String itName, String itDescription) {
		super(itName, itDescription);
		// TODO Auto-generated constructor stub
	}

	public Spell(String itName, String itDescription, int itValue) {
		super(itName, itDescription);
		this.value = itValue;
	}
	
	public int getValue() { return this.value; }
	
	public String cast(Character opponent) {
		return "";
	}
	
	public String toString() {
		String theCard = this.name + " : " + this.value + " Mana";
		System.out.println(theCard);
		return this.description;
	}
}

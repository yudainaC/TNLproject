package Cards;

public enum Action {
	attaquer, defendre, recuperer, conjurer;
	
	public String getAction() {
		switch(this) {
			case attaquer :
				return "Attaquer";
			case conjurer:
				return "Lancer un sort";
			case defendre :
				return "Se défendre";
			case recuperer :
			default :
				return "Récupérer";
		}
	}
	
	public String toString() {
		switch(this) {
			case attaquer :
				return " a perdu 2 HP";
			case conjurer :
				return " lance un sort";
			case defendre :
				return " se defend contre la prochaine attaque";
			case recuperer :
			default :
				return " essaie de se soigner";
		}
	}
}

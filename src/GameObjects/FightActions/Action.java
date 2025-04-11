package GameObjects.FightActions;

public enum Action {
	forfait, attaquer, defendre, recuperer, conjurer;
	
	public String getAction() {
		switch(this) {
			case forfait :
				return "Déclarer forfait";
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
			case forfait :
				return " a déclarer forfait";
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

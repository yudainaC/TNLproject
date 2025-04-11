package GameObjects.FightActions;

/**
 * Les différentes actions réalisable pendant un combat
 */
public enum FightAction {
	forfait, attaquer, defendre, recuperer, conjurer;

	// Getter
	public String getAction() {
        return switch (this) {
            case forfait -> "Déclarer forfait";
            case attaquer -> "Attaquer";
            case conjurer -> "Lancer un sort";
            case defendre -> "Se défendre";
            default -> "Récupérer";
        };
	}

	// Affichage
	public String toString() {
        return switch (this) {
            case forfait -> " a déclarer forfait";
            case attaquer -> " a perdu 2 HP";
            case conjurer -> " lance un sort";
            case defendre -> " se defend contre la prochaine attaque";
            default -> " essaie de se soigner";
        };
	}
}

package gameCore.GameFight;

/**
 * Les différentes actions réalisables pendant un combat
 */
public enum FightAction {
    forfeit, attack, defend, recover, conjure, use;

	// Getter
	public String getAction() {
        return switch (this) {
            case forfeit -> "Déclarer forfait";
            case attack -> "Attaquer";
            case conjure -> "Lancer un sort";
            case defend -> "Se défendre";
            case use -> "Utiliser un objet";
            default -> "Récupérer";
        };
	}

	// Affichage
	public String toString() {
        return switch (this) {
            case forfeit -> " a déclarer forfait";
            case attack -> " a perdu 2 HP";
            case conjure -> " lance un sort";
            case defend -> " se defend contre la prochaine attaque";
            case use -> " cherche un objet";
            default -> " essaie de se soigner";
        };
	}
}

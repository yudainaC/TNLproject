package gameCore.GameFight;

import java.util.Random;

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

    public static FightAction[] getFightActions() {
        return new FightAction[]{forfeit, attack, defend, recover, conjure, use};
    }

    public static FightAction getRandAction(int n) {
        Random generator = new Random();
        FightAction[] monsterActions = {attack, defend, conjure, recover};
        return monsterActions[generator.nextInt(0, n+1)];
    }

	// Affichage
	public String toString() {
        return switch (this) {
            case forfeit -> " a déclarer forfait";
            case attack -> " attaque";
            case conjure -> " lance un sort";
            case defend -> " se defend contre la prochaine attaque";
            case use -> " cherche un objet";
            default -> " essaie de se soigner";
        };
	}
}

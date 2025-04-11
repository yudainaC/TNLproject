package GameObjects.GameEntities;

import Exceptions.NonValidLifeException;
import Exceptions.NonValidManaException;
import Exceptions.NonValidStrengthException;
import GameObjects.GameElements.Spells.Spell;

import java.util.Random;

/**
 * Sous-classe de Entity, définie les monstre croisé par les héros.
 */
public class Monster extends Entity {
    protected int xp;

    /**
     * Constructeur
     * Définit les valeurs par défaut des attribut :
     * xp = 0
     *
     * Voir le constructeur d'Entity pour le reste.
     */
    public Monster(String itName, String itDescription, int itLife, int itMana, int itStrength, Spell[] itSpells, int theXp)
            throws NonValidLifeException, NonValidManaException, NonValidStrengthException {
        super(itName, itDescription, itLife, itMana, itStrength, itSpells);
        this.xp=theXp;

    }

    /**
     * fonction qui permet de randomiser l'xp obtenu entre les personnages
     * @param bonusXp
     * L'experiance gagné après avoir abattu le monstre. Calculé aléatoirement.
     * @return
     * Retourne l'xp obtenu par chaque perso.
     */
    public int GetXp(Random bonusXp){
        int xpAdd = bonusXp.nextInt(10);
        bonusXp.ints();
        xp =xp + xpAdd;
        return xp;
    }

}

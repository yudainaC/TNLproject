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
     * fonction qui permet de calculer aléatoirement l'xp obtenu en tuant un monstre.
     * @return
     * Retourne l'xp que le monstre lache.
     */
    public int getXp(){
        Random bonusXp = new Random();
        int xpAdd = bonusXp.nextInt(10);
        bonusXp.ints();
        return this.xp + xpAdd;
    }

    /**
     * fonction attribuant l'xp lacher par le monstre au(x) héro(s) l'ayant vaincu.
     * @param killer
     * Un des héro ayant participé a l'élimination.
     */
    public void getKilled(Hero killer) {
        int xpGain = this.getXp();
        System.out.println("Vous avez tué 1 " + this.name + ", vous gagnez " + xpGain);
        killer.verifLevel(xpGain);
    }

}

package gameCore.GameObjects.GameEntities.Single;

import exceptions.NonValidLifeException;
import exceptions.NonValidManaException;
import exceptions.NonValidStrengthException;
import gameCore.GameFight.Fight;
import gameCore.GameFight.FightAction;
import gameCore.GameObjects.GameElements.Spells.DamageSpell;
import gameCore.GameObjects.GameElements.Spells.Spell;

import java.util.Random;
import java.util.Scanner;

/**
 * Sous-classe de Entity, définie les monstres croisés par les héros.
 */
public class Monster extends Entity {
    protected int xp;


    /**
     * Constructeur
     * Définit les valeurs par défaut des attributs :
     * xp = 0
     *
     * Voir le constructeur d'Entity pour le reste.
     */
    public Monster(String itName, String itDescription, int itLife, int itMana, int itStrength, Spell[] itSpells, int itXp, int itDefense, int itSpeed)
            throws NonValidLifeException, NonValidManaException, NonValidStrengthException {
        super(itName, itDescription, itLife, itMana, itStrength, itSpells, itDefense, itSpeed);
        this.xp=itXp;
    }

    /**
     * Fonction qui permet de calculer aléatoirement l'xp obtenu en tuant un monstre.
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
     * Fonction attribuant l'xp lacher par le monstre au(x) héro(s) l'ayant vaincu.
     * @param killer
     * Un des héros ayant participé à l'élimination.
     */
    public void getKilled(Hero killer) {
        int xpGain = this.getXp();
        System.out.println("Vous avez tué 1 " + this.name + ", vous gagnez " + xpGain);
        killer.verifLevel(xpGain);
    }

    /**
     * Voir méthode dans Entité. La différence avec celle de 'Entity' est le choix des cibles.
     */
    @Override
    public String spellAction(Fight fight) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Votre mana : " + this.mana);
        System.out.println("Quel sort voulez-vous lancer ?");
        if (this.spells[0] != null) {
            for (int i = 0; i < this.spells.length; i++) {
                System.out.println((i + 1) + ": " + this.spells[i].getName() + ", " + this.spells[i].getMana() + " Mana");
            }
        }
        System.out.println((this.spells.length+1) + ": Retour");
        int chosenOne = sc.nextInt();
        if (chosenOne > 0 && chosenOne < this.spells.length+1 && this.mana >= this.spells[chosenOne-1].getMana()) {
            Spell spell = this.spells[chosenOne-1];
            System.out.println("Vous avez choisi : " + spell.getName());
            this.mana -= spell.getMana();
            Entity target;
            if (spell instanceof DamageSpell) {
                target = whosTargeted(true, fight);
            } else { //if (spell instanceof SupportSpell) {
                target = whosTargeted(false, fight);
            }
            boolean isAlive = true;
            if (target != null) isAlive = spell.cast(target);
            if (!isAlive) fight.hasDied(target);
            return this.name + FightAction.conjurer;
        }
        return "retour";
    }

    // Affichage
    public String isGoingToDo(FightAction action, Fight fight) {

        switch (action) {
            case forfait -> {
                this.life = 0;
                fight.hasDied(this);
                return this.name + FightAction.forfait;
            }
            case attaquer -> {
                Entity opponent = whosTargeted(true, fight);
                if (opponent == null) return "retour";
                boolean isAlive = opponent.isTarget(this.strength);
                if (isAlive) return this.name + " attaque";
                else {
                    fight.hasDied(opponent);
                    return this.name + " a tué " + opponent.getName();
                }
            }
            case conjurer -> {
                return this.spellAction(fight);
            }
            case defendre -> {
                this.isReady = true;
                return this.name + FightAction.defendre;
            }
            default -> {
                return this.recupAction();
            }
        }
    }

}

package gameCore.GameObjects.GameEntities.Single;

import exceptions.NonValidLifeException;
import exceptions.NonValidManaException;
import exceptions.NonValidStrengthException;
import gameCore.GameFight.Fight;
import gameCore.GameFight.FightAction;
import gameCore.GameObjects.GameElements.Items.Item;
import gameCore.GameObjects.GameElements.Spells.DamageSpell;
import gameCore.GameObjects.GameElements.Spells.Spell;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


/**
 * Sous-classe de Entity, définie les monstres croisés par les héros.
 */
public class Monster extends Entity {
    protected int xp;
    protected Item[] loot;

    /**
     * Constructeur
     * Définit les valeurs par défaut des attributs :
     * xp = 0
     * Voir le constructeur d'Entity pour le reste.
     */
    public Monster(String itName, String itDescription, int itLife, int itMana, int itStrength, List<Spell> itSpells,
                   int itXp, int itDefense, int itSpeed, Item[] itLoot)
            throws NonValidLifeException, NonValidManaException, NonValidStrengthException {

        super(itName, itDescription, itLife, itMana, itStrength, itSpells, itDefense, itSpeed);

        this.xp = itXp;
        this.loot = itLoot;
    }

    public List<Item> getLoot() {
        return List.of(this.loot);
    }

    /**
     * Fonction qui permet de calculer aléatoirement l'expérience obtenue en tuant un monstre.
     * @return
     * Retourne l'expérience que le monstre lâche.
     */
    public int getXp(){
        Random bonusXp = new Random();
        int xpAdd = bonusXp.nextInt(10);
        bonusXp.ints();
        return this.xp + xpAdd;
    }

    /**
     * Fonction attribuant l'expérience lâchée par le monstre au(x) héro(s) l'ayant vaincu.
     * @param killer
     * Un des héros ayant participé à l'élimination.
     */
    public void getKilled(Hero killer) {
        int xpGain = this.getXp();
        System.out.println("Vous avez tué 1 " + this.name + ", " + killer.getName() + " a gagné " + xpGain + " XP");
        killer.verifyLevel(xpGain);
    }

    /**
     * Voir méthode dans Entité. La différence avec celle de 'Entity' est le choix des cibles.
     */
    @Override
    public String spellAction(Fight fight) {

        if (!(this.spells == null)) {
            Random generator = new Random();
            int chosenOne = generator.nextInt(spells.size());
            System.out.println(chosenOne);
            if (this.mana >= this.spells.get(chosenOne).getMana()) {
                Spell spell = this.spells.get(chosenOne);
                this.mana -= spell.getMana();
                Entity target;
                if (spell instanceof DamageSpell) {
                    target = whoIsTargeted(false, fight);
                } else { //if (spell instanceof SupportSpell) {
                    target = whoIsTargeted(true, fight);
                }
                boolean isAlive = true;
                if (target != null) isAlive = spell.cast(target);
                if (!isAlive) fight.hasDied(target);
                return this.name + FightAction.conjure;
            }
        }
        return "retour";
    }

    /**
     * Permet de choisir une cible. Affiche toutes les Entités de l'équipe adverse en cas d'action négatives,
     * et les membres de l'équipe (this non compris) alliée en cas d'action positive. Renvoie null si la cible
     * est déjà à terre ou incorrecte.
     * @param allies
     * Un booléen, vrai s'il faut viser un allié, faux sinon.
     * @param fight
     * Le combat actuel.
     * @return
     * La cible.
     */
    public Entity whoIsTargeted(Boolean allies, Fight fight) {

        ArrayList<Entity> groupTarget = fight.getHeroes().getGroup();
        if (allies) {
            groupTarget = fight.getOpponents().getGroup();
            int index = 0;
            for (int i = 0; i < groupTarget.size(); i++) if (groupTarget.get(i) == this) index = i;
            groupTarget.remove(index);
        }

        Random generator = new Random();
        int chosenOne = generator.nextInt(groupTarget.size());

        if (groupTarget.get(chosenOne).life > 0) {
            Entity target = groupTarget.get(chosenOne);
            System.out.println("Vous avez ciblé : " + target.getName());
            return target;
        }
        return null;
    }

    public String isGoingToDo(Fight fight) {

        int n = 3;
        if (this.life == this.maxLife && this.mana == this.maxMana) n = 2;
        FightAction action = FightAction.getRandAction(n);
        while (action == FightAction.conjure && this.spells == null) action = FightAction.getRandAction(n);

        switch (action) {
            case attack -> {
                Entity opponent = whoIsTargeted(false, fight);
                if (opponent == null) return "retour";
                boolean isAlive = opponent.isTarget(this.strength);
                if (isAlive) return this.name + " attaque" + opponent.getName();
                else {
                    fight.hasDied(opponent);
                    return this.name + " a tué " + opponent.getName();
                }
            }
            case conjure -> {
                return this.spellAction(fight);
            }
            case defend -> {
                this.isReady = true;
                return this.name + FightAction.defend;
            }
            default -> {
                return this.recoveryAction();
            }
        }
    }
    // Affichage

}

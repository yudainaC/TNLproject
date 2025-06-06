import exceptions.*;
import gameCore.GameFight.Fight;
import gameCore.GameObjects.GameElements.Items.Weapon;
import gameCore.GameObjects.GameElements.Skills.Skills;
import gameCore.GameObjects.GameEntities.Group.Group;
import gameCore.GameObjects.GameEntities.Group.MobGroup;
import gameCore.GameObjects.GameEntities.Single.Entity;
import gameCore.GameObjects.GameEntities.Single.Hero;
import gameCore.GameObjects.GameEntities.Single.Monster;
import gameCore.Player;
import gameFactory.Factory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Test {
    public static void main(String[] args) throws NotASkillsException, NonValidLifeException, NonValidManaException, NonValidStrengthException, IOException, TeamIsFullException, YouAreFightingYourselfDumbPlayerException, YouAreTargetingYourselfDumbBoyException, NonValidValueException, NonValidWeightException, NotABonusException, NotAnItemException {

        /* Player.addSkill(Skills.natation);
        Player.addSkill(Skills.artisanat);
        Player.addSkill(Skills.cavernologie);
        Player.addSkill(Skills.forceSurhumaine);
        Player.addSkill(Skills.herboriste);
        System.out.println(Player.getPlayerSkills()); */

        Monster blob = Factory.parseMonster().get("blob");
        Monster mage = Factory.parseMonster().get("sorcier");
        System.out.println(mage.getSpells());
        Monster blob1 = Factory.parseMonster().get("blob");
        Monster mage1 = Factory.parseMonster().get("sorcier");

        Hero bobby = Factory.parseHeroSimple().get("Bobby le premier");
        Hero conny = Factory.parseHeroSimple().get("Conny le premier");

        Monster[] monsters = new Monster[]{mage, blob};
        MobGroup mobGroup1 = new MobGroup(monsters);
        monsters = new Monster[]{mage1, blob1};
        MobGroup mobGroup2 = new MobGroup(monsters);

        Weapon sword = (Weapon) Factory.parseItem().get("Epée courte");
        Player.recruit(bobby);
        Player.recruit(conny);
        Player.putInMyTeam(bobby);
        Player.putInMyTeam(conny);

        Player.getInventory().addItem(Factory.parseItem().get("Potion de force"));
        Player.getInventory().addItem(Factory.parseItem().get("Potion de soin"));
        Player.getInventory().addItem(sword);
        bobby.equipWeapon(sword);

        Fight firstFight = new Fight(Player.getTeam(), mobGroup1);
        firstFight.fullTeamFight();
        /*Fight secondFight = new Fight(Player.getTeam(), monsters1);
        System.out.println(secondFight.fullTeamFight());
        for (Entity hero : Player.getTeam().getGroup()) {
            System.out.println(((Hero) hero).getLevel());
        }*/

    }
}

import exceptions.*;
import gameCore.GameFight.Fight;
import gameCore.GameObjects.GameElements.Skills.Skills;
import gameCore.GameObjects.GameEntities.Group.Group;
import gameCore.GameObjects.GameEntities.Single.Entity;
import gameCore.GameObjects.GameEntities.Single.Hero;
import gameCore.GameObjects.GameEntities.Single.Monster;
import gameCore.Player;
import gameFactory.Factory;

import java.io.IOException;
import java.util.Arrays;

public class Test {
    public static void main(String[] args) throws NotASkillsException, NonValidLifeException, NonValidManaException, NonValidStrengthException, IOException, TeamIsFullException, YouAreFightingYourselfDumbPlayerException, YouAreTargetingYourselfDumbBoyException {

        Player player = new Player();

        /*player.addSkill(Skills.natation);
        player.addSkill(Skills.artisanat);
        player.addSkill(Skills.cavernologie);
        player.addSkill(Skills.forceSurhumaine);
        player.addSkill(Skills.herboriste);
        System.out.println(player.getPlayerSkills());*/

        Monster blob = Factory.parseMonster().get("blob");
        Monster mage = Factory.parseMonster().get("sorcier");
        Monster blob1 = Factory.parseMonster().get("blob");
        Monster mage1 = Factory.parseMonster().get("sorcier");

        Hero bobby = Factory.parseHeroSimple().get("Bobby le premier");
        Hero conny = Factory.parseHeroSimple().get("Conny le premier");

        Group monsters = new Group();
        monsters.addToTeam(mage);
        monsters.addToTeam(blob);
        Group monsters1 = new Group();
        monsters.addToTeam(mage1);
        monsters.addToTeam(blob1);

        player.recruit(bobby);
        player.recruit(conny);
        player.putInMyTeam(bobby);
        player.putInMyTeam(conny);

        Fight firstFight = new Fight(player.getTeam(), monsters);
        System.out.println(firstFight.fullTeamFight());
        for (Entity hero : player.getTeam().getGroup()) {
            System.out.println(((Hero) hero).getLevel());
        }
        Fight secondFight = new Fight(player.getTeam(), monsters1);
        System.out.println(secondFight.fullTeamFight());
        for (Entity hero : player.getTeam().getGroup()) {
            System.out.println(((Hero) hero).getLevel());
        }
    }
}

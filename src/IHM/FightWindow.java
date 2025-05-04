package IHM;

import exceptions.*;
import gameCore.GameFight.Fight;
import gameCore.GameObjects.GameEntities.Group.MobGroup;
import gameCore.GameObjects.GameEntities.Single.Entity;
import gameCore.GameObjects.GameEntities.Single.Hero;
import gameCore.GameObjects.GameEntities.Single.Monster;
import gameCore.Player;
import gameFactory.Factory;

import javax.swing.*;
import java.io.IOException;
import java.util.List;

public class FightWindow extends JFrame {
    public static FightPanel panel;

    public FightWindow(List<Entity> fighters) {

        setTitle("Combat !");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        panel = new FightPanel(fighters);
        add(panel);

        setVisible(true);
    }

    public static void main(String[] args) throws NonValidLifeException, NotASkillsException, NonValidManaException, NonValidStrengthException, IOException, NotAnItemException, TeamIsFullException, YouAreTargetingYourselfDumbBoyException {

        Monster blob = Factory.parseMonster().get("blob");
        Monster mage = Factory.parseMonster().get("sorcier");

        Hero bobby = Factory.parseHeroSimple().get("Bobby le premier");
        Hero conny = Factory.parseHeroSimple().get("Conny le premier");

        Monster[] monsters = new Monster[]{mage, blob};
        MobGroup mobGroup1 = new MobGroup(monsters);

        Player.recruit(bobby);
        Player.recruit(conny);
        Player.putInMyTeam(bobby);
        Player.putInMyTeam(conny);

        Fight fight1 = new Fight(Player.getTeam(), mobGroup1);
        new FightWindow(fight1.getOrder());
        fight1.setFightPanel(panel);

        fight1.fullTeamFight();

    }
}


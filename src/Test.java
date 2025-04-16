import exceptions.NotASkillsException;
import gameCore.GameObjects.GameElements.Skills.Skills;
import gameCore.Player;

public class Test {
    public static void main(String[] args) throws NotASkillsException {
        Player player = new Player();
        player.addSkill(Skills.natation);
        player.addSkill(Skills.artisanat);
        player.addSkill(Skills.cavernologie);
        player.addSkill(Skills.forceSurhumaine);
        player.addSkill(Skills.herboriste);
        System.out.println(player.getPlayerSkills());
    }
}

package gameCore.GameObjects.GameElements.Skills.SkillTree;


import gameCore.GameObjects.GameElements.Skills.Skills;


import java.util.HashSet;
import java.util.Set;

public class Skill {
    protected  String name;
    protected Set<Skills> Prerequis;
    protected final int cost;

    public Skill(String theName, int theCost){
        this.Prerequis= new HashSet<>();
        this.name=theName;
        this.cost=theCost;
    }

    public boolean addCondition(Skills skills){return this.Prerequis.add(skills);}
    public boolean removeCondition(Skills skills){return this.Prerequis.remove(skills);}


    /*

     */

}

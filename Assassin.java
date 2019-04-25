import java.util.*;
public class Assassin extends Novice{
    private ArrayList<String> skills;
    
    public Assassin(Novice _novice){
        super(_novice.name);
        super.level = _novice.level;
        super.maxExp = _novice.maxExp;
        super.monstersHaveAttacked = _novice.monstersHaveAttacked;
        super.bag = _novice.bag;
        super.maxHp = _novice.maxHp + 15;
        super.hp = super.maxHp;
        super.attackDamage = _novice.attackDamage + 20;
        super.job = "Assassin";
        skills = new ArrayList<String>();
        skills.add("SonicBlow");
        skills.add("DoubleBladeForce");
        skills.add("VenomSplasher");
    }

    public ArrayList<String> getSkills(){
        return skills;
    }

    public String getPiture(){
        return "assassin.jpg";
    }
}
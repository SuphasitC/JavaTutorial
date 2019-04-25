import java.util.*;
public class Mage extends Novice{
    private ArrayList<String> skills;
    
    public Mage(Novice _novice){
        super(_novice.name);
        super.level = _novice.level;
        super.maxExp = _novice.maxExp;
        super.monstersHaveAttacked = _novice.monstersHaveAttacked;
        super.bag = _novice.bag;
        super.maxHp = _novice.maxHp + 10;
        super.hp = super.maxHp;
        super.attackDamage = _novice.attackDamage + 25;
        super.job = "Mage";
        skills = new ArrayList<String>();
        skills.add("SoulStrike");
        skills.add("FireBlot");
        skills.add("LightningBolt");
    }

    public ArrayList<String> getSkills(){
        return skills;
    }  

    public String getPiture(){
        return "mage.jpg";
    }
}
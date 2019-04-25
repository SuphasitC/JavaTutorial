import java.util.*;
public class Archer extends Novice{
    private ArrayList<String> skills;
    
    public Archer(Novice _novice){
        super(_novice.name);
        super.level = _novice.level;
        super.maxExp = _novice.maxExp;
        super.monstersHaveAttacked = _novice.monstersHaveAttacked;
        super.bag = _novice.bag;
        super.maxHp = _novice.maxHp + 20;
        super.hp = super.maxHp;
        super.attackDamage = _novice.attackDamage + 15;
        super.job = "Archer";
        skills = new ArrayList<String>();
        skills.add("ElementalArrows");
        skills.add("ChargeArrow");
        skills.add("AnkleSnare");
    }

    public ArrayList<String> getSkills(){
        return skills;
    }  

    public String getPiture(){
        return "archer.jpg";
    }
}
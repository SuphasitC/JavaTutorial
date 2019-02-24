import java.io.*;
import java.util.*;

public class Archer extends Novice{
    private ArrayList<String> skill;

    public Archer(Novice changeClass){
        super();
        super.setAll(changeClass);
        skill = new ArrayList<String>();
        skill.add("ElementalArrows");
        skill.add("ChargeArrow");
        skill.add("AnkleSnare");
    }

    public int attackSkill(String skills){
        if(skills.equals("ElementalArrows")){
            return 1000;
        }
        else if(skills.equals("ChargeArrow")){
            return 1500;
        }
        else if(skills.equals("AnkleSnare")){
            return 2000;
        }
        return 0;
    }

    public ArrayList<String> getSkill(){
        return skill;
    }

}
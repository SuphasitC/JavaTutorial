import java.io.*;
import java.util.*;

public class Mage extends Novice{
    private ArrayList<String> skill;

    public Mage(Novice changeClass){
        super();
        super.setAll(changeClass);
        skill = new ArrayList<String>();
        skill.add("SoulStrike");
        skill.add("FireBlot");
        skill.add("LightningBolt");
    }

    public int attackSkill(String skills){
        if(skills.equals("SoulStrike")){
            return 1000;
        }
        else if(skills.equals("FireBlot")){
            return 1500;
        }
        else if(skills.equals("LightningBolt")){
            return 2000;
        }
        return 0;
    }

    public ArrayList<String> getSkill(){
        return skill;
    }

}
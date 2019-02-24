import java.io.*;
import java.util.*;

public class Assassin extends Novice{
    private ArrayList<String> skill;

    public Assassin(Novice changeClass){
        super();
        super.setAll(changeClass);
        skill = new ArrayList<String>();
        skill.add("SonicBlow");
        skill.add("DoubleBladeForce");
        skill.add("VenomSplasher");
    }

    public int attackSkill(String skills){
        if(skills.equals("SonicBlow")){
            return 1000;
        }
        else if(skills.equals("DoubleBladeForce")){
            return 1500;
        }
        else if(skills.equals("VenomSplasher")){
            return 2000;
        }
        return 0;
    }

    public ArrayList<String> getSkill(){
        return skill;
    }
    
}
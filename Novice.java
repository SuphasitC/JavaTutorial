import java.io.*;
import java.util.*;

public class Novice {
    private int level;
    private int exp;
    private int hp;
    private int maxHp;
    private int maxExp;
    private Bag bag;
    
    public Novice(){                    
        level = 1;
        exp = 0;
        maxExp = 50;
        hp = 100; 
        maxHp = 100;
        bag = new Bag(40);
        // skill = new ArrayList<String>();
        // skill.add("NormalAttack");
    }
    public void setAll(Novice changeClass){
        level = changeClass.getLevel();
        exp = changeClass.getExp();
        hp = changeClass.getHp() + 150;
        maxHp = changeClass.getMaxHp() + 150;
        maxExp = changeClass.getMaxExp();
        bag = changeClass.getBag();
    }

    public void levelUp(){
        level++;
        maxExp = level * 50;
        hp = maxHp;
        exp = 0;
        System.out.println("***********--===*****Level Up!*****===--***********");
        System.out.println("Now your level is " + level);
    }

    public Bag getBag(){
        return bag;
    }

    public int getMaxHp(){
        return maxHp;
    }

    public int getHp(){
        return hp;
    }

    public int getLevel(){
        return level;
    }

    public int getExp(){
        return exp;
    }

    public int getMaxExp(){
        return maxExp;
    }

    public void attacked(int monsterATK){
        hp -= monsterATK;
    }

    public void receiveExp(int expFromMonster){
        exp += expFromMonster;
    }

    public void usePotion(String job){
        int potionInBag = bag.getPotion();
        int factor = 100;
        Item item = new Potion();
        if(potionInBag > 0){
            if(job.equals("Novice")){
                factor = 0;
            }
            if((hp + ((Potion)item).getIncreaseHp() < maxHp)){  
                System.out.println("You drink the potion, HP + " + (((Potion)item).getIncreaseHp() + factor));
                bag.minusPotionInBag();
                hp += ((Potion)item).getIncreaseHp() + factor;
            }
            else{
                System.out.println("You drink the potion, HP + " + (maxHp - hp));
                bag.minusPotionInBag();
                hp = maxHp;
            }
        }
        else {
            System.out.println("You not have enough potion.");
        }
    }

    public void useExpCard(){
        int expCardInBag = bag.getExpCard();
        Item item = new ExpCard();
        if(expCardInBag > 0){
            if((exp + ((ExpCard)item).getIncreaseExp()) < maxExp){  
                System.out.println("You use expcard, exp + " + ((ExpCard)item).getIncreaseExp());
                bag.minusExpCardInBag();
                exp += ((ExpCard)item).getIncreaseExp();
            }
            else if((exp + ((ExpCard)item).getIncreaseExp()) >= maxExp){
                System.out.println("You use expcard, exp + " + (maxExp - ((ExpCard)item).getIncreaseExp()));
                bag.minusExpCardInBag();
                levelUp();
            }
        }
        else {
            System.out.println("You not have enough expcard.");
        }
    }

    public void setHpAfterDie(){
        hp = 100;
    }

    public void setExpAfterDie(int expDecrease){
        if(exp - expDecrease > 0){
            exp -= expDecrease;
        }
        else{
            exp = 0;
        }
    }

    public ArrayList<String> getSkill(){
        if(level >= 3)
            return getSkill();
        else
            return new ArrayList<String>();
    }

    public int attackSkill(String skill){
        if(level >= 3)
            return attackSkill(skill);
        else
            return 0;
    }
}
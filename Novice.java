import java.io.*;
import java.util.Scanner;
import java.util.Random;

public class Novice {
    private String name;
    private int atkDamage;
    private int level;
    private int exp;
    private int hp;
    private int maxEXP;
    public Bag bag;
    
    public Novice(String _name){                    
        level = 1;
        exp = 0;
        maxEXP = 50;
        hp = 100;
        name = _name;
        bag = new Bag(40);
    }

    public void levelUp(){      //method of level up
        this.level++;
        this.maxEXP = this.level * 50;
        this.hp = 100;
        this.exp = 0;
    }

    public void attack() {                //Bot to get level up automatically
        int monsterATK;
        int expFromMonster;
        int attackTime;
        int numberOfMonsters;
        Scanner input = new Scanner(System.in);

        System.out.print("Enter number of monster: ");
        numberOfMonsters = input.nextInt();

        while(numberOfMonsters != 0){       //How many monsters do you want to get your Avatar attack?
            attackTime = (int)(Math.random() * 1999 + 1);
            monsterATK = (int)(Math.random() * 49 + 1);
            expFromMonster = (int)(Math.random() * 99 + 1);
            this.hp -= monsterATK;
            System.out.println("---------------------------------------------------");
            System.out.println("Attacking...");

            try{                //delay of attacking monsters
                Thread.sleep(attackTime);
            }
            catch(InterruptedException ex){
                Thread.currentThread().interrupt();
            }

            if(this.hp > 0){            //if your Avatar is not die
                System.out.println("Monster has been slain.");
                System.out.println("EXP gain : " + "+" + expFromMonster);
                this.exp += expFromMonster;

                if(this.exp >= this.maxEXP)
                    System.out.println("EXP : " + this.maxEXP + "/" + this.maxEXP);
                else   
                    System.out.println("EXP : " + this.exp + "/" + this.maxEXP);

                System.out.println("hp : " + this.hp);
                bag.putItemToBag();
                System.out.println("You got " + bag.items.get(bag.slotUse - 1).itemName);
                
                if(this.hp <= 40){       //if your Avatar's HP lower than 40 , take the potion to increase HP
                    potion();
                }
                
                bag.checkSlotLeft();
                
                try{
                    Thread.sleep(1000);
                }
                catch(InterruptedException ex){
                    Thread.currentThread().interrupt();
                }
                
                if(this.exp >= this.maxEXP){        //if your Avatar's level is up
                    this.levelUp();
                    System.out.println("***********--===*****Level Up!*****===--***********");
                    System.out.println("Now your level is " + this.level);
                }
            }
            else{               // your Avatar is die
                this.die();
                break;          //break the loop of bot
            }

            try{        //pause the process for getting player look at the Avatar's status after killing the monster
                Thread.sleep(1500);
            }
            catch(InterruptedException ex){
                Thread.currentThread().interrupt();
            }
            numberOfMonsters--;
        }
    }

    public void potion(){        //method of getting heal
        int potionInBag = bag.getPotion();
        if(potionInBag > 0){
            if((this.hp + 60) < 100){       //if your Avatar's HP doesn't lower than 30
                System.out.println("You drink the potion, HP + 60");
                bag.usePotion();
                hp += 60;
            }
            else{
                System.out.println("You drink the potion, HP + " + (100 - this.hp));
                bag.usePotion();
                hp = 100;
            }
        }
        else {
            System.out.println("You not have enough potion.");
        }
    }

    private void die(){         // your Avatar is die
        int expDecrease;

        System.out.println("Your character die, please wait for 10 seconds.");
        expDecrease = level * 5;
       
        if((this.exp - expDecrease) > 0)    //minus exp
            this.exp -= expDecrease;
        else
            this.exp = 0;
       
        System.out.println("Your EXP decrease for " + expDecrease);

        for(int i = 10; i > 0 ; i--){       //wait for 10 seconds to respawn
            System.out.println(i + "...");
            try{
                Thread.sleep(1000);
            }
            catch(InterruptedException ex){
                Thread.currentThread().interrupt();
            }
        }
        
        System.out.println("Your HP is recovered!");
        this.hp = 100;
    }

    public void status() {             //show Avatar's Status
        System.out.println("***************************************************");    
        System.out.println("------------------CharacterStatus------------------");
        System.out.println("***************************************************");
        System.out.println("Name : " + this.name);
        System.out.println("HP : " + this.hp);
        System.out.println("Level : " + this.level);
        System.out.println("EXP : " + this.exp + "/" + this.maxEXP);
        System.out.println("***************************************************");

        try{
            Thread.sleep(3000);
        }
        catch(InterruptedException ex){
            Thread.currentThread().interrupt();
        }
    }
}
import java.io.*;
import java.util.*;

public class MainGame {
    private boolean gameContinue;
    private Player player;
    private Assassin assassin;
    private Mage mage;
    private Archer archer;

    public MainGame(){
        gameContinue = true;
        player = new Player("Em");
    }

    private Item dropItem(){
        int randomIdxOfItem = (int)(Math.random() * 2);
        String nameOfItemDrop;
        if(randomIdxOfItem == 0){
            nameOfItemDrop = "Potion";
        }
        else{
            nameOfItemDrop = "ExpCard";
        }
        Item itemDrop = new Item(nameOfItemDrop);
        return itemDrop;
    }

    public void showGameInterface(){
        Scanner scan = new Scanner(System.in);
        String S = "S",B = "B", P = "P", Q = "Q", A = "A", D = "D", C = "C", I = "I", E = "E", activity, bagEvent;
        int toChangeClass;
        while(gameContinue){

            //ChangeClass
            while(player.getNovice().getLevel() >= 3 && player.getPlayerClass().equals("Novice")){
                System.out.println("===================================================");
                System.out.println("Your level is " + player.getNovice().getLevel());
                System.out.println("Time to change the class");
                System.out.println("===================================================");
                System.out.println("------------- What do you want to be? -------------");
                System.out.println("===================================================");
                System.out.println("(1)Assassin");
                System.out.println("(2)Mage");
                System.out.println("(3)Archer");
                System.out.print("Enter number to change the class : ");
                toChangeClass = scan.nextInt();
                if(toChangeClass == 1){
                    player.changeClass("Assassin");
                }
                else if(toChangeClass == 2){
                    player.changeClass("Mage");
                }
                else if(toChangeClass == 3){
                    player.changeClass("Archer");
                }
                else
                    System.out.println("Please Enter other character.");
                if(!player.getPlayerClass().equals("Novice")){
                    System.out.println("Now your class is " + player.getPlayerClass());
                    try{Thread.sleep(2000);}catch(InterruptedException ex){Thread.currentThread().interrupt();}
                }
            }

            System.out.println("===================================================");
            System.out.println("------------- What do you want to do? -------------");
            System.out.println("===================================================");
            System.out.println("Attack(A,a) = Get your level automatically");
            System.out.println("Bag(B,b) = Open your Inventory");
            System.out.println("Potion(P,p) = Use Potion to increase your HP");
            System.out.println("ExpCard(E,e) = Use expcard");
            System.out.println("Status(S,s) = Check your character's detail");
            System.out.println("ItemQualification(I,i) = Show item qualification");
            System.out.println("Quit(Q,q) = Quit the game (The game will not save!)");
            System.out.println("===================================================");
            System.out.print("Enter Character : ");
            activity = scan.next();

            if(activity.equalsIgnoreCase(I)){
                System.out.println("===================================================");
                System.out.println("--------------- Item  Qualification ---------------");
                System.out.println("===================================================");
                System.out.print("Potion = ");
                new Potion().print();
                System.out.print("ExpCard = ");
                new ExpCard().print();
                try{Thread.sleep(2000);}catch(InterruptedException ex){Thread.currentThread().interrupt();}

            }
            else if(activity.equalsIgnoreCase(E)){
                player.getNovice().useExpCard();
                try{Thread.sleep(2000);}catch(InterruptedException ex){Thread.currentThread().interrupt();}
            }
            //bag
            else if(activity.equalsIgnoreCase(B)){
                while(true){
                    System.out.println("===================================================");
                    System.out.println("------- What do you want to do in this bag? -------");
                    System.out.println("===================================================");
                    System.out.println("CheckSlotLeft(C,c) = Check the number of slot");
                    System.out.println("ShowItemInTheBag(S,s) = Show your items");
                    System.out.println("DiscardItem(D,d) = Discard your selected Item");
                    System.out.println("BackToMenu(B,b) = Back to main menu");//
                    System.out.println("===================================================");
                    System.out.print("Enter Character : ");
                    bagEvent = scan.next();
                    if(bagEvent.equalsIgnoreCase(B)){
                        break;
                    }
                    else if(bagEvent.equalsIgnoreCase(C)){
                        System.out.println("Now slot left = " + (player.getNovice().getBag().getFullSlot() - player.getNovice().getBag().getSlotUse()) + "/" + player.getNovice().getBag().getFullSlot());
                        try{Thread.sleep(2000);}catch(InterruptedException ex){Thread.currentThread().interrupt();}
                    }
                    else if(bagEvent.equalsIgnoreCase(S)){
                        player.getNovice().getBag().showItemInTheBag();
                    }
                    else if(bagEvent.equalsIgnoreCase(D)){
                        int idxOfDiscard;
                        player.getNovice().getBag().showItemInTheBag();
        
                        if(player.getNovice().getBag().getSlotUse() > 0){
                            System.out.print("Select your index to discard : ");
                            idxOfDiscard = scan.nextInt();
                            player.getNovice().getBag().discard(idxOfDiscard);
                        }
                        else if(player.getNovice().getBag().getSlotUse() == 0)
                            System.out.println("You not have any item to discard");
                        else
                            System.out.println("Please Enter the correct Character.");
                    }
                    
                }
            }
            //status
            else if(activity.equalsIgnoreCase(S)){ 
                System.out.println("***************************************************");    
                System.out.println("------------------CharacterStatus------------------");
                System.out.println("***************************************************");
                System.out.println("Name : " + player.getName());
                System.out.println("HP : " + player.getNovice().getHp() + "/" + player.getNovice().getMaxHp());
                System.out.println("Level : " + player.getNovice().getLevel());
                System.out.println("EXP : " + player.getNovice().getExp() + "/" + player.getNovice().getMaxExp());
                System.out.println("Class : " + player.getPlayerClass());
                System.out.println("All Skill : ");

                for(String skill : player.getNovice().getSkill()){
                    System.out.println("- " + skill);
                }

                System.out.println("***************************************************");
                try{Thread.sleep(2000);}catch(InterruptedException ex){Thread.currentThread().interrupt();}
                
            }
            //attack
            else if(activity.equalsIgnoreCase(A)){
                int monsterATK;
                int expFromMonster;
                int attackTime;
                int numberOfMonsters;
                int i = 0;
                int idxOfSkillUse;
                String skillUse = "NormalAttack";

                System.out.print("Enter number of monster: ");
                numberOfMonsters = scan.nextInt();

                while(numberOfMonsters != 0){
                    if(player.getNovice().getLevel() == 3 && player.getPlayerClass().equals("Novice"))
                        break;
                    i = 0;
                    attackTime = (int)(Math.random() * 4999 + 1);
                    if(!player.getPlayerClass().equals("Novice")){
                        System.out.println("---------------------------------------------------");
                        System.out.println("Select Skill : ");

                        for(String skill : player.getNovice().getSkill()){
                            System.out.println("(" + i + ")" + skill + " ----> Reduce time = " + ((float)player.getNovice().attackSkill(skill) / 1000) + " second");
                            i++;
                        }

                        System.out.print("Enter number to select skill : ");
                        idxOfSkillUse = scan.nextInt();

                        if(idxOfSkillUse < player.getNovice().getSkill().size() && idxOfSkillUse >= 0){
                            skillUse = player.getNovice().getSkill().get(idxOfSkillUse);
                            attackTime -= player.getNovice().attackSkill(skillUse);
                            if(attackTime < 0)
                                attackTime = 0;
                            System.out.println("You use " + player.getNovice().getSkill().get(idxOfSkillUse));
                        }
                        else{
                            System.out.println("Please Enter other number");
                            continue;
                        }
                    }

                    monsterATK = (int)(Math.random() * 49 + 1);
                    expFromMonster = (int)(Math.random() * 99 + 1);
                    player.getNovice().attacked(monsterATK);
                    System.out.println("---------------------------------------------------");
                    System.out.println("Attacking..." + ((float)(attackTime + player.getNovice().attackSkill(skillUse)) / 1000) + "s - " + ((float)player.getNovice().attackSkill(skillUse) / 1000) + "s = " + (float)(attackTime) / 1000 + "s");
 
                    try{Thread.sleep(attackTime);}catch(InterruptedException ex){Thread.currentThread().interrupt();}

                    if(player.getNovice().getHp() > 0){            //if your Avatar is not die
                        System.out.println("Monster has been slain.");
                        System.out.println("EXP gain : " + "+" + expFromMonster);
                        player.getNovice().receiveExp(expFromMonster);

                        if(player.getNovice().getExp() >= player.getNovice().getMaxExp())
                            System.out.println("EXP : " + player.getNovice().getMaxExp() + "/" + player.getNovice().getMaxExp());
                        else   
                            System.out.println("EXP : " + player.getNovice().getExp() + "/" + player.getNovice().getMaxExp());

                        System.out.println("hp : " + player.getNovice().getHp());
                        
                        Item itemFromMonster = dropItem();
                        
                        if(player.getNovice().getBag().getSlotUse() < 40){
                            player.getNovice().getBag().putItemToBag(itemFromMonster);
                        }
                        else{
                            System.out.println("Your slot is full, Please discard something");
                        }

                        System.out.println("You got " + player.getNovice().getBag().getItems().get(player.getNovice().getBag().getSlotUse() - 1).getItemName());
                        System.out.println("Now slot left = " + (player.getNovice().getBag().getFullSlot() - player.getNovice().getBag().getSlotUse()) + "/" + player.getNovice().getBag().getFullSlot());
                        
                        if(player.getNovice().getHp() <= 40){
                            player.getNovice().usePotion(player.getPlayerClass());
                            try{Thread.sleep(2000);}catch(InterruptedException ex){Thread.currentThread().interrupt();}
                        }
                
                        try{Thread.sleep(500);}catch(InterruptedException ex){Thread.currentThread().interrupt();}
                
                        if(player.getNovice().getExp() >= player.getNovice().getMaxExp()){        //if your Avatar's level is up
                            player.getNovice().levelUp();
                        }
                    }
                    else{               // your Avatar is die
                        int expDecrease;

                        System.out.println("Your character die, please wait for 10 seconds.");
                        expDecrease = player.getNovice().getLevel() * 5;
                        player.getNovice().setExpAfterDie(expDecrease);
                        System.out.println("Your EXP decrease for " + expDecrease);

                        for(i = 10; i > 0 ; i--){
                            System.out.print(i + "...");
                            try{Thread.sleep(1000);}catch(InterruptedException ex){Thread.currentThread().interrupt();}
                        }
        
                        System.out.println("Your HP is recovered!");
                        player.getNovice().setHpAfterDie();
                        break;
                    }

                    try{Thread.sleep(1500);} catch(InterruptedException ex){Thread.currentThread().interrupt();}
                    numberOfMonsters--;
                }
            }
            //usepotion
            else if(activity.equalsIgnoreCase(P)){
                player.getNovice().usePotion(player.getPlayerClass());
            }
            else if(activity.equalsIgnoreCase(Q)){
                gameContinue = false;
            }
            else{
                System.out.println("Please Enter the correct Character.");
            }
        }
    }

    public static void main(String args[]){
        MainGame g = new MainGame();
        g.showGameInterface();
        System.out.println("Thanks for playing this game.");
        try{Thread.sleep(1000);}catch(InterruptedException ex){Thread.currentThread().interrupt();}
    }
}
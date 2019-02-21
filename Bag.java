import java.io.*;
import java.util.*;

public class Bag{
    ArrayList<Item> items;
    boolean openBag;
    int fullSlot;
    int slotUse;
    public Bag(int _slot){
        fullSlot = _slot;
        items = new ArrayList<Item>();
        for(int i = 0; i < 10; i++)
            items.add(new Item().initialItem(0));
        openBag = true;
        slotUse = 10;
    }

    public void showBagAct(){
        Scanner scan = new Scanner(System.in);
        String bagEvent;
        openBag = true;

        while(openBag){
            System.out.println("===================================================");
            System.out.println("------- What do you want to do in this bag? -------");
            System.out.println("===================================================");
            System.out.println("CheckSlotLeft(C,c) = Check the number of slot");
            System.out.println("ShowItemInTheBag(S,s) = Show your items");
            System.out.println("DiscardItem(D,d) = Discard your selected Item");
            System.out.println("BackToMenu(B,b) = Back to main menu");
            System.out.println("===================================================");
            System.out.print("Enter Character : ");
            bagEvent = scan.next();
            showBagEvent(bagEvent);
        }
    }

    public void showBagEvent(String bagEvent){
        String C = "C", S = "S", B = "B", D = "D";
        if(bagEvent.equalsIgnoreCase(B)){
            backToMain();
        }
        else if(bagEvent.equalsIgnoreCase(C)){
            checkSlotLeft();
            try{
                Thread.sleep(1000);
            }
            catch(InterruptedException ex){
                Thread.currentThread().interrupt();
            }
        }
        else if(bagEvent.equalsIgnoreCase(S)){
            showItemInTheBag();
        }
        else if(bagEvent.equalsIgnoreCase(D)){
            discard();
        }
        else
            System.out.println("Please Enter the correct Character.");
    }

    public void discard(){
        String discardItem;
        Scanner scan = new Scanner(System.in);
        int idxOfDiscard;
        showItemInTheBag();
        
        if(slotUse > 0){
            System.out.print("Select your index to discard : ");
            idxOfDiscard = scan.nextInt();

            if(idxOfDiscard < items.size()){
                discardItem = items.get(idxOfDiscard).itemName;
                items.remove(idxOfDiscard);
                System.out.println("You removed " + discardItem);
                slotUse--;
            }
            
        }
        else if(slotUse == 0)
            System.out.println("You not have any item to discard");

        try{
            Thread.sleep(1000);
        }
        catch(InterruptedException ex){
            Thread.currentThread().interrupt();
        }
    }

    public void checkSlotLeft(){
        System.out.println("Now slot left = " + (fullSlot - slotUse) + "/" + fullSlot);
    }

    public void backToMain(){
        openBag = false;
    }

    public void putItemToBag(){
        int randomIdxOfItem = (int)(Math.random() * 4);
        Item itemDrop = new Item();
        itemDrop = itemDrop.initialItem(randomIdxOfItem);
        if(slotUse < 40){
            items.add(itemDrop);
            slotUse++;
        }
        else
            System.out.println("Your slot is full, Please discard something");
    }

    public void showItemInTheBag(){
        int potion = 0;
        int sword = 0;
        int armor = 0;
        int ring = 0;
        int i = 0;
        
        if(slotUse > 0){

            for(Item item : items){
                if(item.itemName.equals("Potion")){
                    potion++;
                }else if(item.itemName.equals("Sword")){
                    sword++;
                }else if(item.itemName.equals("Armor")){
                    armor++;
                }
                else if(item.itemName.equals("Ring")){
                    ring++;
                }
                System.out.println("[" + i + "]" + item.itemName);
                i++;
            }

            System.out.println("===================================================");
            System.out.println("Potion = " + potion);
            System.out.println("Sword = " + sword);
            System.out.println("Armor = " + armor);
            System.out.println("Ring = " + ring);
            System.out.println("===================================================");
            checkSlotLeft();
            System.out.println("===================================================");

            try{
                Thread.sleep(1000);
            }
            catch(InterruptedException ex){
                Thread.currentThread().interrupt();
            }
        }
        else
            System.out.println("You not have any Item");

        try{
            Thread.sleep(1000);
        }
        catch(InterruptedException ex){
            Thread.currentThread().interrupt();
        }
    }

    public int getPotion(){
        int potion = 0;

        for(Item item : items){
            if(item.itemName.startsWith("Potion"))
                potion++;
        }

        return potion;
    }

    public void usePotion(){
        int idxOfPotionUsed = 0;

        for(int i = 0; i < items.size(); i++){
            idxOfPotionUsed = items.get(i).itemName.indexOf("Potion");
            if(idxOfPotionUsed != -1)
                break;
        }

        items.remove(idxOfPotionUsed);
        slotUse--;
    }
}
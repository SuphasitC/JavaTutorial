import java.io.*;
import java.util.*;

public class Bag{
    private ArrayList<Item> items;
    private int fullSlot;
    private int slotUse;

    public Bag(int _slot){
        fullSlot = _slot;
        items = new ArrayList<Item>();
        
        for(int i = 0; i < 10; i++)
            items.add(new Item("Potion"));

        slotUse = 10;
    }

    public ArrayList<Item> getItems(){
        return items;
    }

    public int getFullSlot(){
        return fullSlot;
    }

    public int getSlotUse(){
        return slotUse;
    }

    public void putItemToBag(Item itemFromMonster){
        items.add(itemFromMonster);
        slotUse++;
    }

    public int getPotion(){
        int potion = 0;

        for(Item item : items){
            if(item.getItemName().startsWith("Potion"))
                potion++;
        }

        return potion;
    }

    public int getExpCard(){
        int expCard = 0;

        for(Item item : items){
            if(item.getItemName().startsWith("ExpCard"))
                expCard++;
        }

        return expCard;
    }

    public void minusPotionInBag(){
        int idxOfPotionUsed = 0;

        for(int i = 0; i < items.size(); i++){
            idxOfPotionUsed = items.get(i).getItemName().indexOf("Potion");
            if(idxOfPotionUsed != -1)
                break;
        }

        items.remove(idxOfPotionUsed);
        useSlotInBag();
    }

    public void minusExpCardInBag(){
        int idxOfExpCardUsed = 0;

        for(int i = 0; i < items.size(); i++){
            idxOfExpCardUsed = items.get(i).getItemName().indexOf("ExpCard");
            if(idxOfExpCardUsed != -1)
                break;
        }

        items.remove(idxOfExpCardUsed);
        useSlotInBag();
    }

    public void useSlotInBag(){
        slotUse--;
    }

    public void showItemInTheBag(){
        int potion = 0;
        int expcard = 0;
        int i = 0;
                            
        if(slotUse > 0){
                    
            for(Item item : items){
                if(item.getItemName().equals("Potion")){
                    potion++;
                }else if(item.getItemName().equals("ExpCard")){
                    expcard++;
                }
                System.out.println("[" + i + "]" + item.getItemName());
                i++;
            }
                    
            System.out.println("===================================================");
            System.out.println("Potion = " + potion);
            System.out.println("Expcard = " + expcard);
            System.out.println("===================================================");
            System.out.println("Now slot left = " + (fullSlot - slotUse + "/" + fullSlot));
            System.out.println("===================================================");
            try{Thread.sleep(2000);}catch(InterruptedException ex){Thread.currentThread().interrupt();}
        }
        else{
            System.out.println("You not have any Item");
        }
    }

    public void discard(int idxOfDiscard){
        String discardItem;
        if(idxOfDiscard < items.size()){
            discardItem = items.get(idxOfDiscard).getItemName();
            items.remove(idxOfDiscard);
            System.out.println("You removed " + discardItem);
            useSlotInBag();
            try{Thread.sleep(1500);}catch(InterruptedException ex){Thread.currentThread().interrupt();}
        }
    }
}
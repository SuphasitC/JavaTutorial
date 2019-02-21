import java.io.*;
import java.util.*;

public class Item{
    String itemName;
    int amount = 0;
    
    public Item initialItem(int idxOfItem){
        Item newItem = new Item();
        if(idxOfItem == 0){
            newItem.itemName = "Potion";
            newItem.amount = 1;
        }
        else if(idxOfItem == 1){
            newItem.itemName = "Sword";
            newItem.amount = 1;
        }
        else if(idxOfItem == 2){
            newItem.itemName = "Armor";
            newItem.amount = 1;
        }else{
            newItem.itemName = "Ring";
            newItem.amount = 1;
        }
        return newItem;
    }

}
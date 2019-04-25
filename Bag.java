import java.util.*;
public class Bag{
    private int fullSlot;
    private int slotUse;
    private ArrayList<Item> items;

    public Bag(int _fullSlot){
        fullSlot = _fullSlot;
        slotUse = 0;
        items = new ArrayList<Item>();

        for(int i = 0; i < 10; i++){
            items.add(new Item("Potion"));
            slotUse++;
        }
    }

    public int getFullSlot(){
        return fullSlot;
    }

    public int getSlotUse(){
        return slotUse;
    }

    public void setSlotUse(int use){
        slotUse += use;
    }

    public ArrayList<Item> getItems(){
        return items;
    }
}
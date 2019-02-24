import java.io.*;
import java.util.*;

public class Item{
    private String itemName;
    private String qualification;

    public Item(String _name){
        itemName = _name;
    }

    public void setQualification(String property){
        qualification = property;
    }

    public String getItemName(){
        return itemName;
    }

    public String getQualification(){
        return qualification;
    }
}
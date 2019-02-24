import java.io.*;
import java.util.*;

public class Player {
    private String name;
    private Novice novice;
    private String playerClass;

    public Player(String _name){
        name = _name;
        novice = new Novice();
        playerClass = "Novice";
    }

    public String getName(){
        return name;
    }

    public Novice getNovice(){
        return novice;
    }

    public String getPlayerClass(){
        return playerClass;
    }

    public void changeClass(String pClass){
        playerClass = pClass;
        if(pClass.equals("Assassin")){
            novice = new Assassin(novice);
        }
        else if(pClass.equals("Mage")){
            novice = new Mage(novice);
        }
        else if(pClass.equals("Archer")){
            novice = new Archer(novice);
        }
    }
}
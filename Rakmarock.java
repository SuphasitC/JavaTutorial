import java.util.*;

public class Rakmarock{
    private  Novice novice;
    private  ArrayList<Monster> monsters;

    public Rakmarock(){
        novice = new Novice("Em");
        monsters = new ArrayList<Monster>();
        monsters.add(new Monster("Slime"));
        monsters.add(new Monster("Genijiro"));
        monsters.add(new Monster("Icchin"));
    }

    public Novice getNovice(){
        return novice;
    }

    public ArrayList<Monster> getMonsters(){
        return monsters;
    }

    public Monster getRandomMonster(){
        return monsters.get((int)(Math.random() * 3));
    }

    public Item dropItem(){
        int idxOfItemDrop = (int)(Math.random() * 2);
        String nameOfItemDrop;

        if(idxOfItemDrop == 0){
            nameOfItemDrop = "Potion";
        }
        else{
            nameOfItemDrop = "ExpCard";
        }

        Item itemDrop = new Item(nameOfItemDrop);
        return itemDrop;
    }

    public void putItemToBag(Bag _noviceBag, Item _itemDrop){
        _noviceBag.getItems().add(_itemDrop);
    }

    public Monster createMonster(int idxOfMonster){
        String monsterName;

        if(idxOfMonster == 0){
            monsterName = "Slime";
        }
        else if(idxOfMonster == 1){
            monsterName = "Genijiro";
        }
        else{
            monsterName = "Icchin";
        }
        return new Monster(monsterName);
    }

    public void changeClass(int idxOfClass){
        if(idxOfClass == 1){
            novice = new Assassin(novice);
        }
        else if(idxOfClass == 2){
            novice = new Mage(novice);
        }
        else if(idxOfClass == 3){
            novice = new Archer(novice);
        }
    }
    public static void main(String args[]) {
        new RakmarockGui();
    }
}
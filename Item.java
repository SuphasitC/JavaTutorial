public class Item{
    private String name;
    private int valueOfItem;

    public Item(String _name){
        name = _name;

        if(_name.equalsIgnoreCase("Potion")){
            valueOfItem = 60;
        }
        else if(_name.equalsIgnoreCase("ExpCard")){
            valueOfItem = (int)(Math.random() * 100);
        }
    }

    public String getName(){
        return name;
    }

    public int getValueOfItem(){
        return valueOfItem;
    }
}
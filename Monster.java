import java.util.*;
public class Monster extends Alive{
    private boolean drop;
    private int expGain;
    private String picture;

    public Monster(String _name){
        super(_name);
        if(_name.equals("Genijiro")){
            super.hp = 50;
            super.maxHp = 50;
            super.attackDamage = 10;
            expGain = 50;
            picture = "genijiro.jpg";
        }
        else if(_name.equals("Icchin")){
            super.hp = 70;
            super.maxHp = 70;
            super.attackDamage = 20;
            expGain = 80;
            picture = "icchin.jpg";
        }
        else{
            expGain = 10;
            picture = "slime.jpg";
        }
        if(Math.random() < 0.8)
            drop = true;
        else
            drop = false;
    }

    public boolean getDrop(){
        return drop;
    }

    public int getExpGain(){
        return expGain;
    }

    public String getPicture(){
        return picture;
    }

    public void showMonsterList(ArrayList<Monster> monsters){
        int i = 1;
        System.out.println("===================================================");
        System.out.println("------------------- Monster List ------------------");
        System.out.println("===================================================");

        for(Monster monster : monsters){
            System.out.println("(" + i + ")" + monster.name);
            i++;
        }
        
        System.out.println("Enter Monster : ");
    }
}
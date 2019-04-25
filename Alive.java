public class Alive{
    protected String name;
    protected int hp;
    protected int maxHp;
    protected int attackDamage;
    protected boolean isAlive;

    public Alive(String _name){
        name = _name;
        hp = 30;
        maxHp = 30;
        attackDamage = 5;
        isAlive = true;
    }

    public String getName(){
        return name;
    }

    public int getHp(){
        return hp;
    }

    public void setHp(int set){
        hp += set;
    }

    public int getMaxHp(){
        return maxHp;
    }

    public int getAttackDamage(){
        return attackDamage;
    }

    public boolean getIsAlive(){
        return isAlive;
    }

    public void setIsAlive(boolean bool){
        setIsAlive(bool);
    }

    public void die(){
        hp = maxHp;
        System.out.println(name + " die.");
    }
}
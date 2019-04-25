import javax.swing.JLabel;

public class Novice extends Alive{
    protected int level;
    protected int exp;
    protected int maxExp;
    protected int monstersHaveAttacked;
    protected String job;
    protected Bag bag;
    protected String picture;

    public Novice(String _name){
        super(_name);
        super.hp = 100;
        super.attackDamage = 10;
        level = 1;
        exp = 0;
        maxHp = 100;
        maxExp = 100;
        monstersHaveAttacked = 0;
        job = "Novice";
        bag = new Bag(40);
    }

    public int getLevel(){
        return level;
    }

    public int getExp(){
        return exp;
    }

    public int getMaxExp(){
        return maxExp;
    }

    public int getMonsterHasAttacked(){
        return monstersHaveAttacked;
    }

    public String getJob(){
        return job;
    }

    public Bag getBag(){
        return bag;
    }

    public String getPiture(){
        return "novice.jpg";
    }

    public boolean usePotion(){
        int idxOfPotion = 0;
        boolean useSuccess = false;

        for(Item item : bag.getItems()){
            if(item.getName().startsWith("Potion")){
                bag.setSlotUse(-1);
                bag.getItems().remove(idxOfPotion);
                plusHp(item.getValueOfItem());
                useSuccess = true;
                break;
            }
            idxOfPotion++;
        }
        return useSuccess;
    }

    public boolean useExpCard(){
        int idxOfExpCard = 0;
        boolean useSuccess = false;

        for(Item item : bag.getItems()){
            if(item.getName().startsWith("ExpCard")){
                bag.setSlotUse(-1);
                bag.getItems().remove(idxOfExpCard);
                plusExp(item.getValueOfItem());
                useSuccess = true;
                break;
            }
            idxOfExpCard++;
        }
        return useSuccess;
    }

    public boolean attack(Monster monster, JLabel noviceHp, JLabel nowAttacking, JLabel monAttackedHp){
        while(this.isAlive && monster.getIsAlive()){
            //GUI Update
            nowAttacking.setText("Now Attacking : " + monster.getName());
            nowAttacking.paintImmediately(nowAttacking.getVisibleRect());
            monAttackedHp.setText("HP : " + monster.getHp() + "/" + monster.getMaxHp());
            monAttackedHp.paintImmediately(monAttackedHp.getVisibleRect());
            noviceHp.setText("HP : " + this.hp + "/" + this.maxHp);
            noviceHp.paintImmediately(noviceHp.getVisibleRect());
            this.hp -= monster.getAttackDamage();
            monster.setHp(-this.attackDamage);
            try{Thread.sleep(1000);}catch(InterruptedException ex){Thread.currentThread().interrupt();}


            if(this.hp <= 0){
                this.die();
                this.lostExp();
                return false;
            }
            else if(monster.getHp() <= 0){
                monster.die();
                this.monstersHaveAttacked += 1;
                plusExp(monster.getExpGain());
                return true;
            }
        }
        return false;
    }

    public void lostExp(){
        int expLost = (int)(Math.random() * 100);

        if(exp - expLost <= 0){
            exp = 0;
        }
        else
            exp -= expLost;
    }

    public void plusExp(int expPlus){
        if(this.exp + expPlus < maxExp)
            this.exp += expPlus;
        else{
            this.exp = 0;
            this.levelUp();
        }
    }
    
    public void plusHp(int hpPlus){
        if(this.hp + hpPlus <= this.maxHp)
            this.hp += hpPlus;
        else{
            this.hp = this.maxHp;
        }
    }

    public void levelUp(){
        this.level += 1;
        this.maxHp += 5;
        this.maxExp += 20;
        this.attackDamage += 1;
        this.hp = this.maxHp;
    }
}
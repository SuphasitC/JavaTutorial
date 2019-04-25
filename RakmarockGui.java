import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class RakmarockGui extends JFrame{
    private static final long serialVersionUID = 1L;
    private Rakmarock rakmarock;
    Container c;
    JPanel picturePanel;
    JPanel aliveStatPanel;
    JPanel noviceStatPanel;
    JPanel monsterStatPanel;
    JPanel buttonPanel;
    JPanel nowAttackingPanel;
    JLabel noviceName;
    JLabel noviceLevel;
    JLabel noviceClass;
    JLabel noviceHp;
    JLabel noviceExp;
    JLabel noviceAttackDamage;
    JLabel noviceMonstersHaveAttacked;
    JLabel monsterWhich;
    JLabel monsterName;
    JLabel monsterHp;
    JLabel monsterAttackDamage;
    JLabel nowAttacking;
    JLabel monAttackedHp;
    JLabel playerLabel;
    JLabel monsterLabel;
    ImageIcon playerIcon;
    ImageIcon monsterIcon;
    String playerPicture;
    String monsterPicture;
    JButton attackButton;
    JButton bagButton;
    JButton usePotionButton;
    JButton useExpCardButton;

    public RakmarockGui(){
        super("Rakmarok");
        rakmarock = new Rakmarock();
        c = getContentPane();
        c.setLayout(new BoxLayout(c, BoxLayout.Y_AXIS));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        picturePanel = new JPanel();
        picturePanel.setLayout(new BoxLayout(picturePanel, BoxLayout.X_AXIS));

        aliveStatPanel = new JPanel();
        aliveStatPanel.setLayout(new BoxLayout(aliveStatPanel, BoxLayout.X_AXIS));

        //Now attacking
        nowAttackingPanel = new JPanel();
        nowAttackingPanel.setLayout(new BoxLayout(nowAttackingPanel, BoxLayout.Y_AXIS));
        nowAttacking = new JLabel("No monster attacking now.");
        nowAttackingPanel.add(nowAttacking);
        monAttackedHp = new JLabel("No monster attacking");
        nowAttackingPanel.add(monAttackedHp);
        nowAttacking.setAlignmentX(0.5f);
        monAttackedHp.setAlignmentX(0.5f);

        //Novice
        playerPicture = "novice.jpg";
        playerIcon = new ImageIcon("potion.jpg");

        noviceStatPanel = new JPanel();
        noviceStatPanel.setLayout(new BoxLayout(noviceStatPanel, BoxLayout.Y_AXIS));
        noviceName = new JLabel("Name : " + rakmarock.getNovice().getName());
        noviceLevel = new JLabel("Level : " + rakmarock.getNovice().getLevel());
        noviceClass = new JLabel("Class : " + rakmarock.getNovice().getJob());
        noviceHp = new JLabel("HP : " + rakmarock.getNovice().getHp() + "/"
                                    + rakmarock.getNovice().getMaxHp());
        noviceExp = new JLabel("EXP : " + rakmarock.getNovice().getExp() + "/"
                                    + rakmarock.getNovice().getMaxExp());
        noviceAttackDamage = new JLabel("Attack damage : " + rakmarock.getNovice().getAttackDamage());
        noviceMonstersHaveAttacked = new JLabel("Monsters have slain : " + rakmarock.getNovice().getMonsterHasAttacked());
        //
        //Add noviceLabel to novicePanel
        noviceStatPanel.add(noviceName);
        noviceStatPanel.add(noviceLevel);
        noviceStatPanel.add(noviceClass);
        noviceStatPanel.add(noviceHp);
        noviceStatPanel.add(noviceExp);
        noviceStatPanel.add(noviceAttackDamage);
        noviceStatPanel.add(noviceMonstersHaveAttacked);
        //Monster
        Monster monster = rakmarock.getRandomMonster();
        //
        //monsterPicture = monster.getPicture();
        monsterPicture = "expcard.jpg";
        monsterIcon = new ImageIcon(monsterPicture);
        //
        monsterStatPanel = new JPanel();
        monsterStatPanel.setLayout(new BoxLayout(monsterStatPanel, BoxLayout.Y_AXIS));
        monsterWhich = new JLabel(" : Monster near you : ");
        monsterName = new JLabel("Name : " + monster.getName());
        monsterHp = new JLabel("HP : " + monster.getHp() + "/" + monster.getMaxHp());
        monsterAttackDamage = new JLabel("Attack damage : " + monster.getAttackDamage());
        //Add monsterLabel to monsterPanel
        monsterStatPanel.add(monsterWhich);
        monsterStatPanel.add(monsterName);
        monsterStatPanel.add(monsterHp);
        monsterStatPanel.add(monsterAttackDamage);
        aliveStatPanel.add(noviceStatPanel);
        aliveStatPanel.add(monsterStatPanel);

        //Button
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(2, 2, 3, 3));
        attackButton = new JButton("Attack");
        bagButton = new JButton("OpenBag");
        usePotionButton = new JButton("Use Potion");
        useExpCardButton = new JButton("Use ExpCard");
        bagButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                JFrame bagFrame = new JFrame("Inventory");
                bagFrame.setDefaultCloseOperation(HIDE_ON_CLOSE);
                bagFrame.setLayout(new GridLayout(8, 5));

                JLabel[] slot;
                slot = new JLabel[40];
                int i = 0;

                for(Item item : rakmarock.getNovice().getBag().getItems()){
                    if(item.getName().equals("Potion")){
                        slot[i] = new JLabel(new ImageIcon("potion.jpg"));
                    }
                    else if(item.getName().equals("ExpCard")){
                        slot[i] = new JLabel(new ImageIcon("expcard.jpg"));
                    }
                    else{
                        slot[i] = new JLabel(new ImageIcon("empty.jpg"));
                    }
                    bagFrame.add(slot[i]);
                    i++;
                }
                int fullSlot = rakmarock.getNovice().getBag().getFullSlot();

                for(; i < fullSlot; i++){
                    slot[i] = new JLabel(new ImageIcon("empty.jpg"));
                    bagFrame.add(slot[i]);
                }

                bagFrame.setVisible(true);
                bagFrame.pack();
            }
        });
        attackButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                boolean attackSuccess = false;
                String monsterToAttack = JOptionPane.showInputDialog(c,
                                        "(1)" + rakmarock.getMonsters().get(0).getName() +
                                        "\n(2)" + rakmarock.getMonsters().get(1).getName() +
                                        "\n(3)" + rakmarock.getMonsters().get(2).getName() +
                                        " \n\n Choose Monster", "Choose monster", JOptionPane.NO_OPTION);
                int idxOfMonsterChoose = Integer.parseInt(monsterToAttack);
                Monster monster = rakmarock.createMonster(idxOfMonsterChoose - 1);
                attackSuccess = rakmarock.getNovice().attack(monster, noviceHp, nowAttacking, monAttackedHp);
                if(attackSuccess && monster.getDrop()){
                    Item itemDrop = rakmarock.dropItem();
                    if(rakmarock.getNovice().getBag().getSlotUse() < rakmarock.getNovice().getBag().getFullSlot()){
                        JOptionPane.showMessageDialog(c, monster.getName() + " has been slain.\nYou drop " + itemDrop.getName(), "Attack success", JOptionPane.INFORMATION_MESSAGE);
                        rakmarock.putItemToBag(rakmarock.getNovice().getBag(), itemDrop);
                        rakmarock.getNovice().getBag().setSlotUse(1);
                    }
                    else{
                        JOptionPane.showMessageDialog(c, monster.getName() + "Your bag is full.", "Full bag", JOptionPane.ERROR_MESSAGE);
                    }
                    
                }
                else if(attackSuccess){
                    JOptionPane.showMessageDialog(c, monster.getName() + " has been slain.\n", "Attack success", JOptionPane.INFORMATION_MESSAGE);
                }
                else{
                    JOptionPane.showMessageDialog(c, "You die, EXP Decrease.", "Attack failure", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
        usePotionButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                boolean useSuccess = rakmarock.getNovice().usePotion();
                Item item = new Item("Potion");

                if(useSuccess){
                    JOptionPane.showMessageDialog(c, "Use Potion, Increase HP = " + item.getValueOfItem(), "Use potion success", JOptionPane.INFORMATION_MESSAGE);
                }
                else{
                    JOptionPane.showMessageDialog(c, "You not have enough potion", "Not enough potion", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
        useExpCardButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                boolean useSuccess = rakmarock.getNovice().useExpCard();

                if(useSuccess){
                    JOptionPane.showMessageDialog(c, "Use ExpCard, Increase EXP", "Use potion success", JOptionPane.INFORMATION_MESSAGE);
                }
                else{
                    JOptionPane.showMessageDialog(c, "You not have enough expcard", "Not enough expcard", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        //Add Button to panel
        buttonPanel.add(attackButton);
        buttonPanel.add(bagButton);
        buttonPanel.add(usePotionButton);
        buttonPanel.add(useExpCardButton);

        // picturePanel.add(playerLabel);
        // picturePanel.add(monsterLabel);
        picturePanel.add(new JLabel(new ImageIcon("test1.jpg")));

        c.add(picturePanel);
        c.add(aliveStatPanel);
        c.add(nowAttackingPanel);
        c.add(buttonPanel);
        pack();
        setVisible(true);
        setResizable(false);

        int noviceLv = rakmarock.getNovice().getLevel();
        while(true){
            redraw();
            try{Thread.sleep(1000);}catch(InterruptedException ex){Thread.currentThread().interrupt();}
            if(noviceLv < rakmarock.getNovice().getLevel()){
                JOptionPane.showMessageDialog(c, "Level Up!, your Level : " + rakmarock.getNovice().getLevel(), "Level up", JOptionPane.OK_OPTION);
                noviceLv = rakmarock.getNovice().getLevel();
            }
            //changeClass
            if(rakmarock.getNovice().getLevel() >= 5 && rakmarock.getNovice().getJob().equals("Novice")){
                String changeJob = JOptionPane.showInputDialog(c,
                                        "Time to change class, choose one." +                        
                                        "\n(1)Assassin" + "\n(2)Mage" + "\n(3)Archer", "Time to change class", JOptionPane.WARNING_MESSAGE);
                int idxOfClass = Integer.parseInt(changeJob);
                if(idxOfClass < 1 && idxOfClass > 3){
                    JOptionPane.showMessageDialog(c, "Out of index, please try again.", "Warning!", JOptionPane.WARNING_MESSAGE);
                }
                else if(rakmarock.getNovice().getJob().equals("Novice")){
                    rakmarock.changeClass(idxOfClass);
                    JOptionPane.showMessageDialog(c,"Now, you're " + rakmarock.getNovice().getJob(), "Change class success!", JOptionPane.INFORMATION_MESSAGE);
                }
            }
            
        }

    }

    public void redraw(){
        // playerIcon.
        // playerLabel.setIcon(playerIcon);
        // monsterLabel.setIcon(monsterIcon);
        noviceName.setText("Name : " + rakmarock.getNovice().getName());
        noviceLevel.setText("Level : " + rakmarock.getNovice().getLevel());
        noviceClass.setText("Class : " + rakmarock.getNovice().getJob());
        noviceHp.setText("HP : " + rakmarock.getNovice().getHp() + "/"
                                    + rakmarock.getNovice().getMaxHp());
        noviceExp.setText("EXP : " + rakmarock.getNovice().getExp() + "/"
                                    + rakmarock.getNovice().getMaxExp());
        noviceAttackDamage.setText("Attack damage : " + rakmarock.getNovice().getAttackDamage());
        noviceMonstersHaveAttacked.setText("Monsters have slain : " + rakmarock.getNovice().getMonsterHasAttacked());

        Monster monster = rakmarock.getRandomMonster();
        monsterWhich.setText(" : Monster near you : ");
        monsterName.setText("Name : " + monster.getName());
        monsterHp.setText(("HP : " + monster.getHp() + "/" + monster.getMaxHp()));
        monsterAttackDamage.setText(("Attack damage : " + monster.getAttackDamage()));

        nowAttacking.setText("No monster attacking now.");
        monAttackedHp.setText("No monster attacking");
    }
}
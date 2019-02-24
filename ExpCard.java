import java.io.*;
import java.util.*;

public class ExpCard extends Item {
    private int increaseExp;

    public ExpCard(){
        super("ExpCard");
        super.setQualification("Increase random exp from 0 - 199");
        increaseExp = (int)(Math.random() * 200 + 1);
    }

    public int getIncreaseExp(){
        return increaseExp;
    }

    public void print(){
        System.out.println(super.getQualification());
    }
}
import java.io.*;
import java.util.*;

public class Potion extends Item {
    private float increaseHp;

    public Potion(){
        super("Potion");
        super.setQualification("Increase 60% of Max HP");
        increaseHp = 60;
    }

    public float getIncreaseHp(){
        return increaseHp;
    }

}
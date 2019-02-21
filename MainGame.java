import java.io.*;
import java.util.Scanner;
import java.util.Random;

public class MainGame {
    private boolean gameContinue;
    private Novice novice;

    public MainGame(){
        gameContinue = true;
        novice = new Novice("Em");
    }

    public static void main(String args[]){
        MainGame game = new MainGame();
        Scanner scan = new Scanner(System.in);
        String activity;
        while(game.gameContinue){
            game.showGameInterface();
            System.out.print("Enter Character : ");
            activity = scan.next();
            game.showEvent(activity);
        }
        System.out.println("Thanks for playing this game.");
    }

    public void showGameInterface(){
        System.out.println("===================================================");
        System.out.println("------------- What do you want to do? -------------");
        System.out.println("===================================================");
        System.out.println("Attack(A,a) = Get your level automatically");
        System.out.println("Bag(B,b) = Open your Inventory");
        System.out.println("Potion(P,p) = Increase your HP");
        System.out.println("Status(S,s) = Check your character's detail");
        System.out.println("Quit(Q,q) = Quit the game (The game will not save!)");
        System.out.println("===================================================");

    }

    public void showEvent(String activity){
        String S = "S",B = "B", P = "P", Q = "Q", A = "A";
        if(activity.equalsIgnoreCase(B)){
            novice.bag.showBagAct();
        }
        else if(activity.equalsIgnoreCase(S)){   //strcmp in C , does not worry about upper,lower case
            novice.status();
        }
        else if(activity.equalsIgnoreCase(A)){
            novice.attack();
        }
        else if(activity.equalsIgnoreCase(P)){
            novice.potion();
        }
        else if(activity.equalsIgnoreCase(Q)){
            this.gameContinue = false;
        }
        else{
            System.out.println("Please Enter the correct Character.");
        }
    }
}
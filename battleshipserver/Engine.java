
/**
 * Write a description of class Engine here.
 * 
 * @author Shlok Gharia & Akash Singhal  
 * @version (a version number or a date)
 */

import java.io.*;
import java.net.*;
import java.util.*;
import java.util.*;
import java.awt.*;
import javax.swing.*;
//start
import java.util.Random;
//end
public class Engine
{
    //start
    public static Random rand = new Random();
    public static int x = rand.nextInt(4);
    public static String sound1 = new String("/01 - & Down.wav");
    public static String sound2 = new String("/Needle.wav");
    public static String sound3 = new String("/No Love (Instrumental).wav");
    public static String sound4 = new String("/05 Stylo.wav");
    public static String[] soundBank = {sound1,sound2,sound3,sound4};
    static Music mPlayer = new Music(soundBank[2]);
    static Music mPlayer2 = new Music("/explosion_1.wav");
    static Music mPlayer3 = new Music("/02. Song For The Dead.wav");
    static Music mPlayer4 = new Music("/07 Thundercat - Oh Sheit it's X.wav");
    //end
    static Scanner scan = new Scanner(System.in);
    static int sendX;
    static int sendY;
    static int selectionx;
    static int selectiony;
    static boolean check = false;
    static ShipSelection ship;
    static ArrayList<XYPoint> list;
    public static void main(String [] args)throws Exception
    {

        ServerSocket servSocket;
        Socket fromClientSocket;
        int cTosPortNumber = 1777;
        String str;
        servSocket = new ServerSocket(cTosPortNumber);
        System.out.println("Waiting for a connection on " + cTosPortNumber);
        fromClientSocket = servSocket.accept();

        ObjectOutputStream oos = new ObjectOutputStream(fromClientSocket.getOutputStream());

        ObjectInputStream ois = new ObjectInputStream(fromClientSocket.getInputStream());
        oos.writeObject("You are connected to Player 1! Please wait for Player 1 to finish setup.");
        
        mPlayer.play();
        
        System.out.println((String)ois.readObject());
        oos.writeObject(selections());

        GameLogic gameLogic = new GameLogic(ship);
        if((boolean) ois.readObject())
        { 
            System.out.println("Let's start playing.");
            System.out.println("----------------------------------------------");
            oos.writeObject("Let's start playing. Let Player 1 make their move first.");
        }
        MovesBoard board = new MovesBoard();
        ShipBoard board2 = new ShipBoard();

        board2.changeColor(list.get(0).getXPosition(), list.get(0).getYPosition(), Color.BLACK);
        board2.changeColor(list.get(1).getXPosition(), list.get(1).getYPosition(), Color.BLACK);
        board2.changeColor(list.get(2).getXPosition(), list.get(2).getYPosition(), Color.BLACK);
        while(!check)
        {
            board.setEnabled(true);
            System.out.println("Select a place to hit on the board and then hit enter");
            scan.nextLine();
            board.setEnabled(false);
            oos.writeObject(sendX);
            oos.writeObject(sendY);
            if((boolean) ois.readObject()){
                board.changeColor(sendX, sendY, Color.RED);
                if((boolean) ois.readObject()){
                    JOptionPane.showMessageDialog(null, "You Won!");
                    mPlayer.stop();
                    mPlayer4.play();
                    board.setVisible(false);
                    board2.setVisible(false);
                    System.exit(0);
                }
            }
            System.out.println("Now let's wait for Player 2");
            oos.writeObject(true);
            if((boolean) ois.readObject())
            {
                selectionx = (int) ois.readObject();
                selectiony = (int) ois.readObject();
                if(gameLogic.hitOrMiss(selectionx,selectiony,mPlayer2)==true)
                {
                    board2.changeColor(selectionx, selectiony, Color.RED);
                    oos.writeObject(true);
                    for(int i=0; i<list.size(); i++)
                    {
                        if((list.get(i).getXPosition() == selectionx) && (list.get(i).getYPosition() == selectiony))
                        {
                            list.remove(i);
                        }
                    }
                    if(gameLogic.endGame(list)){
                        oos.writeObject(true);
                        JOptionPane.showMessageDialog(null, "You Lost");
                        mPlayer.stop();
                        mPlayer3.play();
                        board.setVisible(false);
                        board2.setVisible(false);
                        System.exit(0); 
                    }
                    else{oos.writeObject(false);}
                }
                else if(gameLogic.hitOrMiss(selectionx,selectiony,mPlayer2)==false)
                {
                    board2.changeColor(selectionx, selectiony, Color.WHITE);
                    oos.writeObject(false);
                }
            }
            check=gameLogic.endGame(list);
        }
        oos.close();
        ois.close();
        servSocket.close();
        fromClientSocket.close();
    }

    public static boolean selections()
    {
        System.out.println("Please input your name");
        String name = scan.nextLine();
        System.out.println("Now, select 3 ships from the selection board (Press enter when done)");
        ship = new ShipSelection();
        scan.nextLine();
        ship.setVisible(false);
        list = ship.getPoints();
        return true;
    }

    public static void getXY(int x, int y)
    {
        sendX= x;
        sendY= y;
    }
}

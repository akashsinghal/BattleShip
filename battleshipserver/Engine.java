
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
public class Engine
{
    static Scanner scan = new Scanner(System.in);
    static int sendX;
    static int sendY;
    static int selectionx;
    static int selectiony;
    static boolean check = true;
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
        oos.writeObject("You are connected to Player 2!");
        System.out.println((String)ois.readObject());
        scan.nextLine();
        System.out.println("Now let's begin!");
        System.out.println("First, let's setup the game");
        oos.writeObject(selections());
        
        GameLogic gameLogic = new GameLogic(ship);
        if((boolean) ois.readObject())
        {
            System.out.println("Let's start playing.");
            oos.writeObject("Let's start playing. Let Player 1 make their move first.");
        }
        MovesBoard board = new MovesBoard();
        ShipBoard board2 = new ShipBoard();
        
        board2.changeColor(list.get(0).getXPosition(), list.get(0).getYPosition(), Color.BLACK);
        board2.changeColor(list.get(1).getXPosition(), list.get(1).getYPosition(), Color.BLACK);
        board2.changeColor(list.get(2).getXPosition(), list.get(2).getYPosition(), Color.BLACK);
        while(check)
        {
            System.out.println("Select a place to hit on the board and then hit enter");
            scan.nextLine();
            oos.writeObject(sendX);
            oos.writeObject(sendY);
            if((boolean) ois.readObject()){
                board.changeColor(sendX, sendY, Color.RED);
                if((boolean) ois.readObject()){
                    JOptionPane.showMessageDialog(null, "You Won!");
                    System.exit(0);
                }
            }
            System.out.println("Now let's wait for Player 2");
            oos.writeObject(true);
            if((boolean) ois.readObject())
            {
                selectionx = (int) ois.readObject();
                selectiony = (int) ois.readObject();
                 if(gameLogic.hitOrMiss(selectionx,selectiony)==true)
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
                         JOptionPane.showMessageDialog(null, "You Lost");
                         check=gameLogic.endGame(list);
                         oos.writeObject(true);
                        }
                 }
                else if(gameLogic.hitOrMiss(selectionx,selectiony)==false)
                 {
                     board2.changeColor(selectionx, selectiony, Color.WHITE);
                     oos.writeObject(false);
                 }
                //board2.changeColor(selectionx, selectiony, Color.RED);
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


/**
 * Write a description of class Engine here.
 * 
 * @author Shlok Gharia & Akash Singhal  
 * @version (a version number or a date)
 */
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Iterator;
import java.util.Vector;
import java.util.*;
import java.awt.*;
public class Engine
{
    static GameLogic gameLogic = new GameLogic();
    static Scanner scan = new Scanner(System.in);
    static int sendX;
    static int sendY;
    static int selectionx;
    static int selectiony;
    static boolean check = true;
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
        oos.writeObject("You are connected to Player 1!");
        System.out.println((String)ois.readObject());
        scan.nextLine();
        System.out.println("Now let's begin!");
        System.out.println("First, let's start by having you select where you want to place your battle ships");
        oos.writeObject(selections());
        if((boolean) ois.readObject())
        {
            System.out.println("Let's start playing");
            oos.writeObject("Let's start playing");
        }
        SheetOfButtons board = new SheetOfButtons();
        ShipBoard board2 = new ShipBoard();
        while(check)
        {
            System.out.println("select a place to hit on the board and then hit enter");
            scan.nextLine();
            oos.writeObject(sendX);
            oos.writeObject(sendY);
            System.out.println("Now let's wait for Player 2");
            //oos.writeObject(true);
            //if((boolean) ois.readObject())
            //{
                selectionx = (int) ois.readObject();
                selectiony = (int) ois.readObject();
                if(gameLogic.hitOrMiss(selectionx,selectiony)==true)
                {
                    board2.changeColor(selectionx, selectiony, Color.RED);
                }
                else if(gameLogic.hitOrMiss(selectionx,selectiony)==false)
                {
                    board2.changeColor(selectionx, selectiony, Color.WHITE);
                }
                //board2.changeColor(selectionx, selectiony, Color.RED);
            //}
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
        ShipSelection ship = new ShipSelection();
        scan.nextLine();
        return true;
    }

    public static void getXY(int x, int y)
    {
        sendX= x;
        sendY= y;
    }
}


import java.io.*;
import java.net.*;
import java.util.*;
import java.awt.*;
import javax.swing.*;
public class Engine {
    static Scanner scan = new Scanner(System.in);
    static int selectionx;
    static int selectiony;
    static int sendX;
    static int sendY;
    static boolean check = false;
    static ShipSelection ship;
    static GameLogic gameLogic;
    static ArrayList <XYPoint> list;
    public static void main(String args[])throws Exception {

        Socket socket1;
        int portNumber = 1777;
        String str = "";

        socket1 = new Socket("192.168.1.253", portNumber);

        ObjectInputStream ois = new ObjectInputStream(socket1.getInputStream());

        ObjectOutputStream oos = new ObjectOutputStream(socket1.getOutputStream());
        oos.writeObject("You are connected to Player 2! Let's start to setup the game.");
        str= (String) ois.readObject();
        System.out.println(str);
        System.out.println("Now, let's wait till player 1 selects their ships");
        if((boolean) ois.readObject())
        {
            oos.writeObject(selections());
            gameLogic = new GameLogic(ship);
        }
        System.out.println((String) ois.readObject());
        System.out.println("---------------------------------------------------");
        MovesBoard board = new MovesBoard();
        ShipBoard board2 = new ShipBoard();
        board2.changeColor(list.get(0).getXPosition(), list.get(0).getYPosition(), Color.BLACK);
        board2.changeColor(list.get(1).getXPosition(), list.get(1).getYPosition(), Color.BLACK);
        board2.changeColor(list.get(2).getXPosition(), list.get(2).getYPosition(), Color.BLACK);
        while(!check)
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
                         oos.writeObject(true);
                         JOptionPane.showMessageDialog(null, "You Lost");
                         board.setVisible(false);
                         board2.setVisible(false);
                         System.exit(0);
                 }
                else{oos.writeObject(false);}
            }
            else if(gameLogic.hitOrMiss(selectionx,selectiony)==false)
            {
                board2.changeColor(selectionx, selectiony, Color.WHITE);
                oos.writeObject(false);
            }

            if((boolean) ois.readObject())
            {
                oos.writeObject(true);
                System.out.println("Select a place to hit on the board and then hit enter");
                scan.nextLine();
                oos.writeObject(sendX);
                oos.writeObject(sendY);
                if((boolean) ois.readObject()){
                    board.changeColor(sendX, sendY, Color.RED);
                    if((boolean) ois.readObject()){
                        JOptionPane.showMessageDialog(null, "You Won!");
                        board.setVisible(false);
                        board2.setVisible(false);
                        System.exit(0);
                    }
                }
                System.out.println("Now let's wait for Player 1");
            }
            check=gameLogic.endGame(list);
        }
        ois.close();
        oos.close();
        socket1.close();
    }

    public static boolean selections()
    {
        System.out.println("Alright, Now let's setup up the game. Please input your name");
        String name = scan.nextLine();
        System.out.println("Now, select 3 ships from the selection board (Press enter when done)");
        ship = new ShipSelection();
        scan.nextLine();
        ship.setVisible(false);
        list=ship.getPoints();
        return true;
    }

    public static void getXY(int x, int y)
    {
        sendX= x;
        sendY= y;
    }
}

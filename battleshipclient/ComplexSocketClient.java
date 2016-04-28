import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.*;
import java.awt.*;
public class ComplexSocketClient {
    static Scanner scan = new Scanner(System.in);
    static int selectionx;
    static int selectiony;
    static int sendX;
    static int sendY;
    static boolean check =true;
    public static void main(String args[])throws Exception {

        Socket socket1;
        int portNumber = 1777;
        String str = "";

        socket1 = new Socket(InetAddress.getLocalHost(), portNumber);

        ObjectInputStream ois = new ObjectInputStream(socket1.getInputStream());

        ObjectOutputStream oos = new ObjectOutputStream(
                socket1.getOutputStream());
        oos.writeObject("You are connected to Player 2!");

        str= (String) ois.readObject();
        System.out.println(str);
        System.out.println("Now, let's wait till player 1 selects their ships");
        if((boolean) ois.readObject())
        {
            oos.writeObject(selections());
        }
        System.out.println((String) ois.readObject());
        final SheetOfButtons board = new SheetOfButtons();
        ShipBoard board2 = new ShipBoard();
        while(check)
        {
            selectionx = (int) ois.readObject();
            selectiony = (int) ois.readObject();
            board2.changeColor(selectionx, selectiony, Color.RED);
           
            if((boolean) ois.readObject())
            {
                oos.writeObject(true);
                System.out.println("Select a place to hit on the board and then hit enter");
                scan.nextLine();
                oos.writeObject(sendX);
                oos.writeObject(sendY);
                System.out.println("Now let's wait for Player 1");
                //oos.writeObject(true);
            }
        }
        ois.close();
        oos.close();
        socket1.close();
    }

    public static boolean selections()
    {
        System.out.println("Alright, let's choose where to put your ships");
        String name = scan.nextLine();
        return true;
    }
    
     public static void getXY(int x, int y)
    {
        sendX= x;
        sendY= y;
    }
}

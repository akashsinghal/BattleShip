import java.awt.Color;
import javax.swing.*;
/**
 * Write a description of class GameLogic here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class GameLogic
{
    static int x;
    static int y;

    static ShipSelection checkBoard;
    public GameLogic (ShipSelection ship)
    {
        //x=xpos;
        //y=ypos;
        checkBoard = ship;
    }

    public boolean isTaken()
    {
        return true; 
    }

    public boolean hitOrMiss(int xPos, int yPos)
    {
        x=xPos;
        y=yPos;
        if(checkBoard.getSpecificBoard(xPos,yPos).getBackground()== Color.BLACK)
        {
            JOptionPane.showMessageDialog(null, "YOU WERE HIT!");
            return true;
        }
        else if(checkBoard.getSpecificBoard(xPos,yPos).getBackground()== Color.BLUE)
        {
            return false;
        }
        return false;
    }

//     public boolean endGame()
//     {
//         for(int x; x<
//         return true;
//     }
    //hello
}

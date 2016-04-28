import java.awt.Color;
import java.util.*;
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

<<<<<<< HEAD
    static ShipBoard playerBoard;
    static ShipBoard checkBoard;
=======
    static SheetOfButtons playerBoard;
    static SheetOfButtons checkBoard;
    //static ArrayList<XYPoint> points = new ArrayList<XYPoint>();
>>>>>>> origin/master
    public GameLogic ()
    {

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
            return true;
        }
        else if(checkBoard.getSpecificBoard(xPos,yPos).getBackground()== Color.BLUE)
        {
            return false;
        }
        return false;
    }

    public boolean endGame(){
        return true;
    }
    //hello
}

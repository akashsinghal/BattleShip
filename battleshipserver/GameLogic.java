
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

    static SheetOfButtons playerBoard;
    static SheetOfButtons checkBoard;
    public GameLogic ()
    {
        //x=xpos;
        //y=ypos;
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
    //hello
}

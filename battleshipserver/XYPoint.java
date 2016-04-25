
/**
 * Write a description of class XYPoint here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class XYPoint
{
    // instance variables - replace the example below with your own
    private int x;
    private int y;

    /**
     * Constructor for objects of class XYPoint
     */
    public XYPoint(int xP, int yP)
    {
        // initialise instance variables
        x = xP;
        y = yP;
    }

    /**
     * An example of a method - replace this comment with your own
     * 
     * @param  y   a sample parameter for a method
     * @return     the sum of x and y 
     */
    public int getX()
    {
        return x;
    }
    public int getY()
    {
        return y;
    }
    public void setXY(int xP, int yP)
    {
        x = xP;
        y = yP;
    }
}

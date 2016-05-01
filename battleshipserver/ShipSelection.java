import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.util.*;

/**
 * Houses all of the commands for the rows of buttons, from creating them to changing the colors
 * 
 * @author Shlok Gharia
 * @version 1.1
 */
public class ShipSelection extends JFrame
{
    class Button extends JButton {
        int r = 0, c = 0;

        public Button(int c, int y) {
            this.r = y;
            this.c = c;
        }
    }
    private static Button[][] board;
    private static ArrayList<XYPoint> points = new ArrayList<XYPoint>();
    public ShipSelection()
    {
        this.setTitle("Ship Selection Board Player 1");
        this.setLayout(new GridLayout(10,10));
        board = new Button[10][10];
        for(int rows=0; rows < board.length;rows++)
        {
            for(int column=0; column < board[rows].length; column++)
            {
                Border thickBorder = new LineBorder(Color.BLACK, 1);
                Button button = new Button(column,rows);
                button.setBackground(Color.BLUE);
                button.setOpaque(true);
                button.setBorder(thickBorder);
                button.addActionListener(new ActionListener()
                    {
                        public void actionPerformed(ActionEvent event)
                        {
                            draw(((Button) event.getSource()).c, ((Button) event.getSource()).r);
                        }

                        private void draw(int y, int x)
                        {
                            
                            if(board[x][y].getBackground() == Color.BLUE) 
                            {
                                board[x][y].setBackground(Color.BLACK);
                                points.add(new XYPoint(x, y));
                                //System.out.println(x + " , " + y);
                            }
                            else if(board[x][y].getBackground() == Color.BLACK)
                            {
                                board[x][y].setBackground(Color.BLUE);
                            }
                        }
                    });

                board[rows][column] = button;
                this.add(button);
            }
        }
        repaint();
        this.setSize(500,500);
        this.setVisible(true);
    }

    public ArrayList<XYPoint> getPoints()
    {
        return points;
    }

    public Button[][] getBoard()
    {
        return board;
    }

    public Button getSpecificBoard(int xPos, int yPos)
    {
        return board[xPos][yPos];
    }
}
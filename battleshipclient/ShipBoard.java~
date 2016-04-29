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

/**
 * Houses all of the commands for the rows of buttons, from creating them to changing the colors
 * 
 * @author Shlok Gharia
 * @version 1.1
 */
public class ShipBoard extends JFrame
{
    class Button extends JButton {
        int r = 0, c = 0;

        public Button(int c, int y) {
            this.r = y;
            this.c = c;
        }
    }

    private static Button[][] board;
    public ShipBoard()
    {
        this.setTitle("Ship Board");
        this.setLayout(new GridLayout(10,10));
        board = new Button[10][10];
        for(int rows=0; rows < board.length;rows++)
        {
            for(int column=0; column < board[rows].length; column++)
            {
                Border thickBorder = new LineBorder(Color.BLACK, 3);
                Button button = new Button(column,rows);
                button.setBackground(Color.BLUE);
                button.setOpaque(true);
                button.setBorder(thickBorder);
                board[rows][column] = button;
                this.add(button);
            }
        }
        repaint();
        this.setSize(500,500);
        this.setVisible(true);
    }
    
    public void changeColor(int x, int y, Color c)
    {
        board[y] [x].setBackground(c);
        repaint();
    }
}
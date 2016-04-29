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
public class MovesBoard extends JFrame
{
    class Button extends JButton {
        int r = 0, c = 0;

        public Button(int c, int y) {
            this.r = y;
            this.c = c;
        }
    }

    private static Button[][] board;
    static Engine eng = new Engine();
    public MovesBoard()
    {
         this.setTitle("Moves Board");
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
                button.addActionListener(new ActionListener()
                    {
                        public void actionPerformed(ActionEvent event)
                        {
                            draw(((Button) event.getSource()).c, ((Button) event.getSource()).r);;
                        }

                        private void draw(int y, int x)
                        {
                            eng.getXY(y, x);
                            if(board[x][y].getBackground() == Color.BLUE) 
                            {
                                board[x][y].setBackground(Color.WHITE);
                            }
                            else if(board[x][y].getBackground() == Color.GREEN)
                            {
                                board[x][y].setBackground(Color.RED);
                            }
                        }
                    });
                button.addActionListener(new ActionListener()
                    {
                        public void actionPerformed(ActionEvent event)
                        {
                            //erase (((Button) event.getSource()).c, ((Button) event.getSource()).r, event);
                        }

                        private void draw(int y, int x)
                        {
                            if(board[x][y].getBackground() == Color.BLUE) 
                            {
                                board[x][y].setBackground(Color.WHITE);
                            }
                            else if(board[x][y].getBackground() == Color.GREEN)
                            {
                                board[x][y].setBackground(Color.RED);
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
       public void changeColor(int x, int y, Color c)
    {
        board[y] [x].setBackground(c);
        repaint();
    }
}
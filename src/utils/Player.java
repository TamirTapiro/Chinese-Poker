package utils;

import game.Helper;
import game.PlayerListener;
import game.PlayerPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Player extends JPanel implements PlayerListener
{
    public static final int width = 725;
    public static final int height = 225;
    public static final String color ="0x1B263B"; //    0xD8DDEF original color
    public JButton[][] cardsArray;
    public Hand[] hands;
    //public int i,j;
    public String currentCard;
    public boolean playing;
    int counter;




    public Player()
    {
        hands  = new Hand[5];
        counter = 0;

        for(int k = 0 ; k< hands.length ; k++)
        {
            hands[k] = new Hand();
        }
        cardsArray = new JButton[5][5];

        this.setSize(width,height);
        this.setBackground(Color.decode(color));
        this.setLayout(new GridLayout(5,5));

        for(int i = 0; i < 5 ; i++)
        {
            for(int j=0 ; j < 5 ; j++)
            {
                cardsArray[i][j] = new JButton();
                cardsArray[i][j].setBorder(BorderFactory.createEmptyBorder());
                cardsArray[i][j].setFont(new Font("Ariel" ,Font.PLAIN,28));
                cardsArray[i][j].setName(i+","+j);
                cardsArray[i][j].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if(e.getSource() instanceof JButton)
                        {
                            int row = counter / 5;
                            String name = ((JButton) e.getSource()).getName();

                            if(((JButton) e.getSource()).getText() == "")
                            {
                                if(row == parseName(name,"row"))
                                {
                                    ((JButton) e.getSource()).setText(currentCard);
                                    if(((JButton) e.getSource()).getText().endsWith("♦") ||
                                            ((JButton) e.getSource()).getText().endsWith("♥"))
                                    {
                                        ((JButton) e.getSource()).setForeground(Color.red);
                                    }
                                    Card c = new Card(currentCard);
                                    int index_X = Integer.parseInt(((JButton) e.getSource()).getName().substring(2,3));
                                    Helper.btnArray[c.getCardShape()][(c.getCardValue()-1)].setBackground(Color.decode(Helper.color));
                                    //System.out.println(index_X +"   "+ ((JButton) e.getSource()).getName());
                                    hands[index_X].cards.add(new Card(currentCard));
                                    counter++;

                                    playing = false;
                                }
                                else
                                {
                                    JOptionPane.showMessageDialog(null,"You need to fill the current row, before jump to the others.","Error",JOptionPane.ERROR_MESSAGE);
                                }
                            }
                        }
                    }
                });
                this.add(cardsArray[i][j]);
                /*if(i > 0)
                {
                    cardsArray[i][j].setVisible(false); // only first row is visible
                }*/
            }
        }
    }

    public void disableButtons()
    {
        for(int i = 0; i < 5 ; i++)
        {
            for(int j = 0 ; j< 5; j++)
            {
                cardsArray[i][j].setEnabled(false);
            }
        }
    }

    public void activateButtons()
    {
        for(int i = 0; i < 5 ; i++)
        {
            for(int j = 0 ; j< 5; j++)
            {
                cardsArray[i][j].setEnabled(true);
            }
        }
    }


    public static  void main(String args[])
    {
        //Player.parseName("1,2");
    }
    public int parseName(String name, String opt)
    {
        // Name format is "X,Y" , X and Y are numbers.
        // opt is option to choose between getRow or getCol
        String row = name.substring(0,1);
        String col = name.substring(2,3);

        int x = Integer.parseInt(row);
        int y = Integer.parseInt(col);

        if(opt == "row" )
            return x;
        else if( opt == "col")
            return  y;
        else return -1;
        //System.out.println("row: " + x + ",col: " + y);

    }

    public void printHands()
    {
        for( int i = 0 ; i < 5 ; i++)
        {
            System.out.println("Hand " + (i +1) + ": ");
            System.out.println(hands[i]);
        }
    }



    @Override
    public void notifyPlayer()
    {
        System.out.println("notifying player...");

    }
}

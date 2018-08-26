package game;


import javax.swing.*;
import java.awt.*;

public class Helper extends JPanel
{
    public static final int SHAPES = 4;
    public static final int VALUES = 13;
    public static final String color= "0xA0A4B8";
    public static JButton[][] btnArray;

    JButton[][] arr;


    public Helper()
    {

        this.setLayout(new GridLayout(SHAPES,VALUES));
        btnArray = new JButton[SHAPES][VALUES];
        String[] shapesArray ={"♠","♦","♥","♣"};
        for( int i =0; i < SHAPES ; i++)
        {
            for( int j = 0; j < VALUES ; j++)
            {
                btnArray[i][j] = new JButton();
                String name = "";
                if(j<9) // numbers
                    name = (j+1) + " ";
                else if(j==9) // dont fit into the button
                    name = (j+1) + "";
                else if (j==10)
                    name = "J ";
                else if(j==11)
                    name = "Q ";
                else
                    name = "K ";

                name += shapesArray[i];


                btnArray[i][j] = new JButton(name);
                //btn.setBorder(BorderFactory.createEmptyBorder());
                //btn.setBorder(null);
                //btn.setText(name);

                //btn
                btnArray[i][j].setBackground(Color.white);
                if(i == 1 || i == 2)
                {
                    btnArray[i][j].setForeground(Color.red);
                }

                btnArray[i][j].setBorder(BorderFactory.createEmptyBorder());
                btnArray[i][j].setMargin(new Insets(0,0,0,0));
                this.add(btnArray[i][j]);
                //this.add(btn);
            }

        }

    }

}

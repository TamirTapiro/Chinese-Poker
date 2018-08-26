package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class PlayerPanel extends JPanel
{
    public static final int width = 725;
    public static final int height = 225;
    public static final String color ="0x1B263B"; //    0xD8DDEF original color
    public JButton[][] cardsArray;
    public int i,j;

    public PlayerPanel()
    {
        cardsArray = new JButton[5][5];

        this.setSize(width,height);
        this.setBackground(Color.decode(color));
        this.setLayout(new GridLayout(5,5));

        for(i = 0; i < 5 ; i++)
        {
            for( j=0 ; j < 5 ; j++)
            {
                cardsArray[i][j] = new JButton();
                cardsArray[i][j].setBorder(BorderFactory.createEmptyBorder());

                cardsArray[i][j].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e)
                    {


                    }
                });
                this.add(cardsArray[i][j]);
                if(i > 0)
                {
                    cardsArray[i][j].setVisible(false); // only first row is visible
                }
            }
        }


       //this.repaint();


    }

}


/*


try{
            img = ImageIO.read(new File("C:\\Users\\Tamir\\source\\repos\\Poker\\Poker\\imgs\\2_of_clubs.jpg"));
            //img.getScaledInstance(10,10,Image.SCALE_DEFAULT);

        }
       catch (Exception e){}
       JLabel picLabel = new JLabel(new ImageIcon(img));

for(int i = 0 ; i < 5 ; i++)
       {
           for (int j = 0 ; j < 5 ; j++)
           {
               cardsArray[i][j] = new JPanel(){
                   @Override
                   public void paintComponent(Graphics g)
                   {
                       super.paintComponent(g);
                       g.drawImage(img, 0, 0, null);
                   }
               };
               //cardsArray[i][j].setBackground()

               //cardsArray[i][j].add(picLabel);
               this.add(cardsArray[i][j]);
               //cardsArray[i][j].repaint();

               //this.add(cardsArray[i][j]);
           }
       }
 */
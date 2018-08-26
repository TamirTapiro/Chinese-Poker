package game;

import utils.Player;

import javax.swing.*;
import java.awt.*;

public class Board
{
    public static final int width = 800;
    public static final int height = 800;
    public static final String color = "0x1B263B";
    public static final String[] shapes = {"♠", "♥", "♦", "♣"};

    JFrame frame;
    JPanel mainPanel;
    Player p1,p2;
    Helper helper;
    Color c;
    Image image;
    JLabel[] labels;
    JLabel currentCard;

    public Board(Player p1, Player p2)
    {
        mainPanel = new JPanel();
        this.p1 = p1;
        this.p2 = p2;
        helper = new Helper();
        labels = new JLabel[4];
        currentCard = new JLabel();
        frame = new JFrame();
        frame.add(mainPanel);
        mainPanel.add(helper);
        setup();
        mainPanel.add(this.p1);
        mainPanel.add(this.p2);
        mainPanel.add(currentCard);
        frame.setVisible(true);
    }

    public void setup()
    {
        c = Color.decode(color);
        frame.setSize(width,height);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        //frame.setBackground(c);
        mainPanel.setBackground(c);
        mainPanel.setLayout(null);
        setupLabels();
        currentCard.setBounds((int)width/2 - 90, 275,180,90);
        currentCard.setFont(new Font("Ariel" ,Font.PLAIN,64));
        currentCard.setBackground(Color.white);
        currentCard.setOpaque(true);
        currentCard.setHorizontalAlignment(JLabel.CENTER);
        currentCard.setVerticalAlignment(JLabel.CENTER);
        helper.setBounds(10,630, width - 35, 125);
        p1.setBounds(30,30,Player.width,Player.height);
        p2.setBounds(30,150 + Player.height,Player.width,Player.height);

    }

    public void setupLabels()
    {
        int x = 30,y = 275,width = 160,height = 80;

        for (int i = 0 ; i < shapes.length; i++)
        {
            labels[i] = new JLabel(shapes[i]);
            labels[i].setBounds(x,y,width,height);
            Font labelFont = labels[i].getFont();
            String labelText = labels[i].getText();

            int stringWidth = labels[i].getFontMetrics(labelFont).stringWidth(labelText);
            int componentWidth = labels[i].getWidth();

            // Find out how much the font can grow in width.
            double widthRatio = (double)componentWidth / (double)stringWidth;

            int newFontSize = (int)(labelFont.getSize() * widthRatio);
            int componentHeight = labels[i].getHeight();

            // Pick a new font size so it will not be larger than the height of label.
            int fontSizeToUse = Math.min(newFontSize, componentHeight);

            // Set the label's font size to the newly determined size.
            labels[i].setFont(new Font(labelFont.getName(), Font.PLAIN, fontSizeToUse));
            if(i==0)
            {
                x += width;
            }
            else if(i==1)
            {
                x+= width*2 + 15;
            }
            else if(i==2)
            {
                x+=width;
            }

            if( i ==1 || i == 2)
            {
                labels[i].setForeground(Color.red);
            }
            else labels[i].setForeground(Color.white);
            mainPanel.add(labels[i]);
        }

    }

}

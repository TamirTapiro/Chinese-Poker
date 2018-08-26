package utils;

import javafx.scene.image.Image;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class Card
{
    private String cardShape;
    private int cardValue;


    public Card(int cardValue, String cardShape)
    {
        this.cardValue = cardValue;
        this.cardShape = cardShape;

    }

    public Card(String card)
    {
        String[] s = new String[2];
        s = card.split(" ");
        cardShape = s[1];
        if(s[0].equals("A")) cardValue = 1;
        else if (s[0].equals("J")) cardValue = 11;
        else if (s[0].equals("Q")) cardValue = 12;
        else if (s[0].equals("K")) cardValue = 13;
        else cardValue = Integer.valueOf(s[0]);
    }

    public String toString()
    {

        if (cardValue >=2 && cardValue <=10)
            return cardValue +" "+ cardShape;
        else if(cardValue == 11)
        {
            return "J " + cardShape;
        }
        else if(cardValue == 12)
        {
            return "Q " + cardShape;
        }
        else if(cardValue == 13)
        {
            return "K " + cardShape;
        }
        else
        {
            return "A " + cardShape;
        }
    }

    public int getCardValue()
    {
        return cardValue;
    }

    public int getCardShape()
    {
        switch (cardShape)
        {
            case "♠":
                return 0;
            case "♦":
                return 1;
            case "♥":
                return 2;
            case "♣":
                return 3;
            default:
                return -1;
        }
    }

}



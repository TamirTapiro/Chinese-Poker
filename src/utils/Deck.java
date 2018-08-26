package utils;

import java.util.ArrayList;
import java.util.Random;

public class Deck
{

    public final int DECK_SIZE = 52;
    public ArrayList<Card> deckOfCards;
    public String[] shapes = {"♠", "♦","♥" , "♣"};
    private static Deck deckInstance = null; // Singleton

    private Deck()
    {
        deckOfCards = new ArrayList<>(DECK_SIZE);
        for (int i =1; i<=13;i++)
        {
            for (int j=0; j<shapes.length ; j++)
            {
                Card c= new Card(i,shapes[j]);
                deckOfCards.add(c);
            }
        }
    }

    public static Deck getInstance()
    {
        if (deckInstance == null)
            deckInstance = new Deck();
        return deckInstance;
    }

    public void shuffle(int times) {
        Random rnd1 = new Random();
        Random rnd2 = new Random();
        for (int i = 0; i < times; i++) {
            swap(rnd1.nextInt(52), rnd2.nextInt(52));
        }
    }

    public void swap(int c1, int c2)
    {
        if(c1==c2)
        {
            c1 = (c1+1)%52;
        }
        Card tmp = deckOfCards.get(c1);
        deckOfCards.set(c1,deckOfCards.get(c2));
        deckOfCards.set(c2,tmp);
    }

    public Card takeCard()
    {
        Card c = deckInstance.deckOfCards.get(0);
        deckInstance.deckOfCards.remove(0);
        return c;
    }

    public String toString()
    {
        String str = "";
        for (Card c: deckInstance.deckOfCards)
        {
             str += c.toString() + "\n";
        }
        return str;
    }

}

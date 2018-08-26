package game;
import utils.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.EventListener;


public class Game
{

    public final static int HANDS = 5;
    public static ArrayList<Player> player;
    public static Card card;
    public static Deck deck = Deck.getInstance();
    public static Board board;
    public static int turn = 0;
    public static int counter = 0;
    public static int player1rank = 0;
    public static int player2rank = 0;





    public static void main(String[] args)
    {
        Game g = new Game();
        player = new ArrayList<Player>(2);
        player.add(0,new Player());
        player.add(1,new Player());
        deck.shuffle(128);
        board = new Board(player.get(0),player.get(1));

        //start();
        gameplay();

    }

    public static void gameplay(){
        start();
        while(true)
        {

            card = deck.takeCard();
            board.currentCard.setText(card.toString());
            if (card.toString().endsWith("♦") || card.toString().endsWith("♥"))
            {
                board.currentCard.setForeground(Color.red);
            }
            else
            {
                board.currentCard.setForeground(Color.black);
            }
            switch (turn)
            {
                case 0: // p1
                    player.get(0).playing = true;
                    player.get(0).currentCard = board.currentCard.getText();
                    while(player.get(0).playing == true)
                    {
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        player.get(1).disableButtons(); // disable the buttons of the second player
                    }
                    player.get(1).activateButtons();
                    turn = 1;
                    break;


                case 1:
                    player.get(1).playing = true;
                    player.get(1).currentCard = board.currentCard.getText();
                    while(player.get(1).playing == true)
                    {
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        player.get(0).disableButtons(); // disable the buttons of the second player
                    }
                    player.get(0).activateButtons();
                    turn = 0;
                    break;


            }
            counter++;
            if(counter == 40) // 50 cards = End of the game
                endGame();
        }

    }

    public static void start()
    {
        for(int i = 0 ; i < HANDS ; i++)
        {
            for (int j = 0 ; j < player.size(); j++)
            {
                card = deck.takeCard();
                board.currentCard.setText(card.toString());
                player.get(j).currentCard = card.toString();
                player.get(j).cardsArray[0][i].doClick();
                //Helper.btnArray[card.getCardShape()][card.getCardValue()-1].setBackground(Color.decode(Helper.color));
            }
        }
    }

    public static void endGame()
    {
        for (Player p: player)
        {
            for(int i = 0 ; i < p.hands.length ; i++)
            {
                p.hands[i].evaluate();
            }
        }
        for( int j = 0 ; j < 5; j ++)
        {
            System.out.println("Hand " + (j+1) + ":");
            System.out.println("Player 1: " + player.get(0).hands[j].rank +"\t\t Player 2: " + player.get(1).hands[j].rank);

            if(player.get(0).hands[j].compareTo(player.get(1).hands[j]) == 1)
            {
                player1rank++;
            }
            else if(player.get(0).hands[j].compareTo(player.get(1).hands[j]) == -1)
                player2rank++;
            else continue;
        }

        if(player1rank > player2rank)
            endMessage(1);
        else if(player1rank < player2rank)
            endMessage(2);
        else
            endMessage(0);

        System.exit(0);
    }

    public static void endMessage(int player)
    {
        String winner = "Player";
        switch (player)
        {
            case 1:
                winner = winner +=" 1 Is The WINNER!!!";
            case 2:
                winner += " 2 Is The WINNER!!!";
            default:
                winner = "It's A DROW!!!";
        }
        JOptionPane.showMessageDialog(null, "Score of the game: " + player1rank + "\t" + player2rank + ".",winner,JOptionPane.INFORMATION_MESSAGE);
    }



}

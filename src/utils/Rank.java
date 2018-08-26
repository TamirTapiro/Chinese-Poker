package utils;


import java.util.Arrays;

public class Rank
{
    public static final String[] rankLevels = {"High Card" ,"Pair"  , "Two pairs" ,"Three of a kind"  , "Straight" , "Flush" , "Full house" , "Four of a kind", "Straight flush" ,"Royal flush"};

    static final int SHAPES = 4;
    static final int VALUES = 13;
    static final int CARDS = 5;
    boolean [][] array = new boolean[SHAPES][VALUES];
    int[] hand;
    int pair; // value of the pair cards/
    int[] tow_pairs = new int[2];
    int three_of_a_kind;
    int straight;
    int flush;
    int[] full_house = new int[2];
    int four_of_a_kind;
    int straight_flush;
    boolean royal_flush;



    public Rank()
    {
        hand = new int[5];
        for(int i =0 ; i < SHAPES ; i++)
        {
            for (int j =0; j< VALUES; j ++)
            {
                array[i][j] = false;
            }
        }
        pair = 0;
        tow_pairs[0] = tow_pairs[1] = 0;
        three_of_a_kind =0;
        straight = 0;
        flush = 0;
        full_house[0] = full_house[1] = 0;
        four_of_a_kind =0;
        straight_flush = 0;
        royal_flush = false;
    }


    public String getRank()
    {
        if(pair > 0)
            return rankLevels[1];// + " : " + pair;
        else if (tow_pairs[0] > 0 )
            return rankLevels[2];// + " : 1) " + tow_pairs[0] + " , 2) " + tow_pairs[1];
        else if (three_of_a_kind > 0)
            return rankLevels[3];// + " : " + three_of_a_kind;
        else if (straight > 0)
            return rankLevels[4];// + " : " + straight;
        else if (flush > 0)
            return rankLevels[5];// + " : " + flush;
        else if (full_house[0] > 0)
            return rankLevels[6];// + " : " + "(" + full_house[0] + " , " + full_house[1] + ")";
        else if (four_of_a_kind > 0)
            return rankLevels[7];// + " : " + four_of_a_kind;
        else if (straight_flush > 0)
            return rankLevels[8];// + " : " + straight_flush;
        else  if (royal_flush)
            return rankLevels[9];
        else // nothing here
            return rankLevels [0];
    }
    public void setRank(Hand h)
    {
        if(h.cards.size() < 5)
        {
            System.out.println("To rank a hand you must have 5 crads.");
            return;
        }

        //else
        int i = 0;
        for (Card c: h.cards)
        {
            array[c.getCardShape()][c.getCardValue() - 1] = true;
            hand[i] = c.getCardValue();
        }
        Arrays.sort(hand);
        check();
    }


    void check()
    {
        valueCheck();
        setStraight();
        setFlush();
        setStraight_flush();
    }


    boolean setStraight_flush() // done.
    {
        // 1-5 , 2-6 , 3-7 , 4-8 , 5-9 , 6-10 , 7-Jack , 8-Queen , 9-King
        final int CARDS = 5;
        for (int i = 0 ; i < SHAPES ; i++)
        {
            for( int j = 0 ; j < 10 ; j++) // 10 options for Straight flush for each shape.
            {
                if(array[i][j] && array[i][j+1] && array[i][j+2] && array[i][j+3] && array[i][(j+4)%13])
                {
                    if(j == 9) // card number 10.
                    {
                        royal_flush = true;
                        return true;
                    }
                    else
                    {
                        straight_flush = j + 5; // the highest card of the straight.
                        return true;
                    }
                }
            }
        }
        return false;
    }

    boolean valueCheck() // done.
    {
        //this function checks for:
        //Four_of_a_kind , Three_of_a_kind , Pair , Two_pairs , Full_house.

        boolean flag = false;
        int[] counter = new int[VALUES]; // declaring counter.

        for(int i =0 ; i<VALUES ; i++) // init counter.
        {
            counter[i] = 0;
        }

        for(int i =0 ; i< SHAPES; i++)
        {
            for( int j = 0 ; j< VALUES ; j++)
            {
                if (array[i][j])
                {
                    counter[j]++;
                }
            }
        }

        for(int i =0 ; i<VALUES ; i++)
        {
            if(counter[i] == 4)
            {
                four_of_a_kind = i+1;
                return true;
            }

            if(counter[i] == 3)
            {
                three_of_a_kind = i+1;
            }

            if(counter[i] == 2)
            {
                if(flag == false) // this is the first pair detected.
                {
                    pair = i+1;
                    flag = true;
                    continue;
                }
                else // there is a first pair and now it is the second.
                {
                    tow_pairs[0] = i+1; // higher pair is first.
                    tow_pairs[1] = pair; // lower is the second.
                    pair = 0;
                }
            }
        }

        if(three_of_a_kind  > 0 && pair > 0) // check for full house (3&2).
        {
            full_house[0] = three_of_a_kind;
            full_house[1] = pair;
            three_of_a_kind = 0;
            pair = 0;
            return true;
        }

        if( pair > 0 || tow_pairs[0] > 0 || three_of_a_kind > 0 )
            return true;

        return false;
    }



    void setFlush()
    {
        if(flush > 0)
            return;

        int[] tmpShapes = new int[SHAPES];

        for(int i = 0; i < SHAPES ; i++)
        {
            for ( int j = 0; j < VALUES ; j++)
            {
                if(array[i][j])
                {
                    tmpShapes[i]++;
                    if(tmpShapes[i] == 5)
                    {
                        flush = j+1;
                        return ;
                    }
                }
            }
        }
    }


    void setStraight()
    {
        if(royal_flush || straight_flush >0)
            return;
        int counter = 0;
        for(int i = 0 ; i < 4 ; i ++)
        {
            System.out.println((hand[i]+1) + " " + hand[i+1]);
            if(hand[i]+1 == hand[i+1])
            {
                counter++;
            }
        }
        if(counter == 4)
            straight = hand[4];

        //check for 10-J-Q-K-A
        if(hand[0] == 1 && hand[1] == 10 && hand[2] == 11 && hand[3] == 12 && hand[4] == 13)
            straight = 14;
        System.out.println(counter + " " + straight);
    }

    public int checkHigh(int[] other)
    {
        if(hand[0] == 1 && other[0] != 1)
            return 1;
        else if(hand[0] != 1 && other [0] == 1)
            return -1;
        else
        for(int i = 0 ; i < hand.length - 1 ; i++) // -1 is because of the first check
        {
            if(this.hand[4-i] > other[4-i])
            {
                return 1;
            }
            else if (this.hand[4-i] < other[4-i])
            {
                return -1;
            }
            else
            {
                continue;
            }
        }
        return 0;
    }

}

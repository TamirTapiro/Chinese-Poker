package utils;

import java.util.ArrayList;

public class Hand implements Comparable<Hand>

{
    public ArrayList<Card> cards;
    public Rank rank;

    public Hand()
    {
        cards = new ArrayList<>(5);
        rank = new Rank();
    }

    public String toString()
    {
        String str = "";
        for (Card c: cards)
        {
            str += c.toString();
        }
        return str;
    }

    public void evaluate()
    {
        this.rank.setRank(this);
    }

    @Override
    public int compareTo(Hand other)
    {
        String myRank = this.rank.getRank();
        String otherRank = other.rank.getRank();

        int myIndex = -1;
        int otherIndex = -1;

        for (int i=0 ; i < Rank.rankLevels.length ; i++)
        {
            if (Rank.rankLevels[i] == myRank) {
                myIndex = i;
            }
            if (Rank.rankLevels[i] == otherRank) {
                otherIndex = i;
            }
            if(myIndex == otherIndex && myIndex != -1)
                break;
        }
        System.out.println("My Rank: "+ myRank + ", my index: " + myIndex);
        System.out.println("Other Rank: "+ otherRank + ", other index: " + otherIndex);
        if(myIndex > otherIndex)
            return 1;
        else if( otherIndex > myIndex)
            return -1;
        else //we both got same rank
        {
            switch (myIndex)
            {
                case 0:
                    if(this.rank.checkHigh(other.rank.hand) == 1)
                        return 1;
                    else if(this.rank.checkHigh(other.rank.hand) == -1)
                        return -1;
                    else
                    {
                        System.out.println("Its a drow!");
                        return 0;
                    }

                case 1:
                    if(this.rank.pair == other.rank.pair)
                    {
                        if (this.rank.checkHigh(other.rank.hand) == 1)
                            return 1;
                        else if (this.rank.checkHigh(other.rank.hand) == -1)
                            return -1;
                        else
                            {
                            System.out.println("Its a drow!");
                            return 0;
                        }
                    }
                    else if( this.rank.pair > other.rank.pair)
                        return 1;
                    else return -1;

                case 2:
                    if(this.rank.tow_pairs[0] > other.rank.tow_pairs[0]) // same high pair
                        return 1;
                    else if (this.rank.tow_pairs[0] < other.rank.tow_pairs[0])
                        return -1;
                    else // same high pair
                    {
                        if(this.rank.tow_pairs[1] > other.rank.tow_pairs[1])
                            return 1;
                        else if (this.rank.tow_pairs[1] < other.rank.tow_pairs[1])
                            return -1;
                        else // same low pair
                        {
                            if(this.rank.hand[4] > other.rank.hand[4])
                                return 1;
                            else if (this.rank.hand[4] < other.rank.hand[4])
                                return -1;
                            else return 0;
                        }
                    }

                case 3:
                    if(this.rank.three_of_a_kind > other.rank.three_of_a_kind)
                        return 1;
                    else return  -1;

                case 4:
                    if( this.rank.straight > other.rank.straight)
                        return 1;
                    else if ( this.rank.straight < other.rank.straight)
                        return -1;
                    else
                    {
                        System.out.println("Its a drow!!!");
                        return 0;
                    }

                case 5:
                    if(this.rank.checkHigh(other.rank.hand) == 1)
                        return 1;
                    else return -1;

                case 6:
                    if(this.rank.full_house[0] > other.rank.full_house[0])
                        return 1;
                    else return -1;

                case 7:
                    if(this.rank.four_of_a_kind > other.rank.four_of_a_kind)
                        return 1;
                    else return -1;

                case 8:
                    if(this.rank.straight_flush > other.rank.straight_flush)
                        return 1;
                    else return -1;
                case 9:
                    return 0;
            }
        }
        return 0;
    }
}

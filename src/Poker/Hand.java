package Poker;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class Hand implements Comparable<Hand> {

    private final HandRanking ranking;
    private final ArrayList<Rank> values = new ArrayList<>();

    public Hand(HandRanking r, Card[] cards, HashMap<Rank,Integer> map) {
        this.ranking = r;

        switch (this.ranking) {
            case HIGH_CARD, FLUSH -> {
                for (int i = 4; i >= 0; --i) {
                    values.add(cards[i].getRANK());
                }
            }
            case PAIR -> {
                Rank pairRank = getRankOfValue(2,map);
                values.add(pairRank);
                for (int i = 4; i >= 0; --i) {
                    Rank rank = cards[i].getRANK();
                    if (rank != pairRank)
                        values.add(rank);
                }
            }
            case TWO_PAIR -> {
                ArrayList<Rank> pairs = new ArrayList<>();
                for (Rank rank : map.keySet()) {
                    if (map.get(rank) == 2) {
                        pairs.add(rank);
                    }
                }
                Collections.sort(pairs);
                values.add(pairs.getLast());
                values.add(pairs.getFirst());

                for (int i = 4; i >= 0; --i) {
                    if (map.get(cards[i].getRANK()) == 1) {
                        values.add(cards[i].getRANK());
                    }
                }
            }
            case THREE_OF_A_KIND -> {
                Rank tripRank = getRankOfValue(3,map);
                values.add(tripRank);
                for (int i = 4; i >= 0; --i) {
                    Rank rank = cards[i].getRANK();
                    if (rank != tripRank)
                        values.add(rank);
                }
            }
            case STRAIGHT, STRAIGHT_FLUSH -> {
                if (cards[0].getRANK() == Rank.TWO && cards[4].getRANK() == Rank.ACE) {
                    values.add(Rank.FIVE);
                }
                else {
                    values.add(cards[4].getRANK());
                }
            }
            case FULL_HOUSE -> {
                values.add(getRankOfValue(3,map));
                values.add(getRankOfValue(2,map));
            }
            case FOUR_OF_A_KIND -> {
                values.add(getRankOfValue(4,map));
                values.add(getRankOfValue(1,map));
            }
            case ROYAL_FLUSH -> {
                values.add(Rank.ACE);
                values.add(Rank.KING);
                values.add(Rank.QUEEN);
                values.add(Rank.JACK);
                values.add(Rank.TEN);
            }
        }

    }

    public HandRanking getRanking() {return this.ranking;}

    private Rank getRankOfValue(int value, HashMap<Rank,Integer> map) {
        Rank high = null;

        for (Rank r : map.keySet()) {
            if (map.get(r) == value && (high == null || r.ordinal() > high.ordinal())) {
                high = r;
            }
        }
        if (high == null)
            throw new IllegalArgumentException("Invalid HashMap");
        return high;
    }

    @Override
    public int compareTo(Hand o) {
        if (this.ranking.ordinal() > o.ranking.ordinal()) {
            return 1;
        }
        else if (this.ranking.ordinal() < o.ranking.ordinal()) {
            return -1;
        }
        else {
            if (this.values.size() != o.values.size())
                throw new IllegalArgumentException("Invalid Kicker Values");

            for (int i = 0; i < values.size(); ++i) {
                if (this.values.get(i).ordinal() > o.values.get(i).ordinal()) {
                    return 1;
                }
                else if (this.values.get(i).ordinal() < o.values.get(i).ordinal()) {
                    return -1;
                }
            }
            return 0;
        }
    }

    @Override
    public String toString() {
        return this.ranking.toString();
    }
}

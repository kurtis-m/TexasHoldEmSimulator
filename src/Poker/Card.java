package Poker;

public class Card implements Comparable<Card> {
    private final Rank RANK;
    private final Suit SUIT;
    private final String NAME;

    public Card(Rank r, Suit s) {
        this.RANK = r;
        this.SUIT = s;
        this.NAME = RANK + " of " + SUIT;
    }

    @Override
    public int compareTo(Card o) {
        return Integer.compare(this.RANK.ordinal(), o.getRANK().ordinal());
    }

    public Rank getRANK() {return this.RANK;}
    public Suit getSUIT() {return this.SUIT;}

    @Override
    public String toString() {
        return this.NAME;
    }
}

package Poker;

public enum Rank {
    TWO,
    THREE,
    FOUR,
    FIVE,
    SIX,
    SEVEN,
    EIGHT,
    NINE,
    TEN,
    JACK,
    QUEEN,
    KING,
    ACE;

    public int toPrimeNum() {
        return switch (this) {
            case TWO ->     2;
            case THREE ->   3;
            case FOUR ->    5;
            case FIVE ->    7;
            case SIX ->     11;
            case SEVEN ->   13;
            case EIGHT ->   17;
            case NINE ->    19;
            case TEN ->     23;
            case JACK ->    29;
            case QUEEN ->   31;
            case KING ->    37;
            case ACE ->     41;
        };
    }

    @Override
    public String toString() {
        return switch (this) {
            case TWO ->     "Two";
            case THREE ->   "Three";
            case FOUR ->    "Four";
            case FIVE ->    "Five";
            case SIX ->     "Six";
            case SEVEN ->   "Seven";
            case EIGHT ->   "Eight";
            case NINE ->    "Nine";
            case TEN ->     "Ten";
            case JACK ->    "Jack";
            case QUEEN ->   "Queen";
            case KING ->    "King";
            case ACE ->     "Ace";
        };
    }
}

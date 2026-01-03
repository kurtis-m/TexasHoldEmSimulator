package Poker;

public enum HandRanking {
    HIGH_CARD,
    PAIR,
    TWO_PAIR,
    THREE_OF_A_KIND,
    STRAIGHT,
    FLUSH,
    FULL_HOUSE,
    FOUR_OF_A_KIND,
    STRAIGHT_FLUSH,
    ROYAL_FLUSH;

    @Override
    public String toString() {
        return switch (this) {
            case HIGH_CARD ->       "High Card";
            case PAIR ->            "Pair";
            case TWO_PAIR ->        "Two Pair";
            case THREE_OF_A_KIND -> "Three of a Kind";
            case STRAIGHT ->        "Straight";
            case FLUSH ->           "Flush";
            case FULL_HOUSE ->      "Full House";
            case FOUR_OF_A_KIND ->  "Four of a Kind";
            case STRAIGHT_FLUSH ->  "Straight Flush";
            case ROYAL_FLUSH ->     "Royal Flush";
        };
    }
}





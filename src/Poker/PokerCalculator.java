package Poker;

import java.util.Arrays;
import java.util.HashMap;

public class PokerCalculator {

    public static final int HAND_SIZE = 5;
    public static final int COMMUNITY_SIZE = 5;
    public static final int HOLE_SIZE = 2;

    public static Hand getTexasHand(Card[] arr1, Card[] arr2) {
        if (arr1.length + arr2.length != HAND_SIZE + HOLE_SIZE)
            throw new IllegalArgumentException("Invalid number of cards");

        Card[] sevenCards = combineArrays(arr1, arr2);
        Arrays.sort(sevenCards);
        Hand best = null;

        for (int[] c : combos) {
            // combination
            Card[] fiveCards = {
                    sevenCards[c[0]],
                    sevenCards[c[1]],
                    sevenCards[c[2]],
                    sevenCards[c[3]],
                    sevenCards[c[4]]
            };
            Hand current = getFiveCardHand(fiveCards);
            if ( best == null || current.compareTo(best) > 0) {
                best = current;
            }
        }
        // return the best hand out of 21 hands -> C(7,5) = 21
        return best;
    }

    public static Hand getFiveCardHand(Card[] fiveCards) {
        if (fiveCards.length != HAND_SIZE)
            throw new IllegalArgumentException("Invalid number of Cards");

        HashMap<Rank, Integer> map = new HashMap<>();
        for (Card c : fiveCards)
            map.put(c.getRANK(), map.getOrDefault(c.getRANK(), 0) + 1);

        HandRanking handRank;

        if (isRoyalFlush(fiveCards)) {
            handRank = HandRanking.ROYAL_FLUSH;
        }
        else if (isStraight(fiveCards) && isFlush(fiveCards)) {
            handRank = HandRanking.STRAIGHT_FLUSH;
        }
        else if (isFourOfAKind(map)) {
            handRank = HandRanking.FOUR_OF_A_KIND;
        }
        else if (isFullHouse(map)) {
            handRank = HandRanking.FULL_HOUSE;
        }
        else if (isFlush(fiveCards)) {
            handRank = HandRanking.FLUSH;
        }
        else if (isStraight(fiveCards)) {
            handRank = HandRanking.STRAIGHT;
        }
        else if (isThreeOfAKind(map)) {
            handRank = HandRanking.THREE_OF_A_KIND;
        }
        else if (getPairCount(map) == 2) {
            handRank = HandRanking.TWO_PAIR;
        }
        else if (getPairCount(map) == 1) {
            handRank = HandRanking.PAIR;
        }
        else {
            handRank = HandRanking.HIGH_CARD;
        }

        return new Hand(handRank, fiveCards, map);
    }

    public static void printCards(Card[] cards) {
        for (Card c : cards)
            System.out.println(c);
        System.out.println();
    }


    // hand calculation methods

    private static int getPairCount(HashMap<Rank,Integer> map) {
        int pairs = 0;
        for (Rank i : map.keySet())
            if (map.get(i) == 2) ++pairs;

        return pairs;
    }

    private static boolean isThreeOfAKind(HashMap<Rank,Integer> map) {
        return map.containsValue(3) && !map.containsValue(2);
    }

    private static boolean isFullHouse(HashMap<Rank,Integer> map) {
        return map.containsValue(3) && map.containsValue(2);
    }

    private static boolean isFourOfAKind(HashMap<Rank,Integer> map) {
        return map.containsValue(4);
    }

    private static boolean isFlush(Card[] hand) {
        for (int i = 0; i < hand.length-1; ++i) {
            if (hand[i].getSUIT() != hand[i+1].getSUIT())
                return false;
        }
        return true;
    }

    private static boolean isStraight(Card[] hand) {
        if (isAceLowStraight(hand)) return true;

        for (int i = 0; i < hand.length-1; ++i) {
            if (hand[i].getRANK().ordinal()+1 != hand[i+1].getRANK().ordinal())
                return false;
        }

        return true;
    }

    private static boolean isAceLowStraight(Card[] hand) {
        return  hand[0].getRANK() == Rank.TWO &&
                hand[1].getRANK() == Rank.THREE &&
                hand[2].getRANK() == Rank.FOUR &&
                hand[3].getRANK() == Rank.FIVE &&
                hand[4].getRANK() == Rank.ACE;
    }

    private static boolean isRoyalFlush(Card[] hand) {
        return  isStraight(hand) &&
                isFlush(hand) &&
                hand[0].getRANK() == Rank.TEN &&
                hand[4].getRANK() == Rank.ACE;
    }

    private static Card[] combineArrays(Card[] a, Card[] b) {
        Card[] output = new Card[a.length + b.length];

        System.arraycopy(a,0, output,0, a.length);
        System.arraycopy(b, 0, output, a.length, b.length);

        return output;
    }

    private final static int[][] combos = {
            {2,3,4,5,6}, // 0 1
            {1,3,4,5,6}, // 0 2
            {1,2,4,5,6}, // 0 3
            {1,2,3,5,6}, // 0 4
            {1,2,3,4,6}, // 0 5
            {1,2,3,4,5}, // 0 6
            {0,3,4,5,6}, // 1 2
            {0,2,4,5,6}, // 1 3
            {0,2,3,5,6}, // 1 4
            {0,2,3,4,6}, // 1 5
            {0,2,3,4,5}, // 1 6
            {0,1,4,5,6}, // 2 3
            {0,1,3,5,6}, // 2 4
            {0,1,3,4,6}, // 2 5
            {0,1,3,4,5}, // 2 6
            {0,1,2,5,6}, // 3 4
            {0,1,2,4,6}, // 3 5
            {0,1,2,4,5}, // 3 6
            {0,1,2,3,6}, // 4 5
            {0,1,2,3,5}, // 4 6
            {0,1,2,3,4}, // 5 6
    };
}

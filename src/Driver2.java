import Poker.*;

import java.util.HashMap;

public class Driver2 extends PokerCalculator {
    static void main() {
        final int iterations = 1000000;
        HashMap<HandRanking,Integer> resultsMap = new HashMap<>();

        for (int i = 0; i < iterations; ++i) {
            Deck deck = new Deck();
            Card[] community = deck.drawCards(5);
            Card[] hole = deck.drawCards(2);
            Hand hand = getTexasHand(community,hole);

            resultsMap.put(hand.getRanking(), resultsMap.getOrDefault(hand.getRanking(),0)+1);
        }

        for (HandRanking hr : resultsMap.keySet()) {
            double percent = (double) resultsMap.get(hr) / iterations * 100;
            System.out.printf("\n%s ->\t%.4f",hr,percent);
        }
    }
}

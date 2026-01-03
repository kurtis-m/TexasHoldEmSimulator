import Poker.*;

public class Driver extends PokerCalculator {
    static void main() {

        Deck deck = new Deck();

        Card[] community = deck.drawCards(COMMUNITY_SIZE);
        printCards(community);
        printLine();

        Card[] player1 = deck.drawCards(HOLE_SIZE);
        printCards(player1);
        Hand hand1 = getTexasHand(community,player1);
        System.out.println("Player 1 -> " + hand1);
        printLine();

        Card[] player2 = deck.drawCards(HOLE_SIZE);
        printCards(player2);
        Hand hand2 = getTexasHand(community,player2);
        System.out.println("Player 2 -> " + hand2);
        printLine();

        if (hand1.compareTo(hand2) > 0) {
            System.out.println("Player 1 wins");
        }
        else if (hand1.compareTo(hand2) < 0) {
            System.out.println("Player 2 Wins");
        }
        else {
            System.out.println("Split pot");
        }

    }

    static void printLine() {
        System.out.println("-----------------------------------------");
    }
}

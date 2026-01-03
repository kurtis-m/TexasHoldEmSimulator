package Poker;

import java.util.ArrayList;
import java.util.Collections;

public class Deck {
    private final ArrayList<Card> cards = new ArrayList<>();

    public Deck() {
        resetDeck();
    }

    public Card drawCard() {
        if (cards.isEmpty())
            throw new IllegalStateException("Can't draw from empty deck");

        return cards.removeFirst();
    }

    public Card[] drawCards(int amount) {
        if (!isDrawable(amount))
            throw new IllegalStateException("Not enough cards in deck");

        Card[] hand = new Card[amount];

        for (int i = 0; i < amount; ++i)
            hand[i] = drawCard();

        return hand;
    }

    public void resetDeck() {
        cards.clear();

        for (Suit s : Suit.values()) {
            for (Rank r : Rank.values()) {
                cards.add(new Card(r, s));
            }
        }

        shuffleDeck();
    }

    public void shuffleDeck() {
        Collections.shuffle(cards);
    }

    public int getDeckSize() {
        return this.cards.size();
    }

    public boolean isDrawable(int amount) {
        return cards.size() >= amount;
    }
}

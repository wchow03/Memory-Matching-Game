package model;

import java.util.ArrayList;

// Represents a list of Cards
public class Hand {
    private ArrayList<Card> hand;

    // EFFECTS: creates an empty list of cards
    public Hand() {
        this.hand = new ArrayList<>();
    }

    // REQUIRES: a Card object
    // MODIFIES: this
    // EFFECTS: adds card to list of cards
    public void addCard(Card card) {
        hand.add(card);
    }

    // REQUIRES: a Card object
    // MODIFIES: this
    // EFFECTS: removes card from list of cards
    public void removeCard(Card card) {
        hand.remove(card);
    }

    public char getCardAt(int i) {
        return hand.get(i).getLetter();
    }

    public ArrayList<Card> getHand() {
        return hand;
    }
}

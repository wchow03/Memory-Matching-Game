package model;

import java.util.ArrayList;
import java.util.Collections;

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
    // EFFECTS: removes card at specified index
    public void removeCard(int i) {
        hand.remove(i);
    }

    // MODIFIES: this
    // EFFECTS: creates a list of cards with n cards
    // and n/2 pairs that have the same letter
    public void createHand(int n) {
        int l = 65;
        for (int i = 0; i < n; i++) {
            Card card = new Card((char)l);
            hand.add(card);
            hand.add(card);
            l++;
        }
        Collections.shuffle(hand);
    }

    public char getCardAt(int i) {
        return hand.get(i).getLetter();
    }

    public ArrayList<Card> getHand() {
        return hand;
    }

    public int getHandSize() {
        return hand.size();
    }
}

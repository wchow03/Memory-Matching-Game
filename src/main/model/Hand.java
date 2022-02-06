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

    // REQUIRES: two different integers
    // MODIFIES: this
    // EFFECTS: if cards match, returns true and removes the cards from hand,
    // otherwise returns false
    public void compareCards(int i1, int i2) {
        // fix you can't choose two same index's
        char letter1 = getCardAt(i1);
        char letter2 = getCardAt(i2);
        if (letter1 == letter2) {
            if (i1 > i2) {
                removeCard(i1);
                removeCard(i2);
            } else {
                removeCard(i2);
                removeCard(i1);
            }
            System.out.println("It's a match!");
        } else {
            System.out.println("Not a match.");
        }
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

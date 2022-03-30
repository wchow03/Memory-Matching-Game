package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.Collections;

// Represents a list of Cards
public class Hand implements Writable {
    private ArrayList<Card> hand;
    private ArrayList<Integer> matchedCards;

    // EFFECTS: creates an empty list of cards
    public Hand() {
        this.hand = new ArrayList<>();
        this.matchedCards = new ArrayList<>();
    }

    // EFFECTS: creates an empty list of cards and index of cards that have been matched
    public Hand(ArrayList<Integer> matchedCards) {
        this.hand = new ArrayList<>();
        this.matchedCards = matchedCards;
    }

    // REQUIRES: a Card object
    // MODIFIES: this
    // EFFECTS: adds card to list of cards
    public void addCard(Card card) {
        EventLog.getInstance().logEvent(new Event("Card added to hand with value " + card.getLetter()));
        hand.add(card);
    }

    // REQUIRES: a Card object
    // MODIFIES: this
    // EFFECTS: removes card at specified index
    public void removeCard(int i) {
        EventLog.getInstance().logEvent(new Event("Card removed from hand with value " + hand.get(i).getLetter()));
        hand.remove(i);
    }

    // MODIFIES: this
    // EFFECTS: creates a list of cards with n*2 cards
    // and n pairs that have the same letter
    public void createHand(int n) {
        int l = 65;
        for (int i = 0; i < n; i++) {
            Card card = new Card((char)l);
            hand.add(card);
            hand.add(card);
            EventLog.getInstance().logEvent(new Event("Card added to hand with value " + card.getLetter()));
            EventLog.getInstance().logEvent(new Event("Card added to hand with value " + card.getLetter()));
            l++;
        }
        Collections.shuffle(hand);
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("hand", handToJson());
        if (!matchedCards.isEmpty()) {
            json.put("Matched cards", matchedCardsToJson());
        }
        return json;
    }

    // EFFECTS: returns cards in hand as JSON array
    public JSONArray handToJson() {
        JSONArray jsonArray = new JSONArray();
        for (Card c : hand) {
            jsonArray.put(c.toJson());
        }
        return jsonArray;
    }

    // EFFECTS; returns indexes of matched cards to JSON array
    public JSONArray matchedCardsToJson() {
        JSONArray jsonArray = new JSONArray();
        for (Integer matchedCard : matchedCards) {
            jsonArray.put(matchedCard);
        }
        return jsonArray;
    }

    public char getCardAt(int i) {
        return hand.get(i).getLetter();
    }

    public ArrayList<Card> getHand() {
        return hand;
    }

    public ArrayList<Integer> getMatchedCards() {
        return matchedCards;
    }

    public void setMatchedCards(ArrayList<Integer> matchedCards) {
        this.matchedCards = matchedCards;
    }

    public int getHandSize() {
        return hand.size();
    }
}

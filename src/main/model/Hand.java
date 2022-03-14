package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;
import ui.GamePanel;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

// Represents a list of Cards
public class Hand implements Writable {
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
    // EFFECTS: creates a list of cards with n*2 cards
    // and n pairs that have the same letter
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

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("hand", handToJson());
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

package model;

import org.json.JSONObject;
import persistence.Writable;

// Represents a card having an index and a capital letter
public class Card implements Writable {
    private final char letter;

    // REQUIRES: any character
    // EFFECTS: constructs a card that has a capital letter
    public Card(char letter) {
        this.letter = letter;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("cardLetter", String.valueOf(getLetter()));
        return json;
    }

    public char getLetter() {
        return letter;
    }
}

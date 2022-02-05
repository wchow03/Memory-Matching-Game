package model;

// Represents a card having an index and a capital letter
public class Card {
    private final char letter;

    // REQUIRES: any capital letter
    // EFFECTS: constructs a card that has a capital letter
    public Card(char letter) {
        this.letter = letter;
    }

    public char getLetter() {
        return letter;
    }
}

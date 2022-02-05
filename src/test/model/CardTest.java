package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CardTest {
    @Test
    void testConstructor() {
        Card myCard = new Card('A');
        assertEquals('A', myCard.getLetter());
    }
}
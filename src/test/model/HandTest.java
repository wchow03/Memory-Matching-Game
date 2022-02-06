package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class HandTest {
    private Hand hand;

    @BeforeEach
    void runBefore() {
        hand = new Hand();
    }

    @Test
    void testConstructor() {
        ArrayList<Card> cards = hand.getHand();
        assertTrue(cards.isEmpty());
        assertEquals(0, hand.getHandSize());
    }

    @Test
    void testAddOneCard() {
        Card c = new Card('A');
        hand.addCard(c);
        assertEquals('A', hand.getCardAt(0));

        ArrayList<Card> cards = hand.getHand();
        assertEquals(1, cards.size());
        assertEquals(1, hand.getHandSize());
    }

    @Test
    void testAddMultipleCards() {
        Card c1 = new Card('A');
        Card c2 = new Card('B');
        Card c3 = new Card('C');
        hand.addCard(c1);
        hand.addCard(c2);
        hand.addCard(c3);
        assertEquals('A', hand.getCardAt(0));
        assertEquals('B', hand.getCardAt(1));
        assertEquals('C', hand.getCardAt(2));

        ArrayList<Card> cards = hand.getHand();
        assertEquals(3, cards.size());
        assertEquals(3, hand.getHandSize());
    }

    @Test
    void testRemoveOneCard() {
        Card c1 = new Card('A');
        hand.addCard(c1);
        hand.removeCard(0);
        ArrayList<Card> cards = hand.getHand();
        assertTrue(cards.isEmpty());
        assertEquals(0, hand.getHandSize());
    }

    @Test
    void testRemoveMultipleCards() {
        Card c1 = new Card('A');
        Card c2 = new Card('B');
        Card c3 = new Card('C');
        hand.addCard(c1);
        hand.addCard(c2);
        hand.addCard(c3);

        hand.removeCard(0);
        assertEquals('B', hand.getCardAt(0));
        assertEquals('C', hand.getCardAt(1));

        ArrayList<Card> cards = hand.getHand();
        assertEquals(2, cards.size());
        assertEquals(2, hand.getHandSize());

        hand.removeCard(1);
        assertEquals('B', hand.getCardAt(0));

        assertEquals(1, cards.size());
        assertEquals(1, hand.getHandSize());
    }

    @Test
    void testCreateHandTwelveCards() {
        hand.createHand(6);
        assertEquals(12, hand.getHandSize());
    }

    @Test
    void testCreateHandTwentyCards() {
        hand.createHand(10);
        assertEquals(20, hand.getHandSize());
    }

}

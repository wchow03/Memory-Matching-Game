package persistence;

import model.Card;
import model.Hand;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonReaderTest {

    // Method taken from testReaderNonExistentFile from
    // https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/fileNonExistent.json");
        try {
            Hand hand = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // expected
        }
    }

    @Test
    void testReaderHand() {
        JsonReader reader = new JsonReader("./data/testReaderHand.json");
        try {
            Hand hand = reader.read();
            List<Card> cards = hand.getHand();
            assertEquals('C', cards.get(0).getLetter());
            assertEquals('D', cards.get(1).getLetter());
            assertEquals('D', cards.get(2).getLetter());
            assertEquals('C', cards.get(3).getLetter());
            assertEquals(4, cards.size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderHandAndMatchedCards() {
        JsonReader reader = new JsonReader("./data/testReaderHandAndMatchedCards.json");
        try {
            Hand hand = reader.read();
            List<Card> cards = hand.getHand();
            List<Integer> matchedCards = hand.getMatchedCards();
            assertEquals('C', cards.get(0).getLetter());
            assertEquals('D', cards.get(1).getLetter());
            assertEquals('D', cards.get(2).getLetter());
            assertEquals('C', cards.get(3).getLetter());
            assertEquals(4, cards.size());

            assertEquals(0, matchedCards.get(0));
            assertEquals(3, matchedCards.get(1));
            assertEquals(2, matchedCards.size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}

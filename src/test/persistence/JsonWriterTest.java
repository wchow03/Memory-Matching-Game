package persistence;

import model.Card;
import model.Hand;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonWriterTest {

    // Method taken from JsonWriterTest class in
    // https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
    @Test
    void testWriterInvalidFile() {
        try {
            Hand hand = new Hand();
            JsonWriter writer = new JsonWriter("./data/\2illegalName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            //expected
        }
    }

    @Test
    void testWriterHand() {
        try {
            Hand hand = new Hand();
            Card c1 = new Card('B');
            Card c2 = new Card('A');
            Card c3 = new Card('B');
            Card c4 = new Card('A');
            hand.addCard(c1);
            hand.addCard(c2);
            hand.addCard(c3);
            hand.addCard(c4);
            JsonWriter writer = new JsonWriter("./data/testWriterHand.json");
            writer.open();
            writer.write(hand);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterHand.json");
            hand = reader.read();
            List<Card> cards = hand.getHand();
            assertEquals('B', cards.get(0).getLetter());
            assertEquals('A', cards.get(1).getLetter());
            assertEquals('B', cards.get(2).getLetter());
            assertEquals('A', cards.get(3).getLetter());
            assertEquals(4, cards.size());
        } catch (IOException e) {
            fail("Exception not expected");
        }
    }
}

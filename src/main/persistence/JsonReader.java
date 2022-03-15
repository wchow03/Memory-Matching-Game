package persistence;

import model.Card;
import model.Hand;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Stream;

// Modelled after JsonReader class in
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
// Represents a reader that reads a hand from JSON data from file
public class JsonReader {
    private String source;

    // EFFECTS" constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads hand from file and returns it,
    // throws IOException if an error occurs when reading data from file
    public Hand read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseHand(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    public String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }
        return contentBuilder.toString();
    }

    // EFFECTS: parses hand from JSON object and returns it
    private Hand parseHand(JSONObject jsonObject) {
        Hand hand = new Hand();
        addCards(hand, jsonObject);
        if (jsonObject.length() == 2) {
            addMatchedCards(hand, jsonObject);
        }
        return hand;
    }

    // MODIFIES: hand
    // EFFECTS: parses cards from JSON object and adds them to hand
    private void addCards(Hand hand, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("hand");
        for (Object json : jsonArray) {
            JSONObject nextCard = (JSONObject) json;
            addCard(hand, nextCard);
        }
    }

    // MODIFIES: hand
    // EFFECTS: parses card from JSON object and adds it to hand
    private void addCard(Hand hand, JSONObject jsonObject) {
        String letter = jsonObject.getString("cardLetter");
        Card c = new Card(letter.charAt(0));
        hand.addCard(c);
    }

    // MODIFIES: hand
    // EFFECTS: parses integer from JSON object of matched cards and adds it to array of integers,
    // then adds to hand
    private void addMatchedCards(Hand hand, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("Matched cards");
        ArrayList<Integer> matchedCards = new ArrayList<>();
        for (Object json : jsonArray) {
            matchedCards.add((Integer) json);
        }
        hand.setMatchedCards(matchedCards);
    }
}

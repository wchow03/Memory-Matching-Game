package ui;

import model.Hand;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;

public class OptionPanel extends JPanel implements ActionListener {
    private static final String SAVE_FILE = "./data/saveGame.json";
    private Hand hand;
    private ArrayList<Integer> matchedCards;
    private JButton saveButton;
    private JsonWriter jsonWriter = new JsonWriter(SAVE_FILE);

    public OptionPanel(Hand hand, ArrayList<Integer> matchedCards) {
        this.matchedCards = matchedCards;
        this.hand = hand;
        this.setLayout(new BorderLayout());
        this.setBounds(0, 0, 600, 100);

        saveButton = new JButton("Save");
        saveButton.setFocusable(false);
        saveButton.addActionListener(this);

        this.add(saveButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == saveButton) {
            Collections.sort(matchedCards);
            saveGame();
        }
    }

    // Method modelled after saveWorkRoom in
    // https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
    // EFFECTS: saves game to file
    private void saveGame() {
        try {
            jsonWriter.open();
            jsonWriter.write(hand);
            jsonWriter.close();
            System.out.println("Game saved to " + SAVE_FILE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to " + SAVE_FILE);
        }
    }
}

package ui;

import model.EventLog;
import model.Hand;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;

// Represents the option panel at top of board
public class OptionPanel extends JPanel implements ActionListener {
    private static final String SAVE_FILE = "./data/saveGame.json";
    private BoardUI board;
    private Hand hand;
    private ArrayList<Integer> matchedCards;
    private JButton saveButton;
    private JButton resetButton;
    private JsonWriter jsonWriter = new JsonWriter(SAVE_FILE);

    // MODIFIES: this
    // EFFECTS: creates the save button
    public OptionPanel(Hand hand, BoardUI board) {
        this.board = board;
        this.hand = hand;
        this.matchedCards = hand.getMatchedCards();
        this.setLayout(new GridLayout(1, 2));

        saveButton = new JButton("Save");
        saveButton.setBounds(0, 0, 300, 100);
        saveButton.setFocusable(false);
        saveButton.addActionListener(this);

        resetButton = new JButton("Reset");
        resetButton.setBounds(300, 0, 300, 100);
        resetButton.setFocusable(false);
        resetButton.addActionListener(this);

        this.add(saveButton);
        this.add(resetButton);
    }

    // EFFECTS: save game if save button pressed.
    // resets game to MenuUI if reset button pressed
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == saveButton) {
            Collections.sort(matchedCards);
            saveGame();
        }
        if (e.getSource() == resetButton) {
            for (int i = hand.getHandSize() - 1; i >= 0; i--) {
                hand.removeCard(i);
            }
            remove(board);
            board.dispose();
            new MenuUI();
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

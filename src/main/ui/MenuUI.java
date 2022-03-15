package ui;

import model.Hand;
import persistence.JsonReader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

// Represents the menu window
public class MenuUI extends JFrame implements ActionListener {
    private static final String SAVE_FILE = "./data/saveGame.json";
    private JRadioButton easyButton;
    private JRadioButton mediumButton;
    private JRadioButton hardButton;
    private JButton loadButton;
    private JButton startButton;
    private ButtonGroup mode;
    private Hand hand;
    private JsonReader jsonReader = new JsonReader(SAVE_FILE);

    // MODIFIES: this
    // EFFECTS: creates the menu window
    public MenuUI() {
        this.setTitle("Menu");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(500, 125);
        this.setResizable(false);
        this.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));

        createModeButtons();
        createLoadStartButtons();
        this.setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: creates mode buttons
    private void createModeButtons() {
        easyButton = new JRadioButton("Easy (12 cards)");
        mediumButton = new JRadioButton("Medium (16 cards)");
        hardButton = new JRadioButton("Hard (20 cards)");

        easyButton.setActionCommand("Easy");
        mediumButton.setActionCommand("Medium");
        hardButton.setActionCommand("Hard");

        easyButton.setFocusable(false);
        mediumButton.setFocusable(false);
        hardButton.setFocusable(false);

        mode = new ButtonGroup();
        mode.add(easyButton);
        mode.add(mediumButton);
        mode.add(hardButton);

        this.add(easyButton);
        this.add(mediumButton);
        this.add(hardButton);
    }

    // MODIFIES: this
    // EFFECTS: creates load and start buttons
    private void createLoadStartButtons() {
        loadButton = new JButton("Load");
        startButton = new JButton("Start");

        loadButton.setFocusable(false);
        startButton.setFocusable(false);

        loadButton.addActionListener(this);
        startButton.addActionListener(this);

        this.add(loadButton);
        this.add(startButton);
    }

    // MODIFIES: hand
    // EFFECTS: creates a new BoardUI depending on which mode is selected.
    // if load is selected, loads game from last save, creating a BoardUI with the saved hand
    @Override
    public void actionPerformed(ActionEvent e) {
        this.dispose();
        if (e.getSource() == startButton) {
            String difficulty = mode.getSelection().getActionCommand();
            if (difficulty.equals("Easy")) {
                new BoardUI(6);
            } else if (difficulty.equals("Medium")) {
                new BoardUI(8);
            } else if (difficulty.equals("Hard")) {
                new BoardUI(10);
            }
        } else if (e.getSource() == loadButton) {
            loadGame();
            new BoardUI(hand);
        }
    }

    // Method modelled after loadWorkRoom
    // https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
    // MODIFIES: this
    // EFFECTS: loads saved game from file
    private void loadGame() {
        try {
            hand = jsonReader.read();
            System.out.println("Game loaded from " + SAVE_FILE);
        } catch (IOException e) {
            System.out.println("Unable to read from " + SAVE_FILE);
        }
    }
}

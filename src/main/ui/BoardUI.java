package ui;

import model.Event;
import model.EventLog;
import model.Hand;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

// Represents the board where game is played and option to save is available
public class BoardUI extends JFrame {
    private Hand hand;
    private ArrayList<Integer> matchedCards;
    private OptionPanel optionPanel;
    private GamePanel gamePanel;

    // EFFECTS: creates a board with option and game panel
    public BoardUI(int cards) {
        setUp();
        matchedCards = new ArrayList<>();
        hand = new Hand(matchedCards);

        optionPanel = new OptionPanel(hand, this);
        gamePanel = new GamePanel(cards, hand);

        addAndSetVisible();
    }

    // EFFECTS: creates a board with option and game panel
    public BoardUI(Hand hand) {
        setUp();
        matchedCards = hand.getMatchedCards();

        optionPanel = new OptionPanel(hand, this);
        gamePanel = new GamePanel(hand);

        addAndSetVisible();
    }

    // MODIFIES: this
    // EFFECTS: sets up frame of board
    private void setUp() {
        this.setTitle("Memory Game");
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                printLog(EventLog.getInstance());
                System.exit(0);
            }
        });
        this.setSize(600, 650);
        this.setResizable(false);
    }

    // MODIFIES: this
    // EFFECTS: adds option and game panel to frame and sets visible to true
    private void addAndSetVisible() {
        this.add(optionPanel, BorderLayout.NORTH);
        this.add(gamePanel);

        this.setVisible(true);
    }

    public void printLog(EventLog el) {
        for (Event next : el) {
            System.out.println(next.toString() + "\n");
        }
    }
}

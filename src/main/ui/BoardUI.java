package ui;

import model.Hand;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class BoardUI extends JFrame {
    private Hand hand;
    private ArrayList<Integer> matchedCards;
    private OptionPanel optionPanel;
    private GamePanel gamePanel;

    public BoardUI(int cards) {
        setUp();
        matchedCards = new ArrayList<>();
        hand = new Hand(matchedCards);

        optionPanel = new OptionPanel(hand);
        gamePanel = new GamePanel(cards, hand);

        addAndSetVisible();
    }

    public BoardUI(Hand hand) {
        this.setTitle("Memory Game");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(600, 650);
        this.setResizable(false);
        matchedCards = hand.getMatchedCards();

        optionPanel = new OptionPanel(hand);
        gamePanel = new GamePanel(hand);

        this.add(optionPanel, BorderLayout.NORTH);
        this.add(gamePanel);

        this.setVisible(true);
    }

    private void setUp() {
        this.setTitle("Memory Game");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(600, 650);
        this.setResizable(false);
    }

    private void addAndSetVisible() {
        this.add(optionPanel, BorderLayout.NORTH);
        this.add(gamePanel);

        this.setVisible(true);
    }
}

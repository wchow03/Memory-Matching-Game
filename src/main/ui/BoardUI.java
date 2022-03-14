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
        this.setTitle("Memory Game");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(600, 650);
        this.setResizable(false);

        matchedCards = new ArrayList<>();
        hand = new Hand(matchedCards);

        optionPanel = new OptionPanel(hand, matchedCards);
        gamePanel = new GamePanel(cards, hand, matchedCards);

        this.add(optionPanel, BorderLayout.NORTH);
        this.add(gamePanel);

        this.setVisible(true);
    }
}

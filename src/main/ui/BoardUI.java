package ui;

import model.Hand;

import javax.swing.*;
import java.awt.*;

public class BoardUI extends JFrame {
    private Hand hand;
    private OptionPanel optionPanel;
    private GamePanel gamePanel;

    public BoardUI(int cards) {
        this.setTitle("Memory Game");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(600, 650);
        this.setResizable(false);

        hand = new Hand();

        optionPanel = new OptionPanel(hand);
        gamePanel = new GamePanel(cards, hand);

        this.add(optionPanel, BorderLayout.NORTH);
        this.add(gamePanel);

        this.setVisible(true);
    }
}

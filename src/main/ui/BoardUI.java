package ui;

import javax.swing.*;
import java.awt.*;

public class BoardUI extends JFrame {
    private OptionPanel optionPanel;
    private GamePanel gamePanel;

    public BoardUI(int cards) {
        this.setTitle("Memory Game");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(600, 650);
        this.setResizable(false);

        optionPanel = new OptionPanel();
        gamePanel = new GamePanel(cards);

        this.add(optionPanel, BorderLayout.NORTH);
        this.add(gamePanel);

        this.setVisible(true);
    }
}

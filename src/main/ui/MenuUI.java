package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

// Represents the menu window
public class MenuUI extends JFrame implements ActionListener {
    private JRadioButton easyButton;
    private JRadioButton mediumButton;
    private JRadioButton hardButton;
    private JButton loadButton;
    private JButton startButton;
    private ButtonGroup mode;

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

    @Override
    public void actionPerformed(ActionEvent e) {
        String difficulty = mode.getSelection().getActionCommand();
        this.dispose();
        if (e.getSource() == startButton) {
            if (difficulty.equals("Easy")) {
                new BoardUI(6);
            } else if (difficulty.equals("Medium")) {
                new BoardUI(8);
            } else if (difficulty.equals("Hard")) {
                new BoardUI(10);
            }
        } else if (e.getSource() == loadButton) {
            System.out.println("Loading game");
        }
    }
}

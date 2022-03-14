package ui;

import model.Card;
import model.Hand;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class GamePanel extends JPanel implements ActionListener {
    private Hand hand = new Hand();
    private JButton[] buttons;
    private ArrayList<Integer> selectedCards = new ArrayList<>();
    private int cardsClicked = 0;

    public GamePanel(int cards) {
        int row = cards / 2;
        int column = 4;
        this.setLayout(new GridLayout(row, column));
        buttons = new JButton[cards * 2];

        hand.createHand(cards);
        createCardButtons(hand.getHandSize());
    }

    private void createCardButtons(int handSize) {
        for (int i = 0; i < handSize; i++) {
            buttons[i] = new JButton("?");
            buttons[i].setFont(new Font("Arial", Font.BOLD, 50));
            buttons[i].setBackground(new Color(0xdfdbff));
            buttons[i].setFocusable(false);
            buttons[i].addActionListener(this);
            this.add(buttons[i]);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < hand.getHandSize(); i++) {
            if (e.getSource() == buttons[i]) {
                if (cardsClicked == 2) {
                    compareCards();
                }

                System.out.println(hand.getCardAt(i));
                buttons[i].setText(Character.toString(hand.getCardAt(i)));
                selectedCards.add(i);
                cardsClicked++;
                System.out.println(cardsClicked);
            }
        }
    }

    public void compareCards() {
        int firstCardIndex = selectedCards.get(0);
        int secondCardIndex = selectedCards.get(1);

        System.out.println("2 cards selected");

        if (!(hand.getCardAt(selectedCards.get(0)) == hand.getCardAt(selectedCards.get(1)))
                || (firstCardIndex == secondCardIndex)) {
            buttons[firstCardIndex].setText("?");
            buttons[secondCardIndex].setText("?");
        } else {
            System.out.println("Match!");
        }
        cardsClicked = 0;
        selectedCards.clear();
    }
}

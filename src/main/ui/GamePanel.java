package ui;

import model.Hand;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class GamePanel extends JPanel implements ActionListener {
    private Hand hand;
    private JButton[] buttons;
    private ArrayList<Integer> selectedCards = new ArrayList<>();
    private ArrayList<Integer> matchedCards;

    public GamePanel(int cards, Hand hand, ArrayList<Integer> matchedCards) {
        int row = cards / 2;
        int column = 4;
        this.setLayout(new GridLayout(row, column));
        buttons = new JButton[cards * 2];

        hand.createHand(cards);
        this.hand = hand;
        this.matchedCards = matchedCards;

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
                if (selectedCards.size() == 2) {
                    if (!matchedCards.contains(selectedCards.get(0)) && !matchedCards.contains(selectedCards.get(1))) {
                        System.out.println("2 cards selected");
                        compareCards();
                        if (matchedCards.size() == hand.getHandSize()) {
                            JOptionPane.showMessageDialog(this, "You win!");
                            break;
                        }
                        continue;

                    }
                }
                if (!matchedCards.contains(i)) {
                    System.out.println(hand.getCardAt(i));
                    buttons[i].setText(Character.toString(hand.getCardAt(i)));
                    selectedCards.add(i);
                    System.out.println(selectedCards.size());
                }
            }
        }
    }

    public void compareCards() {
        int firstCardIndex = selectedCards.get(0);
        int secondCardIndex = selectedCards.get(1);

        if (!(hand.getCardAt(firstCardIndex) == hand.getCardAt(secondCardIndex))
                || (firstCardIndex == secondCardIndex)) {
            buttons[firstCardIndex].setText("?");
            buttons[secondCardIndex].setText("?");
        } else {
            System.out.println("Match!");
            matchedCards.add(firstCardIndex);
            matchedCards.add(secondCardIndex);
        }
        selectedCards.clear();
    }
}

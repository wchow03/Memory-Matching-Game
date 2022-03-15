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
    private ArrayList<Integer> clearButtonLetter = new ArrayList<>();
    private boolean matchFound = true;

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

    public GamePanel(Hand hand, ArrayList<Integer> matchedCards) {
        int row = hand.getHandSize() / 4;
        int column = 4;
        this.setLayout(new GridLayout(row, column));
        buttons = new JButton[hand.getHandSize()];

        this.hand = hand;
        this.matchedCards = matchedCards;

        createCardButtonsFromSave();
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

    private void createCardButtonsFromSave() {
        int matchedCardIndex = 0;
        for (int i = 0; i < hand.getHandSize(); i++) {
            if (i == matchedCards.get(matchedCardIndex)) {
                buttons[i] = new JButton(String.valueOf(hand.getCardAt(i)));
                matchedCardIndex++;
            } else {
                buttons[i] = new JButton("?");
            }
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
                if (clearButtonLetter.size() == 2 && !matchFound) {
                    buttons[clearButtonLetter.get(0)].setText("?");
                    buttons[clearButtonLetter.get(1)].setText("?");
                    clearButtonLetter.clear();
                }
                if (!matchedCards.contains(i)) {
                    buttons[i].setText(Character.toString(hand.getCardAt(i)));
                    selectedCards.add(i);

                    if (selectedCards.size() == 2 && !matchedCards.contains(selectedCards.get(0))
                            && !matchedCards.contains(selectedCards.get(1))) {

                        compareCards(selectedCards.get(0), selectedCards.get(1));

                        checkWin();
                    }
                }
            }
        }
    }

    private void compareCards(int firstCardIndex, int secondCardIndex) {
        if ((firstCardIndex != secondCardIndex)
                && (hand.getCardAt(firstCardIndex) == hand.getCardAt(secondCardIndex))) {
            matchedCards.add(firstCardIndex);
            matchedCards.add(secondCardIndex);
            matchFound = true;
        } else {
            clearButtonLetter.add(firstCardIndex);
            clearButtonLetter.add(secondCardIndex);
            matchFound = false;
        }
        selectedCards.clear();
    }

    private void checkWin() {
        if (matchedCards.size() == hand.getHandSize()) {
            JOptionPane.showMessageDialog(this, "You win!");
        }
    }

}

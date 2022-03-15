package ui;

import model.Hand;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

// Represents game portion of application
public class GamePanel extends JPanel implements ActionListener {
    private Hand hand;
    private JButton[] buttons;
    private ArrayList<Integer> selectedCards = new ArrayList<>();
    private ArrayList<Integer> matchedCards;
    private ArrayList<Integer> clearButtonLetter = new ArrayList<>();
    private boolean matchFound = true;

    // EFFECTS: creates a panel with cards * 2 buttons and no matched cards
    public GamePanel(int cards, Hand hand) {
        this.hand = hand;
        this.matchedCards = hand.getMatchedCards();

        int row = cards / 2;
        int column = 4;
        this.setLayout(new GridLayout(row, column));
        buttons = new JButton[cards * 2];

        hand.createHand(cards);

        createCardButtons(hand.getHandSize());
    }

    // EFFECTS: creates a panel with hand.getHandSize() buttons and cards that have been matched
    public GamePanel(Hand hand) {
        this.hand = hand;
        this.matchedCards = hand.getMatchedCards();

        int row = hand.getHandSize() / 4;
        int column = 4;
        this.setLayout(new GridLayout(row, column));
        buttons = new JButton[hand.getHandSize()];

        createCardButtonsFromSave();
    }

    // MODIFIES: this
    // EFFECTS: creates handSize amount of buttons with questions marks
    private void createCardButtons(int handSize) {
        for (int i = 0; i < handSize; i++) {
            buttons[i] = new JButton("?");
            setUpButton(i);
        }
    }

    // MODIFIES: this
    // EFFECTS: creates amount of buttons from save. With cards have been matched face up (showing letter)
    private void createCardButtonsFromSave() {
        int matchedCardIndex = 0;
        for (int i = 0; i < hand.getHandSize(); i++) {
            if (matchedCardIndex < matchedCards.size() && matchedCards.get(matchedCardIndex) == i) {
                buttons[i] = new JButton(String.valueOf(hand.getCardAt(i)));
                matchedCardIndex++;
            } else {
                buttons[i] = new JButton("?");
            }
            setUpButton(i);
        }
    }

    // MODIFIES: this
    // EFFECTS: sets button to have font, background colour, non focusable and adds action listener
    private void setUpButton(int i) {
        buttons[i].setFont(new Font("Ariel", Font.BOLD, 70));
        buttons[i].setBackground(Color.white);
        buttons[i].setFocusable(false);
        buttons[i].addActionListener(this);
        this.add(buttons[i]);
    }

    // MODIFIES: this
    // EFFECTS: checks if two buttons were pressed
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
                    buttons[i].setBackground(Color.green);
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

    // MODIFIES: this
    // EFFECTS: compares the two letters at pressed buttons
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

    // EFFECTS: shows a pop up window if size of cards matched is same as hand size
    private void checkWin() {
        if (matchedCards.size() == hand.getHandSize()) {
            JOptionPane.showMessageDialog(this, "You win!");
        }
    }
}

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
    private ArrayList<ImageIcon> logos = new ArrayList<>();
    private ArrayList<Integer> selectedCards = new ArrayList<>();
    private ArrayList<Integer> matchedCards;
    private ArrayList<Integer> clearButtonLetter = new ArrayList<>();
    private boolean matchFound = true;

    // EFFECTS: creates a panel with cards * 2 buttons and no matched cards
    public GamePanel(int cards, Hand hand) {
        initializeHandAndLogos(hand);

        int row = cards / 2;
        int column = 4;
        this.setLayout(new GridLayout(row, column));
        buttons = new JButton[cards * 2];

        hand.createHand(cards);

        createCardButtons(hand.getHandSize());
    }

    // EFFECTS: creates a panel with hand.getHandSize() buttons and cards that have been matched
    public GamePanel(Hand hand) {
        initializeHandAndLogos(hand);

        int row = hand.getHandSize() / 4;
        int column = 4;
        this.setLayout(new GridLayout(row, column));
        buttons = new JButton[hand.getHandSize()];

        createCardButtonsFromSave();
    }

    // MODIFIES: this
    // EFFECTS: initializes hand, matched cards and logos
    private void initializeHandAndLogos(Hand hand) {
        this.hand = hand;
        this.matchedCards = hand.getMatchedCards();
        initializeLogos();
    }

    // MODIFIES: this
    // EFFECTS: initializes logo images
    private void initializeLogos() {
        ImageIcon csharp = new ImageIcon("./data/C#Logo.png");
        ImageIcon cplusplus = new ImageIcon("./data/C++Logo.png");
        ImageIcon c = new ImageIcon("./data/CLogo.png");
        ImageIcon css = new ImageIcon("./data/CSSLogo.png");
        ImageIcon java = new ImageIcon("./data/JavaLogo.png");
        ImageIcon js = new ImageIcon("./data/JSLogo.png");
        ImageIcon python = new ImageIcon("./data/PythonLogo.png");
        ImageIcon racket = new ImageIcon("./data/RacketLogo.png");
        ImageIcon html = new ImageIcon("./data/HTMLLogo.png");
        ImageIcon swift = new ImageIcon("./data/SwiftLogo.png");
        logos.add(csharp);
        logos.add(cplusplus);
        logos.add(c);
        logos.add(css);
        logos.add(java);
        logos.add(js);
        logos.add(python);
        logos.add(racket);
        logos.add(html);
        logos.add(swift);
    }

    // MODIFIES: this
    // EFFECTS: creates handSize amount of buttons with questions marks
    private void createCardButtons(int handSize) {
        for (int i = 0; i < handSize; i++) {
            buttons[i] = new JButton("?");
            buttons[i].setIcon(null);
            setUpButton(i);
        }
    }

    // MODIFIES: this
    // EFFECTS: creates amount of buttons from save. With cards have been matched face up (showing letter)
    private void createCardButtonsFromSave() {
        int matchedCardIndex = 0;
        for (int i = 0; i < hand.getHandSize(); i++) {
            if (matchedCardIndex < matchedCards.size() && matchedCards.get(matchedCardIndex) == i) {
                buttons[i] = new JButton("");
                placeImageOnButton(i);
                placeImageOnButtonExtended(i);
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
                    clearButtons();
                }
                if (!matchedCards.contains(i)) {
                    buttons[i].setText(null);
                    placeImageOnButton(i);
                    placeImageOnButtonExtended(i);
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
    // EFFECTS: places image at corresponding char value
    private void placeImageOnButton(int i) {
        if (hand.getCardAt(i) == 'A') {
            buttons[i].setIcon(resizeIcon(logos.get(0)));
        }
        if (hand.getCardAt(i) == 'B') {
            buttons[i].setIcon(resizeIcon(logos.get(1)));
        }
        if (hand.getCardAt(i) == 'C') {
            buttons[i].setIcon(resizeIcon(logos.get(2)));
        }
        if (hand.getCardAt(i) == 'D') {
            buttons[i].setIcon(resizeIcon(logos.get(3)));
        }
        if (hand.getCardAt(i) == 'E') {
            buttons[i].setIcon(resizeIcon(logos.get(4)));
        }
    }

    // MODIFIES: this
    // EFFECTS: places image at corresponding char value
    private void placeImageOnButtonExtended(int i) {
        if (hand.getCardAt(i) == 'F') {
            buttons[i].setIcon(resizeIcon(logos.get(5)));
        }
        if (hand.getCardAt(i) == 'G') {
            buttons[i].setIcon(resizeIcon(logos.get(6)));
        }
        if (hand.getCardAt(i) == 'H') {
            buttons[i].setIcon(resizeIcon(logos.get(7)));
        }
        if (hand.getCardAt(i) == 'I') {
            buttons[i].setIcon(resizeIcon(logos.get(8)));
        }
        if (hand.getCardAt(i) == 'J') {
            buttons[i].setIcon(resizeIcon(logos.get(9)));
        }
    }

    // EFFECTS: resizes icon to fit JButton
    private ImageIcon resizeIcon(ImageIcon icon) {
        Image img = icon.getImage();
        Image resized = img.getScaledInstance(105, 105, java.awt.Image.SCALE_SMOOTH);
        return new ImageIcon(resized);
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

    // MODIFIES: this
    // EFFECTS: sets the buttons text back to "?"
    private void clearButtons() {
        buttons[clearButtonLetter.get(0)].setIcon(null);
        buttons[clearButtonLetter.get(1)].setIcon(null);
        buttons[clearButtonLetter.get(0)].setText("?");
        buttons[clearButtonLetter.get(1)].setText("?");
        clearButtonLetter.clear();
    }

    // EFFECTS: shows a pop up window if size of cards matched is same as hand size
    private void checkWin() {
        if (matchedCards.size() == hand.getHandSize()) {
            JOptionPane.showMessageDialog(this, "You win!");
        }
    }
}

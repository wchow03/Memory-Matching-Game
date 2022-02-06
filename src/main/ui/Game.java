package ui;

import model.Card;
import model.Hand;

import java.util.Collections;
import java.util.Scanner;

// Memory matching game
public class Game {
    private Scanner scanner;
    private Hand hand;
    private boolean continuePlaying;

    // EFFECTS: starts the application
    public Game() {
        runGame();
        System.out.println("Thanks for playing!");
    }

    // MODIFIES: this
    // EFFECTS: takes in and processes user input
    public void runGame() {
        scanner = new Scanner(System.in);
        continuePlaying = true;

        while (continuePlaying) {
            chooseDifficulty();
            beginPlaying();

            if (hand.getHandSize() == 0) {
                System.out.println("Congratulations, you won!");
            }

            String playAgain;
            System.out.print("Would you like to play again? (yes / no): ");
            while (true) {
                playAgain = (scanner.next()).toLowerCase();
                if (playAgain.equals("no")) {
                    continuePlaying = false;
                    break;
                } else if (playAgain.equals("yes")) {
                    continuePlaying = true;
                    break;
                } else {
                    System.out.print("Please choose one of (yes or no): ");
                }
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    public void chooseDifficulty() {
        System.out.print("Choose a difficulty [Easy(12 cards), Medium(16 cards), Hard(20 cards)]: ");
        String difficulty;
        while (true) {
            difficulty = (scanner.next()).toLowerCase();
            if (difficulty.equals("easy")) {
                createHand(6);
                break;
            } else if (difficulty.equals("medium")) {
                createHand(8);
                break;
            } else if (difficulty.equals("hard")) {
                createHand(10);
                break;
            } else {
                System.out.print("That mode does not exist. Please choose one of: Easy, Medium, Hard: ");
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: creates a list of cards with n cards
    // and n/2 pairs that have the same letter
    public void createHand(int n) {
        hand = new Hand();
        int l = 65;
        for (int i = 0; i < n; i++) {
            Card card = new Card((char)l);
            hand.addCard(card);
            hand.addCard(card);
            l++;
        }
        Collections.shuffle(hand.getHand());
    }

    // EFFECTS: processes user input
    public void beginPlaying() {
        while (continuePlaying && (hand.getHandSize() > 0)) {
            printHand();
            chooseIndexes();
            continuePlaying = keepPlaying();
        }
    }

    // EFFECTS: prints the cards left as indexes (uses 0 indexing)
    public void printHand() {
        String board = "[";
        for (int i = 0; i < hand.getHandSize(); i++) {
            board +=  i + "] [";
        }
        System.out.println(board.substring(0, board.length() - 2));
    }

    // MODIFIES: this
    // EFFECTS: has the user pick two indexes
    public void chooseIndexes() {
        int i1;
        int i2;
        while (true) {
            i1 = chooseIndex("first");
            i2 = chooseIndex("second");
            if (!(i1 == i2)) {
                break;
            }
            System.out.println("Please choose two different indexes.");
        }
        System.out.println("Letter at index " + i1 + ": " + hand.getCardAt(i1));
        System.out.println("Letter at index " + i2 + ": " + hand.getCardAt(i2));
        hand.compareCards(i1, i2);
    }

    // REQUIRES: a non empty string
    // EFFECTS: gets user to choose an integer in the range of hand size
    public int chooseIndex(String str) {
        System.out.print("Choose the " + str + " index: ");
        while (true) {
            try {
                return checkBoundary(Integer.parseInt(scanner.next()));
            } catch (NumberFormatException exception) {
                System.out.print("Please enter an integer: ");
            }
        }
    }

    // EFFECTS: returns an integer in the range of the hand size
    public int checkBoundary(int i) {
        while (i >= hand.getHandSize() || i < 0) {
            System.out.print("Please choose an index in the given range: ");
            i = scanner.nextInt();
        }
        return i;
    }

    // EFFECTS: returns true if enter key is pressed, and false if "quit" is entered
    public boolean keepPlaying() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("Press enter to continue or type quit to exit game: ");
            String option = (scanner.nextLine()).toLowerCase();
            if (option.equals("quit")) {
                return false;
            } else if (option.equals("")) {
                return true;
            } else {
                System.out.println("That is not a valid option, please try again.");
            }
        }
    }
}

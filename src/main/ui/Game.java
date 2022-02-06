package ui;

import model.Card;
import model.Hand;

import java.util.Collections;
import java.util.Scanner;

// Memory matching game application
public class Game {
    private Scanner scanner;
    private Hand hand;
    private boolean continuePlaying;

    // EFFECTS: starts the application
    public Game() {
        runGame();
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

            String playAgain = "";
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
        System.out.println("Thanks for playing!");
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    public void chooseDifficulty() {
        int n = 0;
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
        for (int i=0; i<n; i++) {
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
        String cards = "[";
        String board = "[";
        for (int i=0; i<hand.getHand().size(); i++) {
            board +=  i+"] [";
        }

        System.out.println(cards.substring(0, cards.length()-2));
        System.out.println(board.substring(0, board.length()-2));
    }

    // MODIFIES: this
    // EFFECTS: has the user pick two indexes
    public void chooseIndexes() {
        int i1;
        int i2;
        while (true) {
            i1 = chooseIndex("first");
            i2 = chooseIndex("second");
            if (!(i1==i2)) {
                break;
            }
            System.out.println("Please choose two different indexes.");
        }
        System.out.println("Letter at index 1: " + hand.getCardAt(i1));
        System.out.println("Letter at index 2: " + hand.getCardAt(i2));
        compareCards(i1, i2);
    }

    // REQUIRES: a non empty string
    // EFFECTS: gets user to choose an integer in the range of hand size
    public int chooseIndex(String str) {
        System.out.print("Choose the "+str+" index: ");
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

    // REQUIRES: two different integers
    // MODIFIES: this
    // EFFECTS: removes the cards in hand if cards at index i1 and i2 match,
    // otherwise does nothing
    public void compareCards(int i1, int i2) {
        // fix you can't choose two same index's
        char letter1 = hand.getCardAt(i1);
        char letter2 = hand.getCardAt(i2);
        if (letter1 == letter2) {
            if (i1>i2) {
                hand.removeCard(i1);
                hand.removeCard(i2);
            } else {
                hand.removeCard(i2);
                hand.removeCard(i1);
            }
            System.out.println("It's a match!");
        } else {
            System.out.println("Not a match.");
        }
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

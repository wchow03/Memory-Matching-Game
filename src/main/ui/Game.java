package ui;

import model.Hand;

import java.util.InputMismatchException;
import java.util.Scanner;

// Memory matching game
public class Game {
    private Scanner scanner;
    private Hand hand;
    private boolean continuePlaying;

    // EFFECTS: starts the application
    public Game() {
        runGame();
    }

    // MODIFIES: this
    // EFFECTS: begins application
    public void runGame() {
        hand = new Hand();
        scanner = new Scanner(System.in);
        continuePlaying = true;

        while (continuePlaying) {
            chooseDifficulty();
            beginPlaying();

            if (hand.getHandSize() == 0) {
                System.out.println("Congratulations, you won!");
            }
            playAgain();
        }
        System.out.println("Thanks for playing!");
    }

    // EFFECTS: processes user input to ask if they want to play again
    public void playAgain() {
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

    // MODIFIES: this
    // EFFECTS: processes user input
    public void chooseDifficulty() {
        hand.getHand().clear();
        System.out.print("Choose a difficulty [Easy(12 cards), Medium(16 cards), Hard(20 cards)]: ");
        String difficulty;
        while (true) {
            difficulty = (scanner.next()).toLowerCase();
            if (difficulty.equals("easy")) {
                hand.createHand(6);
                break;
            } else if (difficulty.equals("medium")) {
                hand.createHand(8);
                break;
            } else if (difficulty.equals("hard")) {
                hand.createHand(10);
                break;
            } else {
                System.out.print("That mode does not exist. Please choose one of: Easy, Medium, Hard: ");
            }
        }
    }

    // EFFECTS: processes user input
    public void beginPlaying() {
        while (continuePlaying && (hand.getHandSize() > 0)) {
            printHand();
            chooseIndexes();
            continuePlaying = keepPlaying();
        }
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
        compareCards(i1, i2);
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

    // REQUIRES: two different integers
    // MODIFIES: this
    // EFFECTS: if cards match, returns true and removes the cards from hand,
    // otherwise returns false
    public void compareCards(int i1, int i2) {
        // fix you can't choose two same index's
        char letter1 = hand.getCardAt(i1);
        char letter2 = hand.getCardAt(i2);
        if (letter1 == letter2) {
            if (i1 > i2) {
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

    // REQUIRES: any integer
    // EFFECTS: returns an integer in the range of the hand size
    public int checkBoundary(int i) {
        while (true) {
            try {
                while (i >= hand.getHandSize() || i < 0) {
                    System.out.print("Please choose an index in the given range: ");
                    i = Integer.parseInt(scanner.next());
                }
                break;
            } catch (NumberFormatException exception) {
                System.out.println("Please choose a number and is in the given range.");
            }
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

    // EFFECTS: prints the cards left as indexes (uses 0 indexing)
    public void printHand() {
        String board = "[";
        for (int i = 0; i < hand.getHandSize(); i++) {
            board +=  i + "] [";
        }
        System.out.println(board.substring(0, board.length() - 2));
    }
}

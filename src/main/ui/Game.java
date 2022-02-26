package ui;

import model.Hand;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

// Memory matching game
public class Game {
    private static final String SAVE_FILE = "./data/saveHand.json";
    private Scanner scanner;
    private Hand hand;
    private boolean continuePlaying;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    // EFFECTS: starts the application
    public Game() {
        jsonWriter = new JsonWriter(SAVE_FILE);
        jsonReader = new JsonReader(SAVE_FILE);
        runGame();
    }

    // MODIFIES: this
    // EFFECTS: begins application
    private void runGame() {
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
    private void playAgain() {
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
    private void chooseDifficulty() {
        hand.getHand().clear();
        System.out.print("Choose a difficulty or load from save ");
        System.out.print("[Easy(12 cards), Medium(16 cards), Hard(20 cards), Load from save]: ");
        String option;
        while (true) {
            option = (scanner.next()).toLowerCase();
            if (option.equals("load")) {
                loadGame();
                break;
            } else if (option.equals("easy")) {
                hand.createHand(6);
                break;
            } else if (option.equals("medium")) {
                hand.createHand(8);
                break;
            } else if (option.equals("hard")) {
                hand.createHand(10);
                break;
            } else {
                System.out.print("That mode does not exist. Please choose one of: Easy, Medium, Hard, Load: ");
            }
        }
    }

    // EFFECTS: processes user input
    private void beginPlaying() {
        while (continuePlaying && (hand.getHandSize() > 0)) {
            printHand();
            chooseIndexes();
            continuePlaying = keepPlaying();
        }
    }

    // MODIFIES: this
    // EFFECTS: has the user pick two indexes
    private void chooseIndexes() {
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
    private int chooseIndex(String str) {
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
    private void compareCards(int i1, int i2) {
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
    private int checkBoundary(int i) {
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
    private boolean keepPlaying() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("Press enter to continue, save to save progress, or quit to exit game: ");
            String option = (scanner.nextLine()).toLowerCase();
            if (option.equals("save")) {
                saveGame();
            } else if (option.equals("quit")) {
                return false;
            } else if (option.equals("")) {
                return true;
            } else {
                System.out.println("That is not a valid option, please try again.");
            }
        }
    }

    // EFFECTS: prints the cards left as indexes (uses 0 indexing)
    private void printHand() {
        StringBuilder board = new StringBuilder("[");
        for (int i = 0; i < hand.getHandSize(); i++) {
            board.append(i).append("] [");
        }
        System.out.println(board.substring(0, board.length() - 2));
    }

    // Method modelled after saveWorkRoom in
    // https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
    // EFFECTS: saves game to file
    private void saveGame() {
        try {
            jsonWriter.open();
            jsonWriter.write(hand);
            jsonWriter.close();
            System.out.println("Game saved to " + SAVE_FILE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to " + SAVE_FILE);
        }
    }

    // Method modelled after loadWorkRoom
    // https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
    // MODIFIES: this
    // EFFECTS: loads saved hand from file
    private void loadGame() {
        try {
            hand = jsonReader.read();
            System.out.println("Game loaded from " + SAVE_FILE);
        } catch (IOException e) {
            System.out.println("Unable to read from " + SAVE_FILE);
        }
    }
}

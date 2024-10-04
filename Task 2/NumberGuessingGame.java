import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class NumberGuessingGame {
    static ArrayList<Integer> scoreBoard = new ArrayList<>(); // Holds the number of tries for each game

    public static void main(String[] args) {
        NumberGuessingGame game = new NumberGuessingGame();
        game.menu();
    }

    public void menu() {
        Scanner input = new Scanner(System.in);
        while (true) {
            System.out.println("--------------------");
            System.out.println("Welcome to the number game");
            System.out.println("1) Play the Game");
            System.out.println("2) Score Board");
            System.out.println("3) Exit the game");
            System.out.println("--------------------");
            try {
                System.out.print("What action would you like to do from the above actions? ");
                int menuOption = input.nextInt();
                switch (menuOption) {
                    case 1:
                        playGame(input);
                        break;
                    case 2:
                        displayScoreBoard();
                        break;
                    case 3:
                        System.out.println("\nThanks for playing the game!");
                        System.exit(0);
                        break;
                    default:
                        System.err.println("Invalid option. Please try again.");
                }
            } catch (InputMismatchException e) {
                System.err.println("Invalid input. Please enter a number.");
                input.next(); // Clear invalid input
            }
        }
    }

    public void playGame(Scanner input) {
        System.out.print("\nWhat would you like the range of the numbers to be? ");
        int numberRange = input.nextInt();
        int randomNumber = randomNumber(numberRange);
        guessNumber(randomNumber, input);
    }

    public int randomNumber(int numberRange) {
        Random random = new Random();
        return random.nextInt(numberRange) + 1; // Generate number between 1 and numberRange
    }

    public void guessNumber(int randomNumber, Scanner input) {
        int userGuess;
        int guessCount = 0;
        
        do {
            System.out.print("Enter your guess number: ");
            userGuess = input.nextInt();
            guessCount++;

            if (userGuess > randomNumber) {
                System.out.println("Lower");
            } else if (userGuess < randomNumber) {
                System.out.println("Higher");
            }
        } while (userGuess != randomNumber);

        // Output result
        if (guessCount == 1) {
            System.out.println("You guessed the number right in " + guessCount + " try!");
        } else {
            System.out.println("You guessed the number right in " + guessCount + " tries!");
        }
        scoreBoard.add(guessCount);
    }

    public void displayScoreBoard() {
        System.out.println("--------------------");
        System.out.println("Score Board");
        System.out.println("--------------------");
        if (scoreBoard.isEmpty()) {
            System.out.println("No games played yet.");
            return;
        }
        
        Collections.sort(scoreBoard);
        System.out.println("Your fastest games today out of all tries are: ");
        for (Integer score : scoreBoard) {
            System.out.println("Finished the number game in " + score + " tries");
        }
    }
}

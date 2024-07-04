package guessthenumber;

import java.util.Random;
import java.util.Scanner;

public class GuessTheNumber {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        int numberToGuess = 61; // Set the number to guess
        int maxAttempts = 10; // Reduce the number of attempts
        int score = 0;

        System.out.println("Welcome to the Guess the Number game!");

        while (true) {
            int attempts = 0;
            boolean hasGuessedCorrectly = false;

            System.out.println("\nA new round has started!");
            System.out.println("Guess a number between 1 and 100.");

            while (attempts < maxAttempts) {
                System.out.print("Enter your guess: ");
                int userGuess = scanner.nextInt();
                attempts++;

                if (userGuess == numberToGuess) {
                    System.out.println("Congratulations! You guessed the correct number. \uD83C\uDF89"); // Emoji for celebration
                    score += (maxAttempts - attempts + 1); // More points for fewer attempts
                    hasGuessedCorrectly = true;
                    break;
                } else if (userGuess < numberToGuess) {
                    System.out.println("Too low! The number is higher than your guess.");
                } else {
                    System.out.println("Too high! The number is lower than your guess.");
                }
            }

            if (!hasGuessedCorrectly) {
                System.out.println("You've used all your attempts. The correct number was " + numberToGuess + ".");
            }

            System.out.println("Your score: " + score);
            System.out.print("Do you want to play another round? (yes/no): ");
            String playAgain = scanner.next();

            if (!playAgain.equalsIgnoreCase("yes")) {
                break;
            }
        }

        System.out.println("Thank you for playing! Your final score is: " + score);
        scanner.close();
    }
}

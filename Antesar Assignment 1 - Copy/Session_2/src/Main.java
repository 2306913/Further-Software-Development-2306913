//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        // Create Scanner object
        Scanner sc = new Scanner(System.in);

        // Create "StringActions" object
        StringActions stringActions = new StringActions();

        System.out.println("Hello and welcome!");
        // Main loop

        while (true) {

            System.out.println("Please enter a string to parse: ");

            // Gets input from user
            String input = sc.nextLine();

            // Prints String
            System.out.println("Input string: " + input);
            // Converts to Uppercase
            input = input.toUpperCase();
            // Prints String
            System.out.println("String converted to Uppercase: " + input);
            // Count words in sentence
            System.out.println("Number of words in String: " + stringActions.wordCount(input));
            // Reverses String
            input = stringActions.reverseSentence(input);
            // Prints reversed version of string
            System.out.println("String reversed: " + input);
            // Determines whether String contains the word "Java" (Unlikely given sentence reversal)
            String wordToFind = "Java";
            System.out.println("String contains the word \"Java\"? " + stringActions.containsSubString("Java", input));
            // Replaces spaces in String with underscores
            char spaceReplacement = '_';
            input = stringActions.replaceSpaces(spaceReplacement, input);
            System.out.println("String with spaces replaced with underscores: " + input);

            // Prints final modified string
            System.out.println("Final string: " + input);

            // Asks user if they would like to repeat the program
            String answer;
            while (true) {
                System.out.println("\nWould you like to try another string Y/N? ");
                answer = sc.nextLine();
                if (answer.equalsIgnoreCase("Y") || answer.equalsIgnoreCase("N")) {
                    break;
                }
                else{
                    System.out.println("Invalid answer. Try again.");
                }
            }

            if (answer.equalsIgnoreCase("N")) {
                System.out.println("Goodbye.");
                break;
            }
        }
    }
}
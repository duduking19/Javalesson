package question6;

import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

/**
 * Counts how many times each letter appears in the input text.
 */
public class Question6Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter text:");
        // Convert to lowercase so uppercase and lowercase letters are counted together.
        String text = scanner.nextLine().toLowerCase();

        // TreeMap keeps the result ordered alphabetically by character.
        Map<Character, Integer> letterCounts = new TreeMap<>();
        for (char ch : text.toCharArray()) {
            // Ignore spaces, digits, and punctuation; only letters are counted.
            if (Character.isLetter(ch)) {
                letterCounts.merge(ch, 1, Integer::sum);
            }
        }

        System.out.println();
        System.out.println("Letter counts:");
        // Print the final frequency table in sorted order.
        for (Map.Entry<Character, Integer> entry : letterCounts.entrySet()) {
            System.out.printf("%s -> %d%n", entry.getKey(), entry.getValue());
        }
    }
}

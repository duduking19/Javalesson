package question5;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Counts repeated words in a sentence while ignoring case and punctuation.
 */
public class Question5Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a sentence:");
        String sentence = scanner.nextLine();

        // Convert to lowercase and replace punctuation with spaces so words can be split reliably.
        String normalized = sentence.toLowerCase().replaceAll("[^\\p{L}\\p{Nd}]+", " ").trim();
        // LinkedHashMap preserves the order in which different words first appear.
        Map<String, Integer> frequencies = new LinkedHashMap<>();

        if (!normalized.isEmpty()) {
            // Count how many times each word appears in the cleaned sentence.
            for (String word : normalized.split("\\s+")) {
                frequencies.merge(word, 1, Integer::sum);
            }
        }

        // repeatedWordTypes counts how many distinct words are duplicated.
        int repeatedWordTypes = 0;
        // repeatedOccurrences counts only the extra appearances beyond the first one.
        int repeatedOccurrences = 0;

        System.out.println();
        System.out.println("Repeated words:");
        for (Map.Entry<String, Integer> entry : frequencies.entrySet()) {
            // Only report words whose frequency is greater than one.
            if (entry.getValue() > 1) {
                repeatedWordTypes++;
                repeatedOccurrences += entry.getValue() - 1;
                System.out.printf("%s -> %d%n", entry.getKey(), entry.getValue());
            }
        }

        System.out.println();
        // Output both summary values required for understanding the duplication level.
        System.out.println("Distinct repeated words : " + repeatedWordTypes);
        System.out.println("Extra repeated count    : " + repeatedOccurrences);
    }
}

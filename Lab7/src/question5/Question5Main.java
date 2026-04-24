package question5;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class Question5Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a sentence:");
        String sentence = scanner.nextLine();

        String normalized = sentence.toLowerCase().replaceAll("[^\\p{L}\\p{Nd}]+", " ").trim();
        Map<String, Integer> frequencies = new LinkedHashMap<>();

        if (!normalized.isEmpty()) {
            for (String word : normalized.split("\\s+")) {
                frequencies.merge(word, 1, Integer::sum);
            }
        }

        int repeatedWordTypes = 0;
        int repeatedOccurrences = 0;

        System.out.println();
        System.out.println("Repeated words:");
        for (Map.Entry<String, Integer> entry : frequencies.entrySet()) {
            if (entry.getValue() > 1) {
                repeatedWordTypes++;
                repeatedOccurrences += entry.getValue() - 1;
                System.out.printf("%s -> %d%n", entry.getKey(), entry.getValue());
            }
        }

        System.out.println();
        System.out.println("Distinct repeated words : " + repeatedWordTypes);
        System.out.println("Extra repeated count    : " + repeatedOccurrences);
    }
}

package question6;

import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class Question6Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter text:");
        String text = scanner.nextLine().toLowerCase();

        Map<Character, Integer> letterCounts = new TreeMap<>();
        for (char ch : text.toCharArray()) {
            if (Character.isLetter(ch)) {
                letterCounts.merge(ch, 1, Integer::sum);
            }
        }

        System.out.println();
        System.out.println("Letter counts:");
        for (Map.Entry<Character, Integer> entry : letterCounts.entrySet()) {
            System.out.printf("%s -> %d%n", entry.getKey(), entry.getValue());
        }
    }
}

package question4;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.Scanner;

/**
 * Reads names from the console and removes duplicates with a Set.
 */
public class Question4Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // LinkedHashSet removes duplicates and also preserves the first input order.
        Set<String> uniqueNames = new LinkedHashSet<>();
        // Count every non-empty input name, including duplicates.
        int totalInputs = 0;

        System.out.println("Enter names one per line. Type END to finish.");
        while (scanner.hasNextLine()) {
            String name = scanner.nextLine().trim();
            // END is the sentinel value that stops input.
            if ("END".equalsIgnoreCase(name)) {
                break;
            }
            // Blank lines are ignored so they do not affect the totals.
            if (name.isEmpty()) {
                continue;
            }
            totalInputs++;
            // Adding the same name again has no effect because a Set keeps unique elements only.
            uniqueNames.add(name);
        }

        System.out.println();
        // Print both the total inputs and the unique result so the deduplication effect is visible.
        System.out.println("Total names entered : " + totalInputs);
        System.out.println("Unique names count  : " + uniqueNames.size());
        System.out.println("Unique names        : " + uniqueNames);
    }
}

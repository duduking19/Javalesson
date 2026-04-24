package question4;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.Scanner;

public class Question4Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Set<String> uniqueNames = new LinkedHashSet<>();
        int totalInputs = 0;

        System.out.println("Enter names one per line. Type END to finish.");
        while (scanner.hasNextLine()) {
            String name = scanner.nextLine().trim();
            if ("END".equalsIgnoreCase(name)) {
                break;
            }
            if (name.isEmpty()) {
                continue;
            }
            totalInputs++;
            uniqueNames.add(name);
        }

        System.out.println();
        System.out.println("Total names entered : " + totalInputs);
        System.out.println("Unique names count  : " + uniqueNames.size());
        System.out.println("Unique names        : " + uniqueNames);
    }
}

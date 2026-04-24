package question1;

import java.util.Objects;

/**
 * Demonstrates a generic equality method and explains how equals behaves for
 * different kinds of objects.
 */
public class Question1Main {

    /**
     * Uses the equals contract of the runtime type to compare two values.
     * For wrapper classes and String this means content comparison, while
     * plain Object keeps reference comparison.
     */
    public static <T> boolean isEqualTo(T left, T right) {
        return Objects.equals(left, right);
    }

    /**
     * Prints one labeled comparison result so the main method stays focused on
     * test case preparation instead of formatting details.
     */
    private static <T> void printComparison(String label, T left, T right) {
        System.out.printf("%s -> %s%n", label, isEqualTo(left, right));
    }

    public static void main(String[] args) {
        // Two Integer objects with the same numeric value.
        Integer integerA = 128;
        Integer integerB = 128;

        // Two distinct String objects that contain the same characters.
        String stringA = new String("Java");
        String stringB = new String("Java");

        // objectC points to the same object as objectA, while objectB does not.
        Object objectA = new Object();
        Object objectB = new Object();
        Object objectC = objectA;

        System.out.println("Generic method isEqualTo demo");
        // Integer overrides equals, so the numeric values are compared.
        printComparison("Integer 128 vs 128", integerA, integerB);
        // String also overrides equals, so the text content is compared.
        printComparison("String \"Java\" vs \"Java\"", stringA, stringB);
        // Same reference means Object.equals returns true.
        printComparison("Object same reference", objectA, objectC);
        // Different references mean Object.equals returns false.
        printComparison("Object different reference", objectA, objectB);

        System.out.println();
        System.out.println("Conclusion:");
        // Summarize the observation required by the lab question.
        System.out.println("1. isEqualTo delegates comparison to equals.");
        System.out.println("2. Integer and String override equals, so their contents are compared.");
        System.out.println("3. Object keeps reference-based equals, so only the same object returns true.");
    }
}

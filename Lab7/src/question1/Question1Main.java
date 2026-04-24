package question1;

import java.util.Objects;

public class Question1Main {

    public static <T> boolean isEqualTo(T left, T right) {
        return Objects.equals(left, right);
    }

    private static <T> void printComparison(String label, T left, T right) {
        System.out.printf("%s -> %s%n", label, isEqualTo(left, right));
    }

    public static void main(String[] args) {
        Integer integerA = 128;
        Integer integerB = 128;

        String stringA = new String("Java");
        String stringB = new String("Java");

        Object objectA = new Object();
        Object objectB = new Object();
        Object objectC = objectA;

        System.out.println("Generic method isEqualTo demo");
        printComparison("Integer 128 vs 128", integerA, integerB);
        printComparison("String \"Java\" vs \"Java\"", stringA, stringB);
        printComparison("Object same reference", objectA, objectC);
        printComparison("Object different reference", objectA, objectB);

        System.out.println();
        System.out.println("Conclusion:");
        System.out.println("1. isEqualTo delegates comparison to equals.");
        System.out.println("2. Integer and String override equals, so their contents are compared.");
        System.out.println("3. Object keeps reference-based equals, so only the same object returns true.");
    }
}

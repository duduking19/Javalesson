package question7;

import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

/**
 * Determines whether an input number is prime and, if not, prints its
 * distinct prime factors with a Set.
 */
public class Question7Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a positive integer:");

        // Reject non-integer input before attempting numeric processing.
        if (!scanner.hasNextLong()) {
            System.out.println("Invalid input. Please enter an integer.");
            return;
        }

        long number = scanner.nextLong();
        // The lab question starts from positive integers greater than 1.
        if (number <= 1) {
            System.out.println("Please enter an integer greater than 1.");
            return;
        }

        // If the number is prime, there is no need to compute factorization.
        if (isPrime(number)) {
            System.out.println(number + " is a prime number.");
            return;
        }

        // TreeSet removes duplicates and keeps the factors in ascending order.
        Set<Long> primeFactors = getDistinctPrimeFactors(number);
        System.out.println(number + " is not a prime number.");
        System.out.println("Distinct prime factors: " + primeFactors);
    }

    /**
     * Tests primality by trial division up to the square root of the number.
     */
    private static boolean isPrime(long number) {
        if (number < 2) {
            return false;
        }
        if (number == 2) {
            return true;
        }
        // Any even number greater than 2 is composite.
        if (number % 2 == 0) {
            return false;
        }
        // After excluding even numbers, only odd divisors need to be tested.
        for (long divisor = 3; divisor * divisor <= number; divisor += 2) {
            if (number % divisor == 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * Returns the set of distinct prime factors of a composite number.
     */
    private static Set<Long> getDistinctPrimeFactors(long number) {
        Set<Long> factors = new TreeSet<>();

        // Extract factor 2 first so the remaining number becomes odd.
        while (number % 2 == 0) {
            factors.add(2L);
            number /= 2;
        }

        // Continue trial division with odd candidates only.
        for (long divisor = 3; divisor * divisor <= number; divisor += 2) {
            while (number % divisor == 0) {
                factors.add(divisor);
                number /= divisor;
            }
        }

        // If something larger than 1 remains, it is itself a prime factor.
        if (number > 1) {
            factors.add(number);
        }
        return factors;
    }
}

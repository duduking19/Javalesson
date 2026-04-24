package question7;

import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class 54Question7Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a positive integer:");

        if (!scanner.hasNextLong()) {
            System.out.println("Invalid input. Please enter an integer.");
            return;
        }

        long number = scanner.nextLong();
        if (number <= 1) {
            System.out.println("Please enter an integer greater than 1.");
            return;
        }

        if (isPrime(number)) {
            System.out.println(number + " is a prime number.");
            return;
        }

        Set<Long> primeFactors = getDistinctPrimeFactors(number);
        System.out.println(number + " is not a prime number.");
        System.out.println("Distinct prime factors: " + primeFactors);
    }

    private static boolean isPrime(long number) {
        if (number < 2) {
            return false;
        }
        if (number == 2) {
            return true;
        }
        if (number % 2 == 0) {
            return false;
        }
        for (long divisor = 3; divisor * divisor <= number; divisor += 2) {
            if (number % divisor == 0) {
                return false;
            }
        }
        return true;
    }

    private static Set<Long> getDistinctPrimeFactors(long number) {
        Set<Long> factors = new TreeSet<>();

        while (number % 2 == 0) {
            factors.add(2L);
            number /= 2;
        }

        for (long divisor = 3; divisor * divisor <= number; divisor += 2) {
            while (number % divisor == 0) {
                factors.add(divisor);
                number /= divisor;
            }
        }

        if (number > 1) {
            factors.add(number);
        }
        return factors;
    }
}

package question7;

import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

/**
 * 判断输入整数是否为质数；若不是，则用 Set 输出不同的质因子。
 */
public class Question7Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a positive integer:");

        // 先校验输入是否为整数，避免后续数值处理出错。
        if (!scanner.hasNextLong()) {
            System.out.println("Invalid input. Please enter an integer.");
            return;
        }

        long number = scanner.nextLong();
        // 题目要求输入大于 1 的正整数。
        if (number <= 1) {
            System.out.println("Please enter an integer greater than 1.");
            return;
        }

        // 如果本身就是质数，就不需要继续做质因数分解。
        if (isPrime(number)) {
            System.out.println(number + " is a prime number.");
            return;
        }

        // TreeSet 会自动去重，并按从小到大的顺序保存质因子。
        Set<Long> primeFactors = getDistinctPrimeFactors(number);
        System.out.println(number + " is not a prime number.");
        System.out.println("Distinct prime factors: " + primeFactors);
    }

    /**
     * 使用试除法判断一个数是否为质数，只需要检查到平方根即可。
     */
    private static boolean isPrime(long number) {
        if (number < 2) {
            return false;
        }
        if (number == 2) {
            return true;
        }
        // 大于 2 的偶数一定不是质数。
        if (number % 2 == 0) {
            return false;
        }
        // 排除偶数后，只需要继续尝试奇数因子。
        for (long divisor = 3; divisor * divisor <= number; divisor += 2) {
            if (number % divisor == 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * 返回一个合数的不同质因子集合。
     */
    private static Set<Long> getDistinctPrimeFactors(long number) {
        Set<Long> factors = new TreeSet<>();

        // 先把因子 2 全部除掉，方便后面只处理奇数因子。
        while (number % 2 == 0) {
            factors.add(2L);
            number /= 2;
        }

        // 继续用奇数进行试除，找出其余质因子。
        for (long divisor = 3; divisor * divisor <= number; divisor += 2) {
            while (number % divisor == 0) {
                factors.add(divisor);
                number /= divisor;
            }
        }

        // 如果最后还剩一个大于 1 的数，那么它本身也是质因子。
        if (number > 1) {
            factors.add(number);
        }
        return factors;
    }
}

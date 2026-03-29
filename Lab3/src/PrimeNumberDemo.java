public class PrimeNumberDemo {
    private static int halfTestCount = 0;
    private static int sqrtTestCount = 0;

    public static void main(String[] args) {
        run();
    }

    public static void run() {
        System.out.println("========== 题目1：素数 ==========");
        System.out.println("小于10000的全部素数如下：");
        printPrimesUnder10000();
        System.out.println();
        System.out.println("为了找出不超过10000的素数，需要测试 2 到 10000，一共 9999 个数。");
        System.out.println();
        compareTwoWays();
    }

    public static boolean isPrimeHalf(int n) {
        if (n < 2) {
            return false;
        }

        for (int i = 2; i <= n / 2; i++) {
            halfTestCount++;
            if (n % i == 0) {
                return false;
            }
        }

        return true;
    }

    public static boolean isPrimeSqrt(int n) {
        if (n < 2) {
            return false;
        }

        int max = (int) Math.sqrt(n);
        for (int i = 2; i <= max; i++) {
            sqrtTestCount++;
            if (n % i == 0) {
                return false;
            }
        }

        return true;
    }

    private static void printPrimesUnder10000() {
        int count = 0;

        for (int i = 2; i < 10000; i++) {
            if (isPrimeSqrt(i)) {
                System.out.printf("%6d", i);
                count++;
                if (count % 10 == 0) {
                    System.out.println();
                }
            }
        }

        if (count % 10 != 0) {
            System.out.println();
        }

        System.out.println("一共有 " + count + " 个素数。");
        sqrtTestCount = 0;
    }

    private static void compareTwoWays() {
        halfTestCount = 0;
        long start1 = System.currentTimeMillis();
        int primeCount1 = 0;
        for (int i = 2; i <= 10000; i++) {
            if (isPrimeHalf(i)) {
                primeCount1++;
            }
        }
        long end1 = System.currentTimeMillis();

        sqrtTestCount = 0;
        long start2 = System.currentTimeMillis();
        int primeCount2 = 0;
        for (int i = 2; i <= 10000; i++) {
            if (isPrimeSqrt(i)) {
                primeCount2++;
            }
        }
        long end2 = System.currentTimeMillis();

        System.out.println("方法一：最多测试到 n / 2");
        System.out.println("找到的素数个数：" + primeCount1);
        System.out.println("总共做了 " + halfTestCount + " 次整除判断");
        System.out.println("运行时间：" + (end1 - start1) + " 毫秒");
        System.out.println();
        System.out.println("方法二：最多测试到 sqrt(n)");
        System.out.println("找到的素数个数：" + primeCount2);
        System.out.println("总共做了 " + sqrtTestCount + " 次整除判断");
        System.out.println("运行时间：" + (end2 - start2) + " 毫秒");
    }
}

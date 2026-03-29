import java.util.Objects;

/**
 * 有理数类。
 * 使用“分子/分母”的形式精确表示分数，
 * 并继承 Number、实现 Comparable 以满足题目要求。
 */
public class Rational extends Number implements Comparable<Rational> {
    private final int numerator;
    private final int denominator;

    /**
     * 无参构造方法，默认创建 0/1。
     */
    public Rational() {
        this(0, 1);
    }

    /**
     * 创建一个有理数对象。
     * 构造时会完成分母合法性检查、负号归一化和约分。
     */
    public Rational(int numerator, int denominator) {
        if (denominator == 0) {
            throw new IllegalArgumentException("分母不能为0。");
        }

        int normalizedNumerator = numerator;
        int normalizedDenominator = denominator;

        // 统一让分母保持为正数，负号只保留在分子上。
        if (normalizedDenominator < 0) {
            normalizedNumerator = -normalizedNumerator;
            normalizedDenominator = -normalizedDenominator;
        }

        // 对结果进行约分，保证有理数始终是最简形式。
        int gcd = gcd(Math.abs(normalizedNumerator), normalizedDenominator);
        this.numerator = normalizedNumerator / gcd;
        this.denominator = normalizedDenominator / gcd;
    }

    /**
     * 返回分子。
     */
    public int getNumerator() {
        return numerator;
    }

    /**
     * 返回分母。
     */
    public int getDenominator() {
        return denominator;
    }

    /**
     * 有理数加法。
     */
    public Rational add(Rational other) {
        int newNumerator = numerator * other.denominator + denominator * other.numerator;
        int newDenominator = denominator * other.denominator;
        return new Rational(newNumerator, newDenominator);
    }

    /**
     * 有理数减法。
     */
    public Rational subtract(Rational other) {
        int newNumerator = numerator * other.denominator - denominator * other.numerator;
        int newDenominator = denominator * other.denominator;
        return new Rational(newNumerator, newDenominator);
    }

    /**
     * 有理数乘法。
     */
    public Rational multiply(Rational other) {
        int newNumerator = numerator * other.numerator;
        int newDenominator = denominator * other.denominator;
        return new Rational(newNumerator, newDenominator);
    }

    /**
     * 有理数除法。
     * 除数不能是 0。
     */
    public Rational divide(Rational other) {
        if (other.numerator == 0) {
            throw new ArithmeticException("除数不能为0。");
        }

        int newNumerator = numerator * other.denominator;
        int newDenominator = denominator * other.numerator;
        return new Rational(newNumerator, newDenominator);
    }

    /**
     * 比较两个有理数的大小。
     * 通过交叉相乘避免直接转换成浮点数带来的精度问题。
     */
    @Override
    public int compareTo(Rational other) {
        long left = (long) numerator * other.denominator;
        long right = (long) other.numerator * denominator;
        return Long.compare(left, right);
    }

    /**
     * 转换为 int 时保留整数部分。
     */
    @Override
    public int intValue() {
        return numerator / denominator;
    }

    /**
     * 转换为 long 时保留整数部分。
     */
    @Override
    public long longValue() {
        return (long) numerator / denominator;
    }

    /**
     * 转换为 float。
     */
    @Override
    public float floatValue() {
        return (float) numerator / denominator;
    }

    /**
     * 转换为 double。
     */
    @Override
    public double doubleValue() {
        return (double) numerator / denominator;
    }

    /**
     * 判断两个有理数是否相等。
     * 由于构造时已经约分，所以只需直接比较分子和分母。
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Rational other)) {
            return false;
        }
        return numerator == other.numerator && denominator == other.denominator;
    }

    @Override
    public int hashCode() {
        return Objects.hash(numerator, denominator);
    }

    /**
     * 返回有理数的字符串形式。
     * 当分母为 1 时直接输出整数。
     */
    @Override
    public String toString() {
        if (denominator == 1) {
            return String.valueOf(numerator);
        }
        return numerator + "/" + denominator;
    }

    /**
     * 求最大公约数，用于化简分数。
     */
    private static int gcd(int a, int b) {
        if (a == 0) {
            return b;
        }
        while (b != 0) {
            int temp = a % b;
            a = b;
            b = temp;
        }
        return a;
    }
}

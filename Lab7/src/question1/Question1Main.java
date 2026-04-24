package question1;

import java.util.Objects;

/**
 * 演示泛型比较方法，并说明 equals 在不同对象类型上的行为差异。
 */
public class Question1Main {

    /**
     * 使用运行时类型的 equals 规则比较两个值。
     * 对包装类和 String 来说比较的是内容，
     * 对普通 Object 来说比较的是对象引用。
     */
    public static <T> boolean isEqualTo(T left, T right) {
        return Objects.equals(left, right);
    }

    /**
     * 输出一组带标签的比较结果，避免 main 方法里充满重复的格式化代码。
     */
    private static <T> void printComparison(String label, T left, T right) {
        System.out.printf("%s -> %s%n", label, isEqualTo(left, right));
    }

    public static void main(String[] args) {
        // 两个数值相同的 Integer 对象。
        Integer integerA = 128;
        Integer integerB = 128;

        // 两个内容相同但引用不同的 String 对象。
        String stringA = new String("Java");
        String stringB = new String("Java");

        // objectC 与 objectA 指向同一对象，而 objectB 指向另一个对象。
        Object objectA = new Object();
        Object objectB = new Object();
        Object objectC = objectA;

        System.out.println("Generic method isEqualTo demo");
        // Integer 重写了 equals，因此比较的是数值内容。
        printComparison("Integer 128 vs 128", integerA, integerB);
        // String 同样重写了 equals，因此比较的是字符串内容。
        printComparison("String \"Java\" vs \"Java\"", stringA, stringB);
        // 同一引用时，Object.equals 返回 true。
        printComparison("Object same reference", objectA, objectC);
        // 不同引用时，Object.equals 返回 false。
        printComparison("Object different reference", objectA, objectB);

        System.out.println();
        System.out.println("Conclusion:");
        // 总结本题要求观察到的比较结论。
        System.out.println("1. isEqualTo delegates comparison to equals.");
        System.out.println("2. Integer and String override equals, so their contents are compared.");
        System.out.println("3. Object keeps reference-based equals, so only the same object returns true.");
    }
}

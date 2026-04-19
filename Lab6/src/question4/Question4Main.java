package question4;

/**
 * 演示简单泛型方法 isEqualTo 的使用方式。
 */
public class Question4Main {
    public static void main(String[] args) {
        // 准备不同类型的测试数据，观察 equals 在不同对象上的比较行为。
        Integer intA = 128;
        Integer intB = 128;
        String strA = "Java";
        String strB = new String("Java");
        Object objA = new Object();
        Object objB = new Object();
        Object objC = objA;

        // 依次比较包装类、字符串和普通对象，输出泛型方法的判断结果。
        System.out.println("整数比较结果：" + isEqualTo(intA, intB));
        System.out.println("字符串比较结果：" + isEqualTo(strA, strB));
        System.out.println("对象比较结果（不同引用）：" 
                + isEqualTo(objA, objB));
        System.out.println("对象比较结果（相同引用）：" 
                + isEqualTo(objA, objC));
        System.out.println(
                "结论：重写了 equals 方法的类比较的是内容，"
                        + "而普通 Object 比较的是引用地址。"
        );
    }

    private static <T> boolean isEqualTo(T first, T second) {
        // 先单独处理空引用，避免直接调用 equals 产生空指针异常。
        if (first == null) {
            return second == null;
        }
        // 非空情况下直接使用对象自身的 equals 规则进行比较。
        return first.equals(second);
    }
}

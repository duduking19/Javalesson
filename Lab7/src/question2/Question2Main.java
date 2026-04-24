package question2;

/**
 * 演示带两个类型参数的泛型类 Pair。
 */
public class Question2Main {

    public static void main(String[] args) {
        // Pair<String, Integer> 可用于表示“姓名-分数”这样的数据对。
        Pair<String, Integer> student = new Pair<>("Alice", 95);
        // Pair<Double, String> 说明同一个泛型类可以适配另一组类型参数。
        Pair<Double, String> measurement = new Pair<>(36.5, "Celsius");

        System.out.println("Initial pairs:");
        System.out.println("student = " + student);
        System.out.println("measurement = " + measurement);

        // 修改两个对象中的值，验证 setter 方法是否工作正常。
        student.setFirst("Bob");
        student.setSecond(88);
        measurement.setFirst(98.6);
        measurement.setSecond("Fahrenheit");

        System.out.println();
        System.out.println("After set operations:");
        System.out.println("student first  = " + student.getFirst());
        System.out.println("student second = " + student.getSecond());
        System.out.println("measurement    = " + measurement);
    }
}

/**
 * 保存一对相关的数据，具体类型由调用者决定。
 *
 * @param <F> 第一个元素的类型
 * @param <S> 第二个元素的类型
 */
class Pair<F, S> {
    // 第一项和第二项彼此独立，因此分别使用不同的类型参数描述。
    private F first;
    private S second;

    /**
     * 使用给定的两个值初始化对象。
     */
    public Pair(F first, S second) {
        this.first = first;
        this.second = second;
    }

    /**
     * 返回第一个元素。
     */
    public F getFirst() {
        return first;
    }

    /**
     * 用相同类型的新值替换第一个元素。
     */
    public void setFirst(F first) {
        this.first = first;
    }

    /**
     * 返回第二个元素。
     */
    public S getSecond() {
        return second;
    }

    /**
     * 用相同类型的新值替换第二个元素。
     */
    public void setSecond(S second) {
        this.second = second;
    }

    @Override
    public String toString() {
        // 以简洁形式输出，便于主程序直接打印观察结果。
        return "(" + first + ", " + second + ")";
    }
}

/**
 * 第2题测试类。
 * 用于创建两个有理数对象并验证四则运算结果。
 */
public class Question2Main {
    public static void main(String[] args) {
        // 创建两个用于测试的有理数。
        Rational rational1 = new Rational(1, 2);
        Rational rational2 = new Rational(3, 4);

        // 依次输出两个有理数及其加减乘除结果。
        System.out.println("========== 第2题：有理数运算 ==========");
        System.out.println("有理数1：" + rational1);
        System.out.println("有理数2：" + rational2);
        System.out.println("加法结果：" + rational1.add(rational2));
        System.out.println("减法结果：" + rational1.subtract(rational2));
        System.out.println("乘法结果：" + rational1.multiply(rational2));
        System.out.println("除法结果：" + rational1.divide(rational2));
    }
}

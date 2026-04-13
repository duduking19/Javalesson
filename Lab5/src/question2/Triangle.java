/**
 * 三角形类，使用三边长和海伦公式计算面积。
 */
public class Triangle extends Graph {
    private final double a;
    private final double b;
    private final double c;

    /**
     * 创建三角形对象，并检查三边是否满足构成三角形的条件。
     *
     * @param a 第一条边
     * @param b 第二条边
     * @param c 第三条边
     */
    public Triangle(double a, double b, double c) {
        if (a + b <= c || a + c <= b || b + c <= a) {
            throw new IllegalArgumentException("输入的三条边不能构成三角形。");
        }
        this.a = a;
        this.b = b;
        this.c = c;
    }

    /**
     * 使用海伦公式计算三角形面积。
     *
     * @return 三角形面积
     */
    @Override
    public double getArea() {
        double p = (a + b + c) / 2.0;
        return Math.sqrt(p * (p - a) * (p - b) * (p - c));
    }
}

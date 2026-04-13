/**
 * 矩形类，使用长和宽计算面积。
 */
public class Rectangle extends Graph {
    private final double width;
    private final double height;

    /**
     * 创建矩形对象。
     *
     * @param width 矩形的宽
     * @param height 矩形的高
     */
    public Rectangle(double width, double height) {
        this.width = width;
        this.height = height;
    }

    /**
     * 计算矩形面积。
     *
     * @return 矩形面积
     */
    @Override
    public double getArea() {
        return width * height;
    }
}

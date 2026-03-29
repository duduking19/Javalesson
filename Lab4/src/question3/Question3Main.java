import java.util.Scanner;

/**
 * 第3题主类。
 * 根据每行输入的数字个数创建不同图形对象，并输出面积。
 */
public class Question3Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 第一行输入图形总数。
        int count = Integer.parseInt(scanner.nextLine().trim());

        // 逐个读取图形参数，创建图形并输出面积。
        for (int i = 0; i < count; i++) {
            String line = readNonEmptyLine(scanner);
            String[] parts = line.split("\\s+");
            Graph graph = createGraph(parts);
            System.out.println(formatArea(graph.getArea()));
        }
    }

    /**
     * 读取一行非空输入，避免因为空行导致程序解析失败。
     */
    private static String readNonEmptyLine(Scanner scanner) {
        String line = scanner.nextLine();
        while (line.trim().isEmpty()) {
            line = scanner.nextLine();
        }
        return line.trim();
    }

    /**
     * 根据输入参数个数决定图形类型。
     * 2 个数字表示矩形，3 个数字表示三角形。
     */
    private static Graph createGraph(String[] parts) {
        if (parts.length == 2) {
            double width = Double.parseDouble(parts[0]);
            double height = Double.parseDouble(parts[1]);
            return new Rectangle(width, height);
        }
        if (parts.length == 3) {
            double a = Double.parseDouble(parts[0]);
            double b = Double.parseDouble(parts[1]);
            double c = Double.parseDouble(parts[2]);
            return new Triangle(a, b, c);
        }
        throw new IllegalArgumentException("每行只能输入2个或3个整数。");
    }

    /**
     * 按题目样例风格格式化面积。
     * 如果是整数则直接输出整数；
     * 如果是小数，则按当前实验要求保留整数部分输出。
     */
    private static String formatArea(double area) {
        long rounded = Math.round(area);
        if (Math.abs(area - rounded) < 1e-9) {
            return String.valueOf(rounded);
        }
        return String.valueOf((long) area);
    }
}

/**
 * 图形抽象父类。
 * 只规定所有图形都必须实现“求面积”的方法。
 */
abstract class Graph {
    public abstract double getArea();
}

/**
 * 矩形类。
 * 使用长和宽计算面积。
 */
class Rectangle extends Graph {
    private final double width;
    private final double height;

    public Rectangle(double width, double height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public double getArea() {
        return width * height;
    }
}

/**
 * 三角形类。
 * 使用三边长，通过海伦公式计算面积。
 */
class Triangle extends Graph {
    private final double a;
    private final double b;
    private final double c;

    public Triangle(double a, double b, double c) {
        // 构造对象前先判断三边是否可以组成三角形。
        if (a + b <= c || a + c <= b || b + c <= a) {
            throw new IllegalArgumentException("输入的三条边不能构成三角形。");
        }
        this.a = a;
        this.b = b;
        this.c = c;
    }

    @Override
    public double getArea() {
        double p = (a + b + c) / 2.0;
        return Math.sqrt(p * (p - a) * (p - b) * (p - c));
    }
}

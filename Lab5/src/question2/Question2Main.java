import java.util.Scanner;

/**
 * 第 2 题主类。
 * 根据每行输入的参数个数创建不同图形对象，并通过多态输出面积。
 */
public class Question2Main {
    /**
     * 程序入口，读取图形数量和各图形参数，输出对应面积。
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int count = Integer.parseInt(readNonEmptyLine(scanner));

        for (int i = 0; i < count; i++) {
            String[] parts = readNonEmptyLine(scanner).split("\\s+");
            // 这里使用 Graph 父类引用接收子类对象，体现多态。
            Graph graph = createGraph(parts);
            System.out.println(formatArea(graph.getArea()));
        }
    }

    /**
     * 读取一行非空输入，避免空行影响后续解析。
     *
     * @param scanner 输入扫描器
     * @return 去除首尾空格后的非空字符串
     */
    private static String readNonEmptyLine(Scanner scanner) {
        String line = scanner.nextLine();
        while (line.trim().isEmpty()) {
            line = scanner.nextLine();
        }
        return line.trim();
    }

    /**
     * 根据输入数字的个数创建对应图形对象。
     * 2 个数字表示矩形，3 个数字表示三角形。
     *
     * @param parts 当前行拆分后的参数数组
     * @return 创建好的图形对象
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
     * 按题目样例要求格式化面积。
     * 如果结果是整数则直接输出；否则输出整数部分。
     *
     * @param area 原始面积
     * @return 格式化后的面积字符串
     */
    private static String formatArea(double area) {
        long rounded = Math.round(area);
        if (Math.abs(area - rounded) < 1e-9) {
            return String.valueOf(rounded);
        }
        return String.valueOf((long) area);
    }
}

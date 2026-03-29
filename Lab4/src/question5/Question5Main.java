/**
 * 第5题测试类。
 * 遍历输出每种交通信号灯的 RGB 值。
 */
public class Question5Main {
    public static void main(String[] args) {
        System.out.println("========== 第5题：交通信号灯 RGB ==========");

        // 通过 values() 获取全部枚举常量，再逐个输出。
        for (TrafficLight trafficLight : TrafficLight.values()) {
            System.out.printf("%s -> RGB(%d, %d, %d)%n",
                    trafficLight.getDisplayName(),
                    trafficLight.getRed(),
                    trafficLight.getGreen(),
                    trafficLight.getBlue());
        }
    }
}

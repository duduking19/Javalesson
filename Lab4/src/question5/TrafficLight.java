/**
 * 交通信号灯枚举类。
 * 每个枚举值都封装了颜色名称和对应的 RGB 数值。
 */
public enum TrafficLight {
    RED("红灯", 255, 0, 0),
    YELLOW("黄灯", 255, 255, 0),
    GREEN("绿灯", 0, 255, 0);

    private final String displayName;
    private final int red;
    private final int green;
    private final int blue;

    /**
     * 枚举构造方法。
     * 用于初始化每种灯的中文名称和 RGB 值。
     */
    TrafficLight(String displayName, int red, int green, int blue) {
        this.displayName = displayName;
        this.red = red;
        this.green = green;
        this.blue = blue;
    }

    /**
     * 返回交通灯的中文名称。
     */
    public String getDisplayName() {
        return displayName;
    }

    /**
     * 返回红色分量。
     */
    public int getRed() {
        return red;
    }

    /**
     * 返回绿色分量。
     */
    public int getGreen() {
        return green;
    }

    /**
     * 返回蓝色分量。
     */
    public int getBlue() {
        return blue;
    }
}

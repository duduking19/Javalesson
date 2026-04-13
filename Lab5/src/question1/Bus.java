/**
 * 公共汽车类，继承自汽车类。
 */
public class Bus extends Motor {
    /**
     * 输出公共汽车的运行信息。
     */
    @Override
    public void run() {
        System.out.println("这是公共汽车run方法");
    }
}

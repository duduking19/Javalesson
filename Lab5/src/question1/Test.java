/**
 * 测试类，用于演示继承关系和多态调用效果。
 */
public class Test {
    /**
     * 创建各类交通工具对象，并分别以普通调用和多态调用方式执行 run 方法。
     */
    public static void main(String[] args) {
        Vehicle vehicle = new Vehicle();
        Motor motor = new Motor();
        Ship ship = new Ship();
        Aeroplane aeroplane = new Aeroplane();
        Bus bus = new Bus();
        Car car = new Car();

        vehicle.run();
        motor.run();
        ship.run();
        aeroplane.run();
        bus.run();
        car.run();

        // 使用父类引用统一保存不同子类对象，演示运行时多态。
        System.out.println("--------多态调用--------");
        Vehicle[] vehicles = {vehicle, motor, ship, aeroplane, bus, car};
        for (Vehicle item : vehicles) {
            item.run();
        }
    }
}

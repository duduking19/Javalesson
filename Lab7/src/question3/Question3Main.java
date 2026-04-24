package question3;

import java.util.ArrayList;
import java.util.List;

/**
 * 使用 CarbonFootprint 接口演示多态调用。
 */
public class Question3Main {

    public static void main(String[] args) {
        // 列表声明为接口类型，这样不同实现类都能放进去统一处理。
        List<CarbonFootprint> items = new ArrayList<>();
        items.add(new Building("Teaching Building", 12000));
        items.add(new Car("Compact Petrol Car", 15000));
        items.add(new Bicycle("City Bicycle", 2000));

        System.out.println("Carbon footprint summary (kg CO2e / year)");
        // 循环只依赖接口方法，具体计算逻辑由各个实现类自己完成。
        for (CarbonFootprint item : items) {
            System.out.printf(
                    "%-18s %-22s %.2f%n",
                    item.getCategory(),
                    item.getDescription(),
                    item.getCarbonFootprint()
            );
        }
    }
}

/**
 * 能够估算年度碳足迹的对象都要遵守这组方法约定。
 */
interface CarbonFootprint {
    /**
     * 返回估算得到的年度碳足迹，单位为 kg CO2e。
     */
    double getCarbonFootprint();

    /**
     * 返回类别名称，用于汇总输出。
     */
    String getCategory();

    /**
     * 返回对象的描述信息，便于输出时区分样本。
     */
    String getDescription();
}

/**
 * 建筑物类，根据年度用电量估算碳足迹。
 */
class Building implements CarbonFootprint {
    // 每千瓦时电力对应的近似排放因子，单位为 kg CO2e。
    private static final double ELECTRICITY_FACTOR = 0.556;

    private final String name;
    private final double annualElectricityKwh;

    public Building(String name, double annualElectricityKwh) {
        this.name = name;
        this.annualElectricityKwh = annualElectricityKwh;
    }

    @Override
    public double getCarbonFootprint() {
        // 用电量乘以排放因子，得到年度碳排放估算值。
        return annualElectricityKwh * ELECTRICITY_FACTOR;
    }

    @Override
    public String getCategory() {
        return "Building";
    }

    @Override
    public String getDescription() {
        return name + ", " + annualElectricityKwh + " kWh";
    }
}

/**
 * 汽车类，根据年度行驶里程估算碳足迹。
 */
class Car implements CarbonFootprint {
    // 每公里对应的近似排放因子，单位为 kg CO2e。
    private static final double CAR_FACTOR = 0.175;

    private final String model;
    private final double annualKilometers;

    public Car(String model, double annualKilometers) {
        this.model = model;
        this.annualKilometers = annualKilometers;
    }

    @Override
    public double getCarbonFootprint() {
        // 按行驶里程估算年度排放量。
        return annualKilometers * CAR_FACTOR;
    }

    @Override
    public String getCategory() {
        return "Car";
    }

    @Override
    public String getDescription() {
        return model + ", " + annualKilometers + " km";
    }
}

/**
 * 自行车类，用较小的距离因子模拟其较低的碳足迹。
 */
class Bicycle implements CarbonFootprint {
    // 使用较小因子体现自行车出行的低排放特点。
    private static final double BICYCLE_FACTOR = 0.005;

    private final String type;
    private final double annualKilometers;

    public Bicycle(String type, double annualKilometers) {
        this.type = type;
        this.annualKilometers = annualKilometers;
    }

    @Override
    public double getCarbonFootprint() {
        // 即便是低排放交通工具，也可以通过同一接口统一建模和计算。
        return annualKilometers * BICYCLE_FACTOR;
    }

    @Override
    public String getCategory() {
        return "Bicycle";
    }

    @Override
    public String getDescription() {
        return type + ", " + annualKilometers + " km";
    }
}

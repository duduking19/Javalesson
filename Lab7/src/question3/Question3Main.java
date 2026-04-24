package question3;

import java.util.ArrayList;
import java.util.List;

public class Question3Main {

    public static void main(String[] args) {
        List<CarbonFootprint> items = new ArrayList<>();
        items.add(new Building("Teaching Building", 12000));
        items.add(new Car("Compact Petrol Car", 15000));
        items.add(new Bicycle("City Bicycle", 2000));

        System.out.println("Carbon footprint summary (kg CO2e / year)");
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

interface CarbonFootprint {
    double getCarbonFootprint();

    String getCategory();

    String getDescription();
}

class Building implements CarbonFootprint {
    private static final double ELECTRICITY_FACTOR = 0.556;

    private final String name;
    private final double annualElectricityKwh;

    public Building(String name, double annualElectricityKwh) {
        this.name = name;
        this.annualElectricityKwh = annualElectricityKwh;
    }

    @Override
    public double getCarbonFootprint() {
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

class Car implements CarbonFootprint {
    private static final double CAR_FACTOR = 0.175;

    private final String model;
    private final double annualKilometers;

    public Car(String model, double annualKilometers) {
        this.model = model;
        this.annualKilometers = annualKilometers;
    }

    @Override
    public double getCarbonFootprint() {
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

class Bicycle implements CarbonFootprint {
    private static final double BICYCLE_FACTOR = 0.005;

    private final String type;
    private final double annualKilometers;

    public Bicycle(String type, double annualKilometers) {
        this.type = type;
        this.annualKilometers = annualKilometers;
    }

    @Override
    public double getCarbonFootprint() {
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

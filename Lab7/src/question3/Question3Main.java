package question3;

import java.util.ArrayList;
import java.util.List;

/**
 * Demonstrates polymorphism with a CarbonFootprint interface.
 */
public class Question3Main {

    public static void main(String[] args) {
        // The list is typed to the interface so all implementations can be handled uniformly.
        List<CarbonFootprint> items = new ArrayList<>();
        items.add(new Building("Teaching Building", 12000));
        items.add(new Car("Compact Petrol Car", 15000));
        items.add(new Bicycle("City Bicycle", 2000));

        System.out.println("Carbon footprint summary (kg CO2e / year)");
        // Each object computes its own result, but the loop only depends on the interface.
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
 * Common contract for objects that can estimate an annual carbon footprint.
 */
interface CarbonFootprint {
    /**
     * Returns the estimated annual carbon footprint in kilograms of CO2e.
     */
    double getCarbonFootprint();

    /**
     * Returns a short category label used in the summary table.
     */
    String getCategory();

    /**
     * Returns object-specific descriptive information for display.
     */
    String getDescription();
}

/**
 * Models a building whose footprint is estimated from annual electricity usage.
 */
class Building implements CarbonFootprint {
    // Approximate emission factor in kg CO2e per kWh.
    private static final double ELECTRICITY_FACTOR = 0.556;

    private final String name;
    private final double annualElectricityKwh;

    public Building(String name, double annualElectricityKwh) {
        this.name = name;
        this.annualElectricityKwh = annualElectricityKwh;
    }

    @Override
    public double getCarbonFootprint() {
        // Electricity consumption multiplied by the chosen factor gives the yearly estimate.
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
 * Models a car whose footprint is estimated from annual travel distance.
 */
class Car implements CarbonFootprint {
    // Approximate emission factor in kg CO2e per kilometer for the sample car.
    private static final double CAR_FACTOR = 0.175;

    private final String model;
    private final double annualKilometers;

    public Car(String model, double annualKilometers) {
        this.model = model;
        this.annualKilometers = annualKilometers;
    }

    @Override
    public double getCarbonFootprint() {
        // Distance-based estimate for annual driving emissions.
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
 * Models a bicycle whose footprint is very low and is estimated by distance.
 */
class Bicycle implements CarbonFootprint {
    // Small factor used to represent the low operational impact of cycling.
    private static final double BICYCLE_FACTOR = 0.005;

    private final String type;
    private final double annualKilometers;

    public Bicycle(String type, double annualKilometers) {
        this.type = type;
        this.annualKilometers = annualKilometers;
    }

    @Override
    public double getCarbonFootprint() {
        // Even a low-impact transport mode can still be represented with the same interface.
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

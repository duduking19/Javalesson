package question5;

/**
 * 演示使用组合而非继承来组织员工薪酬模型。
 */
public class Question5Main {
    public static void main(String[] args) {
        // 先为四类薪酬模型分别创建员工对象。
        Employee[] employees = {
                new Employee("张", "三", "1001",
                        new SalariedCompensationModel(2500.0)),
                new Employee("李", "四", "1002",
                        new HourlyCompensationModel(80.0, 46.0)),
                new Employee("王", "五", "1003",
                        new CommissionCompensationModel(32000.0, 0.06)),
                new Employee("赵", "六", "1004",
                        new BasePlusCommissionCompensationModel(28000.0, 0.05, 1500.0))
        };

        // 输出每位员工在初始薪酬模型下的收入。
        System.out.println("原始薪酬模型：");
        for (Employee employee : employees) {
            printEmployee(employee);
        }

        // 运行期间动态切换薪酬模型，体现组合方式的灵活性。
        employees[0].setCompensationModel(new CommissionCompensationModel(18000.0, 0.08));
        employees[1].setCompensationModel(new SalariedCompensationModel(3200.0));
        employees[2].setCompensationModel(new HourlyCompensationModel(95.0, 42.0));
        employees[3].setCompensationModel(
                new BasePlusCommissionCompensationModel(36000.0, 0.04, 1800.0)
        );

        // 再次输出修改后的收入，观察切换模型后的计算结果。
        System.out.println();
        System.out.println("更换薪酬模型后：");
        for (Employee employee : employees) {
            printEmployee(employee);
        }
    }

    private static void printEmployee(Employee employee) {
        // 统一输出员工信息和当前薪酬模型计算出的收入。
        System.out.printf("%s，收入 = %.2f 元%n", employee, employee.earnings());
    }
}

interface CompensationModel {
    // 所有薪酬模型都必须提供统一的收入计算方法。
    double earnings();
}

class SalariedCompensationModel implements CompensationModel {
    private final double weeklySalary;

    SalariedCompensationModel(double weeklySalary) {
        // 固定周薪不能为负数。
        if (weeklySalary < 0.0) {
            throw new IllegalArgumentException("固定周薪不能小于 0。");
        }
        this.weeklySalary = weeklySalary;
    }

    @Override
    public double earnings() {
        return weeklySalary;
    }

    @Override
    public String toString() {
        return String.format("固定周薪模式[周薪=%.2f元]", weeklySalary);
    }
}

class HourlyCompensationModel implements CompensationModel {
    private final double wage;
    private final double hours;

    HourlyCompensationModel(double wage, double hours) {
        // 时薪和工时都必须是非负数。
        if (wage < 0.0 || hours < 0.0) {
            throw new IllegalArgumentException("时薪和工时都不能小于 0。");
        }
        this.wage = wage;
        this.hours = hours;
    }

    @Override
    public double earnings() {
        // 40 小时以内按正常时薪计算，超出部分按 1.5 倍加班工资计算。
        if (hours <= 40.0) {
            return wage * hours;
        }
        return 40.0 * wage + (hours - 40.0) * wage * 1.5;
    }

    @Override
    public String toString() {
        return String.format("小时工资模式[时薪=%.2f元, 工时=%.2f]", wage, hours);
    }
}

class CommissionCompensationModel implements CompensationModel {
    private final double grossSales;
    private final double commissionRate;

    CommissionCompensationModel(double grossSales, double commissionRate) {
        // 销售额必须非负，提成比例必须处于 0 到 1 之间。
        if (grossSales < 0.0 || commissionRate < 0.0 || commissionRate > 1.0) {
            throw new IllegalArgumentException("销售额或提成比例不合法。");
        }
        this.grossSales = grossSales;
        this.commissionRate = commissionRate;
    }

    @Override
    public double earnings() {
        return grossSales * commissionRate;
    }

    @Override
    public String toString() {
        return String.format(
                "佣金模式[销售额=%.2f元, 提成比例=%.2f]",
                grossSales,
                commissionRate
        );
    }
}

class BasePlusCommissionCompensationModel implements CompensationModel {
    private final double grossSales;
    private final double commissionRate;
    private final double baseSalary;

    BasePlusCommissionCompensationModel(
            double grossSales,
            double commissionRate,
            double baseSalary
    ) {
        // 底薪加提成模式下，销售额、提成率和底薪都需要满足合法范围。
        if (grossSales < 0.0 || commissionRate < 0.0 || commissionRate > 1.0
                || baseSalary < 0.0) {
            throw new IllegalArgumentException("底薪加提成模式的参数不合法。");
        }
        this.grossSales = grossSales;
        this.commissionRate = commissionRate;
        this.baseSalary = baseSalary;
    }

    @Override
    public double earnings() {
        return baseSalary + grossSales * commissionRate;
    }

    @Override
    public String toString() {
        return String.format(
                "底薪加提成模式[销售额=%.2f元, 提成比例=%.2f, 底薪=%.2f元]",
                grossSales,
                commissionRate,
                baseSalary
        );
    }
}

class Employee {
    private final String firstName;
    private final String lastName;
    private final String socialSecurityNumber;
    private CompensationModel compensationModel;

    Employee(
            String firstName,
            String lastName,
            String socialSecurityNumber,
            CompensationModel compensationModel
    ) {
        // 创建员工对象时必须同时绑定一个有效的薪酬模型。
        if (compensationModel == null) {
            throw new IllegalArgumentException("薪酬模型不能为空。");
        }
        this.firstName = firstName;
        this.lastName = lastName;
        this.socialSecurityNumber = socialSecurityNumber;
        this.compensationModel = compensationModel;
    }

    double earnings() {
        // 员工收入的具体计算委托给当前持有的薪酬模型完成。
        return compensationModel.earnings();
    }

    void setCompensationModel(CompensationModel compensationModel) {
        // 允许在运行期间切换薪酬模型，但不允许设置为空。
        if (compensationModel == null) {
            throw new IllegalArgumentException("薪酬模型不能为空。");
        }
        this.compensationModel = compensationModel;
    }

    @Override
    public String toString() {
        return "姓名：" + firstName + lastName + "，工号：" + socialSecurityNumber
                + "，薪酬方式：" + compensationModel;
    }
}

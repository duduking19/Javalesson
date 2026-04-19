package question3;

/**
 * 演示自定义异常的定义、抛出与捕获。
 */
public class Question3Main {
    public static void main(String[] args) {
        Student student = new Student();

        try {
            // 先故意传入非法姓名，验证姓名异常是否能被正确触发。
            student.setName("");
        } catch (IllegaNameException e) {
            System.out.println("姓名错误：" + e.getMessage());
        }

        try {
            // 再传入不包含省或市关键字的地址，验证地址异常处理。
            student.setAddress("NoProvinceOrCityKeyword");
        } catch (IllegalAddressException e) {
            System.out.println("地址错误：" + e.getMessage());
        }

        try {
            // 最后传入合法数据，验证对象在正常情况下可以完成赋值。
            student.setName("小明");
            student.setAddress("福建省 厦门市");
            System.out.println("合法学生对象创建成功。");
        } catch (IllegaNameException | IllegalAddressException e) {
            System.out.println("出现未预期的校验异常：" + e.getMessage());
        }
    }
}

class Student {
    private String name;
    private String address;

    void setName(String name) throws IllegaNameException {
        // 姓名长度必须在 1 到 5 之间，否则抛出自定义姓名异常。
        if (name == null || name.length() < 1 || name.length() > 5) {
            throw new IllegaNameException(
                    "姓名长度必须在 1 到 5 个字符之间。"
            );
        }
        this.name = name;
    }

    void setAddress(String address) throws IllegalAddressException {
        // 地址中必须至少包含“省”或“市”关键字，否则视为非法地址。
        if (address == null
                || (!address.contains("\u7701") && !address.contains("\u5e02"))) {
            throw new IllegalAddressException(
                    "地址中必须包含“省”或“市”关键字。"
            );
        }
        this.address = address;
    }

    @Override
    public String toString() {
        return "学生{name='" + name + "', address='" + address + "'}";
    }
}

class IllegaNameException extends Exception {
    // 表示姓名不满足题目要求时抛出的异常。
    IllegaNameException(String message) {
        super(message);
    }
}

class IllegalAddressException extends Exception {
    // 表示地址不满足题目要求时抛出的异常。
    IllegalAddressException(String message) {
        super(message);
    }
}

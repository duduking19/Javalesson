package question1;

/**
 * 演示构造方法失败时通过异常向外报告错误信息。
 */
public class Question1Main {
    public static void main(String[] args) {
        // 先测试合法输入，再测试会导致构造失败的非法输入。
        tryCreate("示例对象");
        tryCreate("   ");
    }

    private static void tryCreate(String name) {
        try {
            // 尝试创建对象，如果构造方法内部校验失败会直接抛出异常。
            SomeClass instance = new SomeClass(name);
            System.out.println("对象创建成功：" + instance.getName());
        } catch (ConstructionFailureException e) {
            // 在调用端统一捕获构造失败异常，并输出失败原因。
            System.out.println("构造方法执行失败：" + e.getMessage());
        }
    }
}

class SomeClass {
    private final String name;

    SomeClass(String name) throws ConstructionFailureException {
        // 构造阶段完成参数合法性检查，不允许空值或全空白字符串。
        if (name == null || name.trim().isEmpty()) {
            throw new ConstructionFailureException(
                    "初始化数据不能为空。"
            );
        }
        // 校验通过后再保存规范化后的名称。
        this.name = name.trim();
    }

    String getName() {
        return name;
    }
}

class ConstructionFailureException extends Exception {
    // 自定义异常类型，用于表示对象构造阶段发生的失败。
    ConstructionFailureException(String message) {
        super(message);
    }
}

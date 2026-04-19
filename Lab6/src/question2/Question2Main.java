package question2;

/**
 * 演示异常的捕获与重抛过程。
 */
public class Question2Main {
    public static void main(String[] args) {
        try {
            // main 作为最外层调用者，触发整个异常传递链。
            someMethod();
        } catch (Exception e) {
            // 最终在 main 中接住被重抛的异常并输出栈踪迹。
            System.out.println("main 方法捕获到重抛异常。");
            e.printStackTrace();
        }
    }

    private static void someMethod() throws Exception {
        try {
            // 调用真正抛出异常的方法。
            someMethod2();
        } catch (Exception e) {
            // 中间层先捕获异常，再原样向上层继续抛出。
            System.out.println("someMethod 捕获异常后重新抛出。");
            throw e;
        }
    }

    private static void someMethod2() throws Exception {
        // 在最底层直接制造异常，作为重抛示例的起点。
        throw new Exception("异常在 someMethod2 中创建。");
    }
}

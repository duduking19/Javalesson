package question4;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.Scanner;

/**
 * 从控制台读取姓名，并使用 Set 完成去重。
 */
public class Question4Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // LinkedHashSet 既能去重，又能保留第一次输入时的顺序。
        Set<String> uniqueNames = new LinkedHashSet<>();
        // 记录用户一共输入了多少个有效姓名，重复项也计入。
        int totalInputs = 0;

        System.out.println("Enter names one per line. Type END to finish.");
        while (scanner.hasNextLine()) {
            String name = scanner.nextLine().trim();
            // END 作为结束标记，输入后停止读取。
            if ("END".equalsIgnoreCase(name)) {
                break;
            }
            // 空行不参与统计，也不加入集合。
            if (name.isEmpty()) {
                continue;
            }
            totalInputs++;
            // Set 中天然不允许重复元素，重复输入不会再次加入。
            uniqueNames.add(name);
        }

        System.out.println();
        // 同时输出总输入数和去重后的结果，便于观察去重效果。
        System.out.println("Total names entered : " + totalInputs);
        System.out.println("Unique names count  : " + uniqueNames.size());
        System.out.println("Unique names        : " + uniqueNames);
    }
}

package question6;

import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

/**
 * 统计输入文本中每个字母出现的次数。
 */
public class Question6Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter text:");
        // 统一转成小写，这样大小写字母会合并统计。
        String text = scanner.nextLine().toLowerCase();

        // TreeMap 会按字符自然顺序排序，输出时就是按字母顺序排列。
        Map<Character, Integer> letterCounts = new TreeMap<>();
        for (char ch : text.toCharArray()) {
            // 忽略空格、数字和标点，只统计真正的字母字符。
            if (Character.isLetter(ch)) {
                letterCounts.merge(ch, 1, Integer::sum);
            }
        }

        System.out.println();
        System.out.println("Letter counts:");
        // 按排序后的键顺序输出字母频次结果。
        for (Map.Entry<Character, Integer> entry : letterCounts.entrySet()) {
            System.out.printf("%s -> %d%n", entry.getKey(), entry.getValue());
        }
    }
}

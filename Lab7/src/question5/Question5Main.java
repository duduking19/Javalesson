package question5;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * 在忽略大小写和标点的前提下，统计句子中的重复单词。
 */
public class Question5Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a sentence:");
        String sentence = scanner.nextLine();

        // 先统一转成小写，再把标点替换为空格，保证后续拆分单词时更稳定。
        String normalized = sentence.toLowerCase().replaceAll("[^\\p{L}\\p{Nd}]+", " ").trim();
        // LinkedHashMap 保留单词第一次出现的顺序，输出更直观。
        Map<String, Integer> frequencies = new LinkedHashMap<>();

        if (!normalized.isEmpty()) {
            // 按空白符拆分清洗后的句子，并统计每个单词的出现次数。
            for (String word : normalized.split("\\s+")) {
                frequencies.merge(word, 1, Integer::sum);
            }
        }

        // 统计“有重复的不同单词”有多少种。
        int repeatedWordTypes = 0;
        // 统计重复出来的总次数，不包含每个单词第一次出现的那次。
        int repeatedOccurrences = 0;

        System.out.println();
        System.out.println("Repeated words:");
        for (Map.Entry<String, Integer> entry : frequencies.entrySet()) {
            // 只有出现次数大于 1 的单词才算重复单词。
            if (entry.getValue() > 1) {
                repeatedWordTypes++;
                repeatedOccurrences += entry.getValue() - 1;
                System.out.printf("%s -> %d%n", entry.getKey(), entry.getValue());
            }
        }

        System.out.println();
        // 输出重复单词的种类数和多出来的重复次数。
        System.out.println("Distinct repeated words : " + repeatedWordTypes);
        System.out.println("Extra repeated count    : " + repeatedOccurrences);
    }
}

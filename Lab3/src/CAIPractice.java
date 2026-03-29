import java.security.SecureRandom;
import java.util.Scanner;

public class CAIPractice {
    private static final SecureRandom RANDOM = new SecureRandom();
    private static int number1;
    private static int number2;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        run(scanner);
    }

    public static void run(Scanner scanner) {
        System.out.println("========== 题目3：计算机辅助教学 ==========");
        System.out.println("输入 -1 可以结束练习。");
        askNewQuestion();

        while (true) {
            int answer = readInt(scanner, "请输入答案：");

            if (answer == -1) {
                System.out.println("练习结束。");
                break;
            }

            if (answer == number1 * number2) {
                System.out.println("Very good!");
                askNewQuestion();
            } else {
                System.out.println("No. Please try again.");
                printCurrentQuestion();
            }
        }
    }

    private static void askNewQuestion() {
        number1 = RANDOM.nextInt(9) + 1;
        number2 = RANDOM.nextInt(9) + 1;
        printCurrentQuestion();
    }

    private static void printCurrentQuestion() {
        System.out.println("How much is " + number1 + " times " + number2 + "?");
    }

    private static int readInt(Scanner scanner, String message) {
        while (true) {
            System.out.print(message);
            String input = scanner.nextLine();

            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("请输入整数。");
            }
        }
    }
}

import java.security.SecureRandom;
import java.util.Scanner;

public class CAIResponsePractice {
    private static final SecureRandom RANDOM = new SecureRandom();
    private static int number1;
    private static int number2;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("CAI Practice With Random Responses");
        System.out.println("Input -1 to stop.");

        createQuestion();

        while (true) {
            int answer = readInt(scanner, "Input answer: ");

            if (answer == -1) {
                System.out.println("Practice over.");
                break;
            }

            if (answer == number1 * number2) {
                printGoodMessage();
                createQuestion();
            } else {
                printBadMessage();
                printQuestion();
            }
        }
    }

    private static void createQuestion() {
        number1 = RANDOM.nextInt(9) + 1;
        number2 = RANDOM.nextInt(9) + 1;
        printQuestion();
    }

    private static void printQuestion() {
        System.out.println("How much is " + number1 + " times " + number2 + "?");
    }

    private static void printGoodMessage() {
        int choice = RANDOM.nextInt(4) + 1;

        switch (choice) {
            case 1:
                System.out.println("Very good!");
                break;
            case 2:
                System.out.println("Excellent!");
                break;
            case 3:
                System.out.println("Nice work!");
                break;
            case 4:
                System.out.println("Keep up the good work!");
                break;
            default:
                System.out.println("Very good!");
        }
    }

    private static void printBadMessage() {
        int choice = RANDOM.nextInt(4) + 1;

        switch (choice) {
            case 1:
                System.out.println("No. Please try again.");
                break;
            case 2:
                System.out.println("Wrong. Try once more.");
                break;
            case 3:
                System.out.println("Don't give up!");
                break;
            case 4:
                System.out.println("No. Keep trying.");
                break;
            default:
                System.out.println("No. Please try again.");
        }
    }

    private static int readInt(Scanner scanner, String message) {
        while (true) {
            System.out.print(message);
            String input = scanner.nextLine();

            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Please input an integer.");
            }
        }
    }
}

import java.util.Scanner;
public class ScannerIOExample {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);

        System.out.print("请输入你的姓名：");
        String name = scanner.nextLine();

        System.out.print("请输入你的年龄：");
        int age = scanner.nextInt();

        System.out.println("====== 输出结果 ======");
        System.out.println("姓名："+name);
        System.out.println("年龄："+age);

        scanner.close();
    }
}
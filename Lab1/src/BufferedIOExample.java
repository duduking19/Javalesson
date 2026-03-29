import java.io.*;
public class BufferedIOExample {
    public static void main (String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        writer.write("请输入你的姓名：");
        writer.flush();
        String name = reader.readLine();

        writer.write("请输入你的年龄：");
        writer.flush();
        int age = Integer.parseInt(reader.readLine());

        writer.write("====== 输出结果 ======\n");
        writer.write("姓名："+name+"\n");
        writer.write("年龄："+age+"\n");

        reader.close();
        writer.close();

    }
}

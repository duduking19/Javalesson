import java.util.Random;
import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("简单选课系统");
        System.out.println();

        Course[] courses = createCourses();
        Student student = inputStudent(scanner);
        Course[] selectedCourses = chooseCourses(scanner, courses);
        Score[] scores = createScores(student, selectedCourses);

        System.out.println();
        System.out.println("学生信息");
        printStudent(student);

        System.out.println();
        System.out.println("选课结果");
        printSelectedCourses(selectedCourses);

        System.out.println();
        System.out.println("课程成绩");
        printScores(selectedCourses, scores);
    }

    private static Course[] createCourses() {
        Course[] courses = new Course[5];
        courses[0] = new Course("C001", "大学英语");
        courses[1] = new Course("C002", "Java程序设计");
        courses[2] = new Course("C003", "操作系统");
        courses[3] = new Course("C004", "数据库");
        courses[4] = new Course("C005", "C语言");
        return courses;
    }

    private static Student inputStudent(Scanner scanner) {
        System.out.print("请输入学号：");
        String studentId = scanner.nextLine();

        System.out.print("请输入姓名：");
        String name = scanner.nextLine();

        System.out.print("请输入班级：");
        String className = scanner.nextLine();

        System.out.print("请输入电话（可以为空）：");
        String phone = scanner.nextLine();

        if (phone.trim().equals("")) {
            return new Student(studentId, name, className);
        }

        return new Student(studentId, name, className, phone);
    }

    private static Course[] chooseCourses(Scanner scanner, Course[] courses) {
        boolean[] selected = new boolean[courses.length];
        int count = 0;

        System.out.println();
        System.out.println("课程列表");
        printAllCourses(courses);
        System.out.println("至少选择4门课程，输入0结束选课。");

        while (true) {
            int choice = readInt(scanner, "请输入课程序号：");

            if (choice == 0) {
                if (count >= 4) {
                    break;
                }

                System.out.println("至少要选择4门课程。");
                continue;
            }

            if (choice < 1 || choice > courses.length) {
                System.out.println("输入的课程序号不正确。");
                continue;
            }

            if (selected[choice - 1]) {
                System.out.println("这门课已经选过了。");
                continue;
            }

            selected[choice - 1] = true;
            count++;
            System.out.println("已选择：" + courses[choice - 1].getCourseName());
        }

        Course[] result = new Course[count];
        int index = 0;

        for (int i = 0; i < courses.length; i++) {
            if (selected[i]) {
                result[index] = courses[i];
                index++;
            }
        }

        return result;
    }

    private static Score[] createScores(Student student, Course[] selectedCourses) {
        Random random = new Random();
        Score[] scores = new Score[selectedCourses.length];

        for (int i = 0; i < selectedCourses.length; i++) {
            int scoreValue = random.nextInt(21) + 80;
            scores[i] = new Score(selectedCourses[i].getCourseId(), student.getStudentId(), scoreValue);
        }

        return scores;
    }

    private static void printAllCourses(Course[] courses) {
        for (int i = 0; i < courses.length; i++) {
            System.out.println((i + 1) + ". " + courses[i].getCourseName() + " (" + courses[i].getCourseId() + ")");
        }
    }

    private static void printStudent(Student student) {
        System.out.println("学号：" + student.getStudentId());
        System.out.println("姓名：" + student.getName());
        System.out.println("班级：" + student.getClassName());

        if (student.getPhone().equals("")) {
            System.out.println("电话：未填写");
        } else {
            System.out.println("电话：" + student.getPhone());
        }
    }

    private static void printSelectedCourses(Course[] selectedCourses) {
        for (int i = 0; i < selectedCourses.length; i++) {
            System.out.println((i + 1) + ". " + selectedCourses[i].getCourseName() + " (" + selectedCourses[i].getCourseId() + ")");
        }
    }

    private static void printScores(Course[] selectedCourses, Score[] scores) {
        for (int i = 0; i < selectedCourses.length; i++) {
            System.out.println(selectedCourses[i].getCourseName() + "：" + scores[i].getScore() + " 分");
        }
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

class Student {
    private String studentId;
    private String name;
    private String className;
    private String phone;

    public Student(String studentId, String name, String className) {
        this.studentId = studentId;
        this.name = name;
        this.className = className;
        this.phone = "";
    }

    public Student(String studentId, String name, String className, String phone) {
        this.studentId = studentId;
        this.name = name;
        this.className = className;
        this.phone = phone;
    }

    public String getStudentId() {
        return studentId;
    }

    public String getName() {
        return name;
    }

    public String getClassName() {
        return className;
    }

    public String getPhone() {
        return phone;
    }
}

class Course {
    private String courseId;
    private String courseName;

    public Course(String courseId, String courseName) {
        this.courseId = courseId;
        this.courseName = courseName;
    }

    public String getCourseId() {
        return courseId;
    }

    public String getCourseName() {
        return courseName;
    }
}

class Score {
    private String courseId;
    private String studentId;
    private int score;

    public Score(String courseId, String studentId, int score) {
        this.courseId = courseId;
        this.studentId = studentId;
        this.score = score;
    }

    public String getCourseId() {
        return courseId;
    }

    public String getStudentId() {
        return studentId;
    }

    public int getScore() {
        return score;
    }
}

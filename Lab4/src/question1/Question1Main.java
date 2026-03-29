import java.util.ArrayList;
import java.util.List;

/**
 * 第1题主类。
 * 该程序演示继承在学生分类和课程分类中的使用方式，
 * 并完成自动选必修课、手动分配选修课以及最终结果输出。
 */
public class Question1Main {
    public static void main(String[] args) {
        // 先创建学生和课程数据，再依次完成自动选课、手动选课和结果输出。
        Student[] students = createStudents();
        Course[] requiredCourses = createRequiredCourses();
        Course[] electiveCourses = createElectiveCourses();

        assignRequiredCourses(students, requiredCourses);
        assignElectiveCourses(students, electiveCourses);
        printSelectionResult(students);
    }

    /**
     * 创建题目要求的4名学生。
     * 其中包含2名本科生和2名研究生。
     */
    private static Student[] createStudents() {
        return new Student[]{
                new Undergraduate("20241001", "张晨", "软件工程1班"),
                new Undergraduate("20241002", "李婷", "软件工程1班"),
                new Graduate("20242001", "王凯", "计算机研1班", "刘教授"),
                new Graduate("20242002", "赵敏", "计算机研1班", "陈教授")
        };
    }

    /**
     * 创建题目要求的2门必修课。
     */
    private static Course[] createRequiredCourses() {
        return new Course[]{
                new RequiredCourse("R001", "Java程序设计", 4),
                new RequiredCourse("R002", "数据结构", 3)
        };
    }

    /**
     * 创建题目要求的2门选修课。
     */
    private static Course[] createElectiveCourses() {
        return new Course[]{
                new ElectiveCourse("E001", "人工智能导论", 2),
                new ElectiveCourse("E002", "Web开发基础", 2)
        };
    }

    /**
     * 自动选课部分：
     * 为每位学生统一加入全部必修课。
     */
    private static void assignRequiredCourses(Student[] students, Course[] requiredCourses) {
        for (Student student : students) {
            for (Course course : requiredCourses) {
                student.addCourse(course);
            }
        }
    }

    /**
     * 手动选课部分：
     * 使用预设方案为每位学生分配1到2门选修课。
     */
    private static void assignElectiveCourses(Student[] students, Course[] electiveCourses) {
        students[0].addCourse(electiveCourses[0]);
        students[1].addCourse(electiveCourses[1]);
        students[2].addCourse(electiveCourses[0]);
        students[2].addCourse(electiveCourses[1]);
        students[3].addCourse(electiveCourses[1]);
    }

    /**
     * 输出每位学生的基本信息和选课结果。
     */
    private static void printSelectionResult(Student[] students) {
        System.out.println("========== 第1题：学生选课信息 ==========");
        for (Student student : students) {
            System.out.println(student.getDescription());
            System.out.println("选课结果：");
            for (Course course : student.getSelectedCourses()) {
                System.out.println("  - " + course);
            }
            System.out.println();
        }
    }
}

/**
 * 学生抽象父类。
 * 父类中保存所有学生共有的信息：学号、姓名、班级和选课列表。
 */
abstract class Student {
    private final String studentId;
    private final String name;
    private final String className;
    private final List<Course> selectedCourses = new ArrayList<>();

    protected Student(String studentId, String name, String className) {
        this.studentId = studentId;
        this.name = name;
        this.className = className;
    }

    /**
     * 为学生增加一门课程。
     * 在加入之前先检查是否已经选择过，避免重复选课。
     */
    public void addCourse(Course course) {
        if (!hasCourse(course.getCourseId())) {
            selectedCourses.add(course);
        }
    }

    /**
     * 返回学生当前的已选课程列表。
     */
    public List<Course> getSelectedCourses() {
        return selectedCourses;
    }

    /**
     * 统一拼接学生信息。
     * 不同子类通过重写方法补充学生类型和额外字段。
     */
    public String getDescription() {
        return String.format("%s 学号：%s，姓名：%s，班级：%s%s",
                getStudentType(),
                studentId,
                name,
                className,
                getExtraInfo());
    }

    /**
     * 返回学生类型，例如“本科生”或“研究生”。
     */
    protected abstract String getStudentType();

    /**
     * 返回附加信息。
     * 父类默认没有附加信息，研究生子类会在这里返回导师信息。
     */
    protected String getExtraInfo() {
        return "";
    }

    /**
     * 判断某门课程是否已经在选课列表中。
     */
    private boolean hasCourse(String courseId) {
        for (Course course : selectedCourses) {
            if (course.getCourseId().equals(courseId)) {
                return true;
            }
        }
        return false;
    }
}

/**
 * 本科生类。
 * 继承学生父类，不增加新字段，只需要返回自己的学生类型。
 */
class Undergraduate extends Student {
    public Undergraduate(String studentId, String name, String className) {
        super(studentId, name, className);
    }

    @Override
    protected String getStudentType() {
        return "本科生";
    }
}

/**
 * 研究生类。
 * 在学生公共信息之外增加导师字段。
 */
class Graduate extends Student {
    private final String advisor;

    public Graduate(String studentId, String name, String className, String advisor) {
        super(studentId, name, className);
        this.advisor = advisor;
    }

    @Override
    protected String getStudentType() {
        return "研究生";
    }

    @Override
    protected String getExtraInfo() {
        return "，导师：" + advisor;
    }
}

/**
 * 课程抽象父类。
 * 父类中保存所有课程共有的信息：编号、课程名和学分。
 */
abstract class Course {
    private final String courseId;
    private final String courseName;
    private final int credit;

    protected Course(String courseId, String courseName, int credit) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.credit = credit;
    }

    public String getCourseId() {
        return courseId;
    }

    /**
     * 返回课程类型，由子类区分“必修课”和“选修课”。
     */
    protected abstract String getCourseType();

    @Override
    public String toString() {
        return String.format("%s[%s] %s（%d学分）", getCourseType(), courseId, courseName, credit);
    }
}

/**
 * 必修课程类。
 */
class RequiredCourse extends Course {
    public RequiredCourse(String courseId, String courseName, int credit) {
        super(courseId, courseName, credit);
    }

    @Override
    protected String getCourseType() {
        return "必修课";
    }
}

/**
 * 选修课程类。
 */
class ElectiveCourse extends Course {
    public ElectiveCourse(String courseId, String courseName, int credit) {
        super(courseId, courseName, credit);
    }

    @Override
    protected String getCourseType() {
        return "选修课";
    }
}

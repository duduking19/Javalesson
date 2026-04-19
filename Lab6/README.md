# Lab6 实验说明

本目录为《Java 程序设计》实验六代码，实现内容对应 `lab6.doc` 中的 5 个题目。各题代码相互独立，分别使用独立包名和独立主类，可单独编译、单独运行。

## 实验内容

1. 题目 1：构造方法失败
   在构造方法中抛出异常，并在主程序中捕获该异常。
2. 题目 2：重抛异常
   演示异常在被捕获后再次抛出的过程，并输出栈踪迹。
3. 题目 3：自定义异常
   自定义非法姓名异常和非法地址异常，在 `Student` 类中进行数据校验。
4. 题目 4：泛型方法 `isEqualTo`
   使用泛型方法比较不同类型对象，并观察 `equals` 的比较行为。
5. 题目 5：混用组合和继承
   使用 `Employee + CompensationModel` 的组合方式重构薪酬体系，并支持动态切换薪酬模型。

## 目录结构

```text
Lab6/
├─ src/
│  ├─ question1/Question1Main.java
│  ├─ question2/Question2Main.java
│  ├─ question3/Question3Main.java
│  ├─ question4/Question4Main.java
│  └─ question5/Question5Main.java
└─ out/
   ├─ question1/
   ├─ question2/
   ├─ question3/
   ├─ question4/
   └─ question5/
```

## 代码与题目对应关系

| 题目 | 源码文件 | 说明 |
| --- | --- | --- |
| 题目 1 | `src/question1/Question1Main.java` | 构造方法抛出自定义异常并由主程序捕获 |
| 题目 2 | `src/question2/Question2Main.java` | 捕获异常后重抛，并在 `main` 中输出栈踪迹 |
| 题目 3 | `src/question3/Question3Main.java` | 自定义姓名异常、地址异常及其处理 |
| 题目 4 | `src/question4/Question4Main.java` | 泛型方法 `isEqualTo` 的实现与测试 |
| 题目 5 | `src/question5/Question5Main.java` | `Employee` 与 `CompensationModel` 的组合建模 |

## 运行环境

- JDK 8 或更高版本
- Windows PowerShell 或其他支持 `javac` / `java` 的终端
- 编译时建议使用 UTF-8 编码

## 编译与运行

以下命令在 `Lab6` 目录下执行。

### 题目 1

```powershell
javac -encoding UTF-8 -d out/question1 src/question1/Question1Main.java
java -cp out/question1 question1.Question1Main
```

### 题目 2

```powershell
javac -encoding UTF-8 -d out/question2 src/question2/Question2Main.java
java -cp out/question2 question2.Question2Main
```

### 题目 3

```powershell
javac -encoding UTF-8 -d out/question3 src/question3/Question3Main.java
java -cp out/question3 question3.Question3Main
```

### 题目 4

```powershell
javac -encoding UTF-8 -d out/question4 src/question4/Question4Main.java
java -cp out/question4 question4.Question4Main
```

### 题目 5

```powershell
javac -encoding UTF-8 -d out/question5 src/question5/Question5Main.java
java -cp out/question5 question5.Question5Main
```

## 实验报告

本次实验报告已按模板整理完成，文件位置如下：

- `D:\JavaLesson\output\doc\22920242203257_经伟昊_实验六.docx`

## 说明

- 每题均可独立编译运行，互不依赖。
- 第 5 题虽然包含多个类和接口，但都封装在同一个源码文件中，仅服务于 `question5` 题目，不会影响其他题目。
- `out/` 目录为已生成的编译产物目录，可直接用于运行对应题目。

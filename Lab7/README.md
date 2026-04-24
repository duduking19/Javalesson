# Lab7 实验说明

本目录为《Java 程序设计》实验七代码，实现内容对应 `lab7(2026) .doc` 中的 7 个题目。各题代码相互独立，分别使用独立包名和独立主类，可单独编译、单独运行。

## 实验内容

1. 题目 1：泛型方法 `isEqualTo`
   使用泛型方法比较 `Integer`、`String`、`Object` 等不同类型，并观察 `equals` 的比较语义。
2. 题目 2：泛型类 `Pair`
   编写带 `get` / `set` 方法的泛型类，并演示不同类型参数组合。
3. 题目 3：`CarbonFootprint` 接口与多态
   为 `Building`、`Car`、`Bicycle` 建模，放入 `ArrayList<CarbonFootprint>` 中统一计算碳足迹。
4. 题目 4：`Set` 去重姓名
   读取用户输入的名字序列，使用 `Set` 删除重复名字。
5. 题目 5：句子中的重复单词
   忽略大小写和标点符号，统计句子中重复单词的数量及频次。
6. 题目 6：字母计数
   读取一段文本，统计每个字母出现的次数。
7. 题目 7：质数判断与质因子集合
   读取用户输入的整数，判断是否为质数；若不是，则使用 `Set` 输出全部不同的质因子。

## 目录结构

```text
Lab7/
├─ src/
│  ├─ question1/Question1Main.java
│  ├─ question2/Question2Main.java
│  ├─ question3/Question3Main.java
│  ├─ question4/Question4Main.java
│  ├─ question5/Question5Main.java
│  ├─ question6/Question6Main.java
│  └─ question7/Question7Main.java
└─ out/
   ├─ question1/
   ├─ question2/
   ├─ question3/
   ├─ question4/
   ├─ question5/
   ├─ question6/
   └─ question7/
```

## 编译与运行

以下命令在 `Lab7` 目录下执行。

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

### 题目 6

```powershell
javac -encoding UTF-8 -d out/question6 src/question6/Question6Main.java
java -cp out/question6 question6.Question6Main
```

### 题目 7

```powershell
javac -encoding UTF-8 -d out/question7 src/question7/Question7Main.java
java -cp out/question7 question7.Question7Main
```

## 说明

- 每题均可独立编译运行，互不依赖。
- 题目 4 到题目 7 需要从控制台读取用户输入。
- 题目 3 中的碳足迹计算采用课堂实验可接受的近似排放因子，用于演示接口与多态，不作为专业碳核算结果。

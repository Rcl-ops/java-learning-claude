# 02 · 第一个程序与代码结构

**学习目标**：读懂 `Hello World` 每个单词；会注释、输入输出、遵守命名规范。

## 1. 逐行解释

```java
package demo;                         // ① 包声明（可省略；省略时属于"默认包"）

import java.util.Scanner;             // ② 导入其他包的类（java.lang 下的类无需导入）

public class HelloWorld {             // ③ 类：Java 代码的基本组织单位；public 类名必须与文件名一致
    public static void main(String[] args) {   // ④ 程序入口
        System.out.println("Hello, World!");   // ⑤ 语句以分号结尾
    }
}
```

- **类**：所有代码都必须在类里（Java 25 的"简化源文件"是例外，见第 20 章）。
- **main 方法**：JVM 找到它开始执行。`public static void main(String[] args)` 是固定写法：
  `public` 任何地方可调用；`static` 不需要先创建对象；`void` 无返回值；`args` 接收命令行参数。
- **System.out.println**：向控制台输出一行并换行；`print` 不换行；`printf` 格式化输出。

## 2. 注释

```java
// 单行注释

/* 多行注释
   可以写很多行 */

/**
 * 文档注释，可被 javadoc 生成 API 文档。
 * @param args 命令行参数
 */
```

好的注释解释**为什么**这样做，而不是复述代码"做了什么"。

## 3. 输出

```java
System.out.println("换行输出");
System.out.print("不换行");
System.out.println();                         // 只换行

int age = 18;
double price = 9.9;
System.out.println("年龄：" + age);            // 字符串拼接
System.out.printf("价格：%.2f 元，年龄：%d 岁%n", price, age);  // 格式化
```

常用格式符：`%d` 整数、`%f` 浮点（`%.2f` 保留 2 位）、`%s` 字符串、`%c` 字符、`%b` 布尔、`%n` 换行、`%5d` 宽度 5、`%-5d` 左对齐、`%05d` 补零、`%,d` 千分位。

## 4. 输入：Scanner

```java
import java.util.Scanner;

public class InputDemo {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("请输入姓名：");
        String name = sc.nextLine();          // 读一整行
        System.out.print("请输入年龄：");
        int age = sc.nextInt();               // 读一个整数
        System.out.println(name + " 明年 " + (age + 1) + " 岁");
        sc.close();
    }
}
```

> ⚠ 经典坑：`nextInt()` 之后紧跟 `nextLine()` 会读到**残留的换行符**，得到空串。解决：多调用一次 `sc.nextLine()` 吃掉换行，或统一用 `nextLine()` 再 `Integer.parseInt()` 转换。

## 5. 命令行参数

```java
public class Args {
    public static void main(String[] args) {
        System.out.println("参数个数：" + args.length);
        for (String a : args) System.out.println(a);
    }
}
// java Args.java hello 123
```

## 6. 命名规范（必须遵守，团队与官方都这样）

| 对象 | 规范 | 例子 |
|------|------|------|
| 类 / 接口 / 枚举 / record | 大驼峰 | `StudentService` |
| 方法 / 变量 | 小驼峰 | `getName`、`totalPrice` |
| 常量 | 全大写下划线 | `MAX_SIZE` |
| 包 | 全小写 | `com.example.hellomod` |

标识符规则：可以包含字母、数字、`_`、`$`，不能数字开头，不能是关键字（`class`、`int`、`public` ...）。Java **区分大小写**。

## 7. 常见错误

| 报错信息 | 原因 |
|----------|------|
| `class X is public, should be declared in a file named X.java` | 文件名与 public 类名不一致 |
| `';' expected` | 漏了分号 |
| `cannot find symbol` | 拼写错误 / 变量没声明 / 没 import |
| `Main method not found` | main 签名写错（大小写、少 static） |

## 小结

- 程序从 `main` 开始；一条语句一个分号；大小写敏感。
- 输出用 `System.out`，输入用 `Scanner`。
- 命名规范：类大驼峰，方法变量小驼峰，常量全大写。

**练习**：`exercises/basics`

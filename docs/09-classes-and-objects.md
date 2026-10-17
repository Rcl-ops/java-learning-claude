# 09 · 类与对象

**学习目标**：理解面向对象的核心思想；会定义类、字段、方法、构造器；理解 `this`、`static`、访问修饰符与初始化顺序。

## 1. 为什么需要面向对象

用**类（Class）**描述一类事物的"数据（字段）+ 行为（方法）"，用**对象（Object）**表示具体个体。
类 = 图纸/模具；对象 = 按图纸造出的实物。

```java
public class Student {
    // 字段（成员变量）：描述状态
    String name;
    int age;

    // 方法：描述行为
    void introduce() {
        System.out.println("我是" + name + "，今年" + age + "岁");
    }
}

public class Main {
    public static void main(String[] args) {
        Student s = new Student();   // 创建对象：new 在堆上分配内存，s 保存引用
        s.name = "小明";
        s.age = 18;
        s.introduce();

        Student t = new Student();   // 另一个独立的对象
        t.name = "小红";
        System.out.println(s.name + " / " + t.name);
    }
}
```

字段默认值：数值 `0`、`boolean` `false`、引用 `null`（与局部变量不同，字段**会**自动初始化）。

## 2. 构造器

构造器在 `new` 时执行，用于初始化对象；名字与类名相同，**没有返回类型**。

```java
public class Student {
    private String name;
    private int age;

    public Student() { this("无名", 0); }          // this(...) 调用本类其他构造器，必须是第一条语句

    public Student(String name, int age) {
        this.name = name;                          // this.name 是字段；name 是参数
        this.age = age;
    }
}
```

- 如果一个构造器都没写，编译器提供无参的默认构造器；**一旦写了任何构造器，默认的就没了**。
- 构造器可以重载。

## 3. this

- `this.字段`：区分同名的字段与参数。
- `this.方法()`：调用本对象方法（通常可省略）。
- `this(...)`：调用另一个构造器。
- `return this;`：实现链式调用（构建者风格）：

```java
public Student setName(String name) { this.name = name; return this; }
new Student().setName("A").setAge(3);
```

## 4. 访问修饰符与封装

| 修饰符 | 本类 | 同包 | 子类 | 其他包 |
|--------|:----:|:----:|:----:|:------:|
| `private` | ✔ | | | |
| （默认，不写） | ✔ | ✔ | | |
| `protected` | ✔ | ✔ | ✔ | |
| `public` | ✔ | ✔ | ✔ | ✔ |

**封装原则**：字段设为 `private`，通过 `public` 的 getter/setter（或有语义的方法）访问，并在其中做校验。

```java
public class Account {
    private double balance;                       // 外部不能直接改

    public double getBalance() { return balance; }

    public void deposit(double amount) {
        if (amount <= 0) throw new IllegalArgumentException("金额必须为正");
        balance += amount;
    }
}
```

好处：保护数据一致性、隐藏实现细节（以后可以改内部实现而不影响调用者）。

## 5. static：属于类，而不是对象

```java
public class Counter {
    private static int count = 0;      // 静态字段：所有对象共享一份
    private final int id;

    public Counter() { id = ++count; }

    public static int getCount() { return count; }   // 静态方法：用 类名.方法() 调用
}
Counter.getCount();
```

- 静态方法里**不能**使用 `this` 或直接访问实例成员。
- 常量：`public static final double PI = 3.14159;`
- 工具类（`Math`、`Arrays`）通常全是静态方法。
- 静态代码块：类加载时执行一次 `static { ... }`。

## 6. 初始化顺序

创建对象时的顺序（先父后子，先静态后实例）：

1. 类加载（只一次）：父类静态字段/静态块 → 子类静态字段/静态块
2. 每次 `new`：父类实例字段/实例块 → 父类构造器 → 子类实例字段/实例块 → 子类构造器

```java
class A {
    static { System.out.println("A 静态块"); }
    { System.out.println("A 实例块"); }
    A() { System.out.println("A 构造器"); }
}
class B extends A {
    static { System.out.println("B 静态块"); }
    { System.out.println("B 实例块"); }
    B() { System.out.println("B 构造器"); }
}
new B();   // A 静态块 → B 静态块 → A 实例块 → A 构造器 → B 实例块 → B 构造器
```

## 7. 对象在内存里（心智模型）

```
栈（stack）                        堆（heap）
┌──────────┐                     ┌──────────────────────┐
│ s ──────────────────────────▶ │ Student{name,age}    │
│ t ─────────────┐               ├──────────────────────┤
└──────────┘     └────────────▶ │ Student{name,age}    │
                                  └──────────────────────┘
```

- 局部变量/方法调用帧在**栈**上；对象在**堆**上。
- 没有任何引用指向的对象会被**垃圾回收器（GC）**自动回收，Java 没有手动 `free`。
- `Student a = b;` 复制的是引用，两个变量指向同一个对象。

## 8. 常用的 Object 方法：toString

```java
@Override
public String toString() {
    return "Student{name=" + name + ", age=" + age + "}";
}
System.out.println(s);   // 自动调用 toString，否则打印 Student@1b6d3586
```

## 9. 包（package）

```java
package com.example.school;     // 文件所在目录应为 com/example/school/
import com.example.school.Student;
import java.util.*;             // 导入整个包（不含子包）
import static java.lang.Math.*; // 静态导入
```

包名反向域名、全小写，用来避免重名和组织代码。

## 10. 常见错误

| 报错 | 原因 |
|------|------|
| `NullPointerException` | 引用为 `null` 时访问字段/方法。养成检查习惯，用 `Objects.requireNonNull` |
| `constructor X in class X cannot be applied to given types` | 调用了不存在的构造器（写了有参构造后默认无参消失） |
| `non-static variable cannot be referenced from a static context` | 在 `static` 方法里用了实例成员 |
| 字段忘了 `private` | 暴露内部状态，破坏封装 |

## 小结

- 类是模板，对象是实例；构造器初始化对象；`this` 指当前对象。
- 封装：私有字段 + 公开方法 + 校验。
- `static` 属于类；初始化顺序"先静后实、先父后子"。

**练习**：`exercises/oop`

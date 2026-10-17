# 11 · 抽象类、接口、枚举、record 与内部类

**学习目标**：区分抽象类与接口；会定义枚举和 record；了解内部类与密封类。

## 1. 抽象类

包含**抽象方法**（只有声明、没有实现）的类必须声明为 `abstract`，不能被实例化。

```java
public abstract class Shape {
    private final String name;
    protected Shape(String name) { this.name = name; }

    public abstract double area();          // 子类必须实现

    public String describe() {              // 可以有普通方法（模板方法模式常用）
        return name + " 面积 = " + String.format("%.2f", area());
    }
}

public class Circle extends Shape {
    private final double r;
    public Circle(double r) { super("圆"); this.r = r; }
    @Override public double area() { return Math.PI * r * r; }
}
// new Shape("x");   ❌ 抽象类不能 new
```

## 2. 接口（interface）

接口定义"**能做什么**"（契约），一个类可以实现多个接口。

```java
public interface Flyable {
    double MAX_HEIGHT = 10000;              // 字段默认 public static final

    void fly();                             // 抽象方法（默认 public abstract）

    default void land() {                   // 默认方法（Java 8）：给实现类提供默认实现
        System.out.println("降落");
    }

    static Flyable none() {                 // 静态方法
        return () -> { };
    }
}

public interface Swimmable { void swim(); }

public class Duck implements Flyable, Swimmable {     // 多实现
    @Override public void fly()  { System.out.println("鸭子飞"); }
    @Override public void swim() { System.out.println("鸭子游"); }
}
```

接口之间可以多继承：`interface A extends B, C`。

### 抽象类 vs 接口

| | 抽象类 | 接口 |
|---|---|---|
| 关系 | is-a（是一种） | can-do（具备能力） |
| 继承/实现个数 | 单继承 | 多实现 |
| 字段 | 任意实例字段 | 只有常量 |
| 构造器 | 有 | 无 |
| 适用 | 多个子类共享状态与代码 | 定义行为规范、解耦 |

**经验**：优先设计接口（面向接口编程）；需要共享字段/实现时再引入抽象类。

### 函数式接口

只有**一个抽象方法**的接口，可以用 Lambda 实现（第 15 章）：

```java
@FunctionalInterface
interface Calculator { int apply(int a, int b); }
Calculator add = (a, b) -> a + b;
```

## 3. 枚举（enum）

表示**一组固定的常量**，类型安全，比 `int`/`String` 常量好得多。

```java
public enum Weekday { MON, TUE, WED, THU, FRI, SAT, SUN }

Weekday d = Weekday.MON;
d.name();            // "MON"
d.ordinal();         // 0
Weekday.valueOf("TUE");       // 字符串 → 枚举；不存在抛 IllegalArgumentException
Weekday.values();             // 所有常量数组
```

枚举可以有字段、构造器、方法，甚至抽象方法：

```java
public enum Planet {
    MERCURY(3.303e+23, 2.4397e6),
    EARTH(5.976e+24, 6.37814e6);

    private final double mass, radius;
    Planet(double mass, double radius) { this.mass = mass; this.radius = radius; }   // 构造器隐式 private
    public double surfaceGravity() { return 6.67300E-11 * mass / (radius * radius); }
}

enum Op {
    ADD("+") { int apply(int a, int b) { return a + b; } },
    MUL("*") { int apply(int a, int b) { return a * b; } };
    final String symbol;
    Op(String s) { symbol = s; }
    abstract int apply(int a, int b);
}
```

配合 `switch`、`EnumMap`、`EnumSet` 使用非常方便：

```java
String t = switch (d) {
    case SAT, SUN -> "周末";
    default -> "工作日";           // 枚举的 switch 表达式覆盖所有常量时可不写 default
};
Map<Weekday, Integer> m = new EnumMap<>(Weekday.class);
```

## 4. record（Java 16+）

用最少的代码声明**不可变数据载体**：

```java
public record Point(int x, int y) {
    public Point {                        // 紧凑构造器：参数校验
        if (x < 0 || y < 0) throw new IllegalArgumentException("坐标不能为负");
    }
    public double distanceToOrigin() { return Math.sqrt(x * x + y * y); }
    public static Point origin() { return new Point(0, 0); }
}

Point p = new Point(3, 4);
p.x();                       // 访问器是 x()，不是 getX()
System.out.println(p);       // Point[x=3, y=4]
p.equals(new Point(3, 4));   // true：自动生成 equals/hashCode/toString
```

特点：字段隐式 `private final`；不能继承其他类（但可以实现接口）；适合 DTO、返回多个值、Map 的 key。

record 解构（Java 21，配合模式匹配）：

```java
Object o = new Point(1, 2);
if (o instanceof Point(int x, int y)) System.out.println(x + y);
```

## 5. 内部类

```java
public class Outer {
    private int v = 1;

    class Inner {                       // ① 成员内部类：持有外部对象引用
        int get() { return v; }
    }
    static class Nested { }             // ② 静态嵌套类：不持有外部引用（更常用，如 Builder）

    void m() {
        class Local { }                 // ③ 局部类
        Runnable r = new Runnable() {   // ④ 匿名类：一次性实现
            @Override public void run() { System.out.println("run"); }
        };
        Runnable r2 = () -> System.out.println("run");   // 现在更推荐 Lambda
    }
}
Outer o = new Outer();
Outer.Inner in = o.new Inner();
Outer.Nested n = new Outer.Nested();
```

内部类可以访问外部类的私有成员；**非 static 内部类可能造成内存泄漏**（隐式持有外部对象）。

## 6. 密封类与接口（Java 17+）

限制"谁可以继承我"，配合 `switch` 实现编译期穷尽检查：

```java
public sealed interface Shape permits Circle, Rect { }
public record Circle(double r) implements Shape { }
public record Rect(double w, double h) implements Shape { }

static double area(Shape s) {
    return switch (s) {                   // 无需 default：编译器知道只有两种
        case Circle c -> Math.PI * c.r() * c.r();
        case Rect r -> r.w() * r.h();
    };
}
```

## 小结

- 抽象类共享代码与状态；接口定义能力，可多实现；优先面向接口编程。
- 枚举表示固定集合；record 是不可变数据类；sealed 约束继承体系。
- 匿名类/内部类的需求大多可由 Lambda 与静态嵌套类替代。

**练习**：`exercises/oop`

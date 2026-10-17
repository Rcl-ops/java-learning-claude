# 20 · 现代 Java 特性速览

**学习目标**：了解 Java 8 → 25 的重要语言特性，写出更简洁、安全的现代代码。

## 1. 版本节奏

Java 每 6 个月发布一次（3 月、9 月）；每两年一个 **LTS**：8、11、17、21、25。预览特性（preview）需 `--enable-preview` 才能使用，可能变化。**特性是否转正以官方 Release Notes / JEP 为准。**

| 版本 | 代表特性 |
|------|----------|
| 8 | Lambda、Stream、Optional、`java.time`、默认方法 |
| 9 | 模块系统、`List.of`、`jshell` |
| 10 | 局部变量 `var` |
| 11 | `String.isBlank/repeat/strip`、`HttpClient`、`java 文件.java` 直接运行 |
| 14 | switch 表达式（箭头、`yield`） |
| 15 | 文本块 `"""` |
| 16 | `record`、`instanceof` 模式匹配 |
| 17 (LTS) | `sealed` 密封类 |
| 21 (LTS) | **虚拟线程**、switch 模式匹配、record 模式、有序集合 `getFirst/reversed` |
| 22+ | 未命名变量 `_`（22）、Stream Gatherers（24）…… |
| 25 (LTS) | 简化源文件与实例 main（JEP 512）、灵活构造器体（JEP 513）、模块导入（JEP 511）、作用域值（JEP 506）等 |

## 2. var（Java 10）

```java
var list = new ArrayList<String>();      // 推断为 ArrayList<String>
var map = Map.of("a", 1);
for (var e : map.entrySet()) { }
```

只用于**局部变量**；右侧类型明显时用，类型不明显（`var x = foo();`）会降低可读性。不能用于字段、参数、返回类型。

## 3. 文本块、switch 表达式、record、sealed

前面章节已介绍：见 [08 字符串](08-strings.md)、[05 流程控制](05-control-flow.md)、[11 抽象/接口/枚举/record](11-abstract-interface-enum-record.md)。

## 4. 模式匹配

```java
// instanceof 模式匹配（16）
if (obj instanceof String s && s.length() > 3) { System.out.println(s); }

// switch 模式匹配 + 守卫（21）
static String fmt(Object o) {
    return switch (o) {
        case null -> "null";
        case Integer i when i > 0 -> "正整数";
        case Integer i -> "整数";
        case String s -> "字符串:" + s;
        case int[] arr -> "int 数组，长度 " + arr.length;
        default -> o.toString();
    };
}

// record 解构模式（21）
record Point(int x, int y) { }
record Line(Point a, Point b) { }
static int dx(Object o) {
    if (o instanceof Line(Point(var x1, var y1), Point(var x2, var y2))) return x2 - x1;
    return 0;
}
```

## 5. 有序集合（Java 21）

```java
List<Integer> l = new ArrayList<>(List.of(1, 2, 3));
l.getFirst(); l.getLast(); l.reversed(); l.addFirst(0); l.removeLast();
LinkedHashMap<String,Integer> m = new LinkedHashMap<>(); m.firstEntry(); m.lastEntry();
```

## 6. 未命名变量与模式（Java 22 正式）

```java
try { Integer.parseInt(s); } catch (NumberFormatException _) { /* 不需要用异常变量 */ }
for (var _ : list) count++;
if (o instanceof Point(int x, _)) { ... }
```

## 7. Java 25 的几个新入门友好特性

> 以下特性在 Java 25 中为正式特性；如果你用 JDK 21，请跳过本节。

**简化源文件 + 实例 main 方法（JEP 512）**：初学者可以省掉 `public class`、`static`、`String[] args`：

```java
// Hello.java —— 在 JDK 25 里可以直接这样写，然后 java Hello.java
void main() {
    IO.println("Hello, Java 25!");     // java.lang.IO：简化的控制台输入输出（IO.println / IO.readln）
}
```

**灵活构造器体（JEP 513）**：允许在 `super(...)` 之前执行校验逻辑、给参数做预处理（但不能访问 `this`）：

```java
class Child extends Parent {
    Child(int age) {
        if (age < 0) throw new IllegalArgumentException("age");   // 在 super 之前校验
        super(age);
    }
}
```

**模块导入（JEP 511）**：`import module java.base;` 一次导入模块导出的所有包，适合小脚本。

> 教学建议：学习阶段仍然使用传统的 `public class + public static void main`，因为所有现有框架、项目和面试都沿用这种写法，而且它能让你理解"类"这一概念。

## 8. 虚拟线程与结构化并发

虚拟线程见第 18 章。结构化并发（`StructuredTaskScope`）将一组子任务视为一个整体处理成功/失败/取消，Java 25 中仍为预览 API，请以官方文档为准。

## 9. 迁移与学习建议

- 新项目直接用 **record、sealed、switch 表达式、文本块、Stream、`var`（适度）**。
- 老代码维护不必强行重写，先保证正确。
- 关注 [dev.java](https://dev.java) 与 OpenJDK 的 JEP 列表获取一手信息。

## 小结

现代 Java 趋势：**更少样板代码、更强类型安全（密封 + 模式匹配）、更简单的并发（虚拟线程）**。

**练习**：任选 `exercises` 中的老题，用 record / switch 表达式 / Stream 重写一遍。

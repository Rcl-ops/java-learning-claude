# 03 · 变量与数据类型

**学习目标**：理解变量与类型；掌握 8 种基本类型、类型转换、溢出与浮点精度、`==` 与 `equals` 的区别。

## 1. 变量

变量 = **有名字、有类型的存储空间**。

```java
int age = 18;              // 声明并初始化
double height;             // 声明
height = 1.75;             // 赋值
final double PI = 3.14159; // final：常量，赋值后不可再改
var count = 10;            // var：编译器推断为 int（Java 10+，仅限局部变量）
```

- Java 是**静态强类型**语言：变量类型在编译期确定，类型不匹配会编译报错。
- **局部变量必须先赋值再使用**；成员变量有默认值（见第 09 章）。

## 2. 八种基本类型

| 类型 | 大小 | 范围 / 说明 | 示例 |
|------|------|-------------|------|
| `byte` | 1 字节 | -128 ~ 127 | `byte b = 100;` |
| `short` | 2 字节 | -32768 ~ 32767 | `short s = 1000;` |
| `int` | 4 字节 | 约 ±21 亿（**默认整数类型**） | `int n = 42;` |
| `long` | 8 字节 | 约 ±9.2×10¹⁸，字面量加 `L` | `long big = 10_000_000_000L;` |
| `float` | 4 字节 | 单精度，字面量加 `f` | `float f = 3.14f;` |
| `double` | 8 字节 | 双精度（**默认小数类型**） | `double d = 3.14;` |
| `char` | 2 字节 | 一个 UTF-16 码元，单引号 | `char c = 'A';` |
| `boolean` | — | `true` / `false` | `boolean ok = true;` |

数字字面量技巧：

```java
int million = 1_000_000;      // 下划线分隔，增强可读性
int bin = 0b1010;             // 二进制 = 10
int oct = 017;                // 八进制 = 15
int hex = 0xFF;               // 十六进制 = 255
long l = 123L;
float f = 1.5f;
double sci = 1.5e3;           // 1500.0
```

## 3. 引用类型（先建立印象）

除 8 种基本类型外，其余都是**引用类型**：`String`、数组、类、接口、枚举……

```java
String name = "Alice";       // 变量里存的是"对象的引用（地址）"
String none = null;          // null 表示不指向任何对象；对 null 调方法会 NPE
```

> 基本类型变量直接存值；引用类型变量存"指向对象的引用"。这是理解后面所有内容的关键。

## 4. 类型转换

### 隐式（自动）转换：小 → 大，安全

```
byte → short → int → long → float → double
              char ↗
```

```java
int i = 100;
long l = i;          // OK
double d = i;        // OK
```

### 显式（强制）转换：大 → 小，可能丢失精度

```java
double d = 9.99;
int i = (int) d;             // 9（直接截断，不是四舍五入）
int big = 130;
byte b = (byte) big;         // -126（溢出回绕）
long l = 3_000_000_000L;
int x = (int) l;             // -1294967296，数据已损坏！
```

四舍五入请用 `Math.round(9.99)`（得到 `long` 的 `10`）。

### 运算中的提升规则

```java
byte a = 10, b = 20;
// byte c = a + b;          // ❌ 编译错误：byte+byte 先提升为 int
byte c = (byte) (a + b);    // ✅
int r = 5 / 2;              // 2    整数除法
double r2 = 5 / 2;          // 2.0  先整除再转 double —— 常见陷阱！
double r3 = 5 / 2.0;        // 2.5
```

## 5. 溢出与浮点精度（两大经典坑）

```java
int max = Integer.MAX_VALUE;          // 2147483647
System.out.println(max + 1);          // -2147483648：溢出，不报错！
System.out.println(Math.addExact(max, 1));  // 抛 ArithmeticException，更安全

System.out.println(0.1 + 0.2);        // 0.30000000000000004
System.out.println(0.1 + 0.2 == 0.3); // false
```

- 浮点数是**二进制近似值**，不能用 `==` 比较；用差值：`Math.abs(a - b) < 1e-9`。
- **金额计算用 `BigDecimal`**（见第 17 章），或用"分"为单位的 `long`。

## 6. char 与 Unicode

```java
char c = 'A';
System.out.println(c + 1);          // 66（char 参与运算变 int）
System.out.println((char) (c + 1)); // B
System.out.println((int) 'a');      // 97
char zh = '中';                      // 中文也是 char
System.out.println('\u4e2d');       // Unicode 转义 → 中
```

转义字符：`\n` 换行、`\t` 制表、`\\` 反斜杠、`\"` 双引号、`\'` 单引号。

> 注意：emoji 等"补充字符"需要两个 `char`（代理对），此时请使用 `String.codePointAt` 等码点 API。

## 7. 包装类与自动装箱

每个基本类型都有对应的**包装类**：`Integer`、`Long`、`Double`、`Character`、`Boolean`……

```java
Integer a = 100;          // 自动装箱
int b = a;                // 自动拆箱
int n = Integer.parseInt("123");
String s = String.valueOf(456);
double d = Double.parseDouble("3.14");
Integer.MAX_VALUE; Integer.toBinaryString(10); // "1010"
```

集合（`List<Integer>`）里只能放包装类，不能放基本类型。

### ⚠ 缓存陷阱

```java
Integer x = 127, y = 127;
System.out.println(x == y);        // true  （-128~127 被缓存，是同一个对象）
Integer p = 1000, q = 1000;
System.out.println(p == q);        // false （不同对象）
System.out.println(p.equals(q));   // true
```

**结论：引用类型比较内容一律用 `equals`，不要用 `==`。** `==` 对引用类型比较的是"是不是同一个对象"。

另外：包装类可能为 `null`，拆箱时会 `NullPointerException`。

## 8. 常见错误清单

| 错误 | 原因 |
|------|------|
| `incompatible types: possible lossy conversion from double to int` | 大转小需要强转 |
| `integer number too large` | 超过 int 范围的字面量忘了加 `L` |
| `variable might not have been initialized` | 局部变量未赋值就使用 |
| `variable x is already defined` | 同一作用域重复声明 |

## 小结

- 8 种基本类型 + 引用类型；整数默认 `int`，小数默认 `double`。
- 大转小要强转并可能丢精度；整数运算会溢出且不报错。
- 浮点不精确，金额用 `BigDecimal`；比较内容用 `equals`。

**练习**：`exercises/basics`

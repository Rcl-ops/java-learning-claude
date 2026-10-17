# 08 · 字符串

**学习目标**：理解 `String` 不可变；熟练使用常用方法、`StringBuilder`、文本块与格式化。

## 1. String 的特性

```java
String s1 = "hello";               // 字面量，进入字符串常量池
String s2 = "hello";
String s3 = new String("hello");   // 强制创建新对象
System.out.println(s1 == s2);      // true  （常量池复用）
System.out.println(s1 == s3);      // false
System.out.println(s1.equals(s3)); // true  ← 比较内容必须用 equals
```

- **不可变（immutable）**：任何"修改"方法都返回**新字符串**，原字符串不变。

```java
String s = "abc";
s.toUpperCase();                   // 返回 "ABC"，但没接收，s 依旧是 "abc"
s = s.toUpperCase();               // 要接收返回值
```

- 字符串内部是 `char`/字节序列；下标从 0 开始。

## 2. 常用方法

```java
String s = "  Hello, Java!  ";
s.length();                 // 16
s.charAt(2);                // 'H'
s.trim();                   // "Hello, Java!"（去首尾空白）  s.strip() 更能处理 Unicode 空白
s.isEmpty();                // false   长度是否为 0
s.isBlank();                // false   是否全为空白（Java 11+）
s.toUpperCase(); s.toLowerCase();

String t = "Hello, Java";
t.indexOf("Java");          // 7；找不到返回 -1
t.lastIndexOf('a');         // 10
t.contains("lo");           // true
t.startsWith("He"); t.endsWith("va");
t.substring(7);             // "Java"
t.substring(0, 5);          // "Hello"  [begin, end) 左闭右开
t.replace("Java", "World"); // "Hello, World"
t.equalsIgnoreCase("HELLO, JAVA");   // true
"apple".compareTo("banana");        // 负数：按字典序比较
"ab".repeat(3);                      // "ababab"（Java 11+）
```

### 分割与连接

```java
String csv = "a,b,,c";
String[] parts = csv.split(",");              // ["a","b","","c"]
String[] ws = "a  b   c".split("\\s+");       // 参数是正则！"\\s+" 匹配一个或多个空白
String joined = String.join("-", "2025", "10", "17");   // "2025-10-17"
String j2 = String.join(",", List.of("x", "y"));
```

### 转换

```java
String.valueOf(123);            // "123"
Integer.parseInt("123");        // 123（格式错误抛 NumberFormatException）
"abc".toCharArray();            // char[]
new String(new char[]{'a','b'});
"héllo".getBytes(java.nio.charset.StandardCharsets.UTF_8);   // 字节
```

## 3. 拼接与 StringBuilder

循环里用 `+` 拼接会反复创建新对象，性能差：

```java
String s = "";
for (int i = 0; i < 10000; i++) s += i;       // ❌ 慢，O(n²)

StringBuilder sb = new StringBuilder();       // ✅ 可变字符序列
for (int i = 0; i < 10000; i++) sb.append(i);
String result = sb.toString();
```

```java
StringBuilder sb = new StringBuilder("hello");
sb.append(" world").insert(0, ">> ").reverse();
sb.setCharAt(0, 'X');
sb.deleteCharAt(0);
sb.length();
```

- `StringBuilder`：非线程安全，速度快，**最常用**。
- `StringBuffer`：线程安全，较慢，基本不用。
- 简单的"一次性"拼接用 `+` 即可，编译器会优化。

## 4. 格式化

```java
String s = String.format("%s 今年 %d 岁，身高 %.2f 米", "小明", 18, 1.756);
String s2 = "%s=%d".formatted("age", 18);            // Java 15+
System.out.printf("|%8s|%-8s|%08.3f|%n", "右", "左", 3.14159);
```

## 5. 文本块（Java 15+）

多行字符串，常用于 JSON / SQL / HTML：

```java
String json = """
        {
          "name": "Alice",
          "age": 18
        }
        """;       // 以结尾 """ 的缩进为准去掉公共缩进；末尾含换行
```

## 6. 正则表达式入门

```java
"abc123".matches("[a-z]+\\d+");                       // true，整串匹配
"a1b22c333".replaceAll("\\d+", "#");                  // "a#b#c#"

import java.util.regex.*;
Pattern p = Pattern.compile("(\\w+)@(\\w+\\.com)");
Matcher m = p.matcher("联系 tom@example.com 或 amy@test.com");
while (m.find()) {
    System.out.println(m.group(1) + " 在 " + m.group(2));
}
```

常用语法：`.` 任意字符、`\d` 数字、`\w` 字母数字下划线、`\s` 空白、`+` 一次或多次、`*` 零次或多次、`?` 零次或一次、`[abc]` 字符集、`^`/`$` 开头/结尾、`(...)` 分组。Java 字符串里反斜杠要写两个。

## 7. 字符处理

```java
char c = 'a';
Character.isDigit(c); Character.isLetter(c); Character.isUpperCase(c);
Character.toUpperCase(c);
for (char ch : "hello".toCharArray()) { ... }
"hello".chars().filter(x -> x == 'l').count();   // 2（Stream 见第 15 章）
```

## 8. 常见错误

| 问题 | 解决 |
|------|------|
| 用 `==` 比较字符串内容 | 用 `equals`；与常量比较写成 `"abc".equals(s)` 可避免 NPE |
| `StringIndexOutOfBoundsException` | 检查下标与长度；`substring` 的 end 是开区间 |
| `split("|")` 得到奇怪结果 | `|` 是正则元字符，需 `split("\\|")` |
| 忘了接收返回值 | 字符串不可变，方法返回新串 |
| 循环里用 `+=` | 改用 `StringBuilder` |

## 小结

- `String` 不可变，比较用 `equals`，性能拼接用 `StringBuilder`。
- `split`、`replaceAll`、`matches` 的参数是正则。
- 多行文本用文本块；格式化用 `printf`/`formatted`。

**练习**：`exercises/strings`

# 13 · 异常处理

**学习目标**：理解异常体系；会 `try-catch-finally`、`throw`/`throws`、自定义异常和 try-with-resources；会读异常栈。

## 1. 异常体系

```
Throwable
├── Error                      严重问题，通常不应捕获：OutOfMemoryError、StackOverflowError
└── Exception
    ├── RuntimeException       "非受检"（unchecked）：编程错误，不强制处理
    │     NullPointerException、IllegalArgumentException、IndexOutOfBoundsException、
    │     ArithmeticException、ClassCastException、NumberFormatException、IllegalStateException
    └── 其他 Exception         "受检"（checked）：必须 try-catch 或 throws 声明
          IOException、SQLException、InterruptedException、自定义的 checked 异常
```

- **受检异常**：编译器强制处理，表示"可能发生、调用者有办法应对"的外部情况（文件不存在、网络断开）。
- **非受检异常**：多为 bug（空指针、下标越界），应该通过**修改代码**避免，而不是 catch 掉。

## 2. try-catch-finally

```java
try {
    int n = Integer.parseInt("abc");          // 抛 NumberFormatException
    System.out.println("不会执行");
} catch (NumberFormatException e) {
    System.out.println("格式错误：" + e.getMessage());
} catch (IllegalStateException | IllegalArgumentException e) {   // 多重捕获
    System.out.println("参数/状态错误");
} catch (Exception e) {                        // 范围大的放后面
    e.printStackTrace();
} finally {
    System.out.println("无论是否异常都会执行，常用于释放资源");
}
```

规则：
- catch 顺序：**子类在前，父类在后**，否则编译错误。
- `finally` 总会执行（除非 `System.exit`、JVM 崩溃）；**不要在 finally 里 `return`**（会吞掉异常）。

## 3. throw 与 throws

```java
public static int divide(int a, int b) {
    if (b == 0) throw new IllegalArgumentException("除数不能为 0");   // throw：主动抛出
    return a / b;
}

public static String readFirstLine(String path) throws IOException {  // throws：声明可能抛出
    return Files.readAllLines(Path.of(path)).get(0);
}
```

调用者要么 `try-catch`，要么继续 `throws`。

## 4. 自定义异常

```java
// 受检异常：调用者必须处理
public class InsufficientFundsException extends Exception {
    private final double shortfall;
    public InsufficientFundsException(String msg, double shortfall) {
        super(msg);
        this.shortfall = shortfall;
    }
    public double getShortfall() { return shortfall; }
}

// 非受检异常：继承 RuntimeException
public class ConfigException extends RuntimeException {
    public ConfigException(String msg, Throwable cause) { super(msg, cause); }
}
```

## 5. 异常链：保留根因

```java
try {
    loadConfig();
} catch (IOException e) {
    throw new ConfigException("加载配置失败", e);     // 把原异常作为 cause 传入，不要丢
}
```

## 6. try-with-resources（Java 7+）

实现了 `AutoCloseable` 的资源，会在块结束时**自动关闭**（逆序），无需手写 finally。

```java
try (BufferedReader br = new BufferedReader(new FileReader("a.txt"));
     BufferedWriter bw = new BufferedWriter(new FileWriter("b.txt"))) {
    String line;
    while ((line = br.readLine()) != null) {
        bw.write(line);
        bw.newLine();
    }
} catch (IOException e) {
    e.printStackTrace();
}
// 自动调用 bw.close() 然后 br.close()
```

自己的资源类：

```java
class Conn implements AutoCloseable {
    @Override public void close() { System.out.println("关闭连接"); }
}
```

## 7. 读懂异常栈

```
Exception in thread "main" java.lang.NullPointerException: Cannot invoke "String.length()" because "s" is null
	at demo.Main.count(Main.java:12)       ← 最先看这里：真正出错的位置（你的代码第 12 行）
	at demo.Main.main(Main.java:5)         ← 谁调用了它
Caused by: ...                              ← 根本原因（异常链）
```

方法：①读第一行（类型 + 消息）；②找到**第一个属于你自己代码**的栈帧；③看那一行有哪些变量可能为 null / 越界。

## 8. 最佳实践

1. **不要吞异常**：`catch (Exception e) { }` 是最差的写法。至少记录日志。
2. **捕获具体的异常**，不要动不动 `catch (Exception e)`。
3. **尽早抛出，延迟处理**：在能处理它的那一层捕获。
4. 参数校验用 `IllegalArgumentException` / `Objects.requireNonNull`；状态不对用 `IllegalStateException`。
5. 不要用异常做普通流程控制（性能差、可读性差）。
6. 抛异常时带上有用信息（哪个值、期望什么）。
7. 恢复不了就让它抛出去；`InterruptedException` 捕获后应恢复中断标志：`Thread.currentThread().interrupt();`

## 9. 常见异常速查

| 异常 | 常见原因 |
|------|----------|
| `NullPointerException` | 对 null 调用方法/访问字段 |
| `ArrayIndexOutOfBoundsException` / `IndexOutOfBoundsException` | 下标 < 0 或 ≥ 长度 |
| `ClassCastException` | 强转到不兼容类型 |
| `NumberFormatException` | `parseInt("abc")` |
| `ArithmeticException` | 整数除 0 |
| `ConcurrentModificationException` | 遍历集合时修改它 |
| `UnsupportedOperationException` | 修改了 `List.of(...)` 这样的不可变集合 |
| `StackOverflowError` | 递归无终止 |
| `FileNotFoundException` / `NoSuchFileException` | 路径错误 |

## 小结

- Error 不管；RuntimeException 修 bug；受检异常认真处理。
- `try-with-resources` 管理资源；自定义异常要保留 `cause`。
- 永远不要吞异常，永远读栈的第一个自己的栈帧。

**练习**：`exercises/exceptions`

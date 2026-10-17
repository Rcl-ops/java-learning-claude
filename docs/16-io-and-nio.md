# 16 · 文件与 IO

**学习目标**：会用 `Path` / `Files` 读写文本、遍历目录；理解字节流、字符流、缓冲与编码；会安全地关闭资源。

## 1. 两套 API

- **旧**：`java.io.File`、`FileInputStream`、`FileReader` ……（仍大量存在）
- **新（推荐）**：`java.nio.file.Path` + `Files`（Java 7+），代码更短、功能更全。

## 2. Path 与 Files（推荐）

```java
Path p = Path.of("data", "notes.txt");          // 跨平台拼接路径（Windows 用 \，Linux 用 /）
p.getFileName(); p.getParent(); p.toAbsolutePath(); p.resolve("sub"); p.normalize();

Files.exists(p); Files.isDirectory(p); Files.size(p);
Files.createDirectories(Path.of("data/backup"));       // 递归创建目录
```

### 读文件

```java
String all = Files.readString(p);                              // 一次读完整个文件（UTF-8），适合小文件
List<String> lines = Files.readAllLines(p);                    // 按行
try (Stream<String> s = Files.lines(p)) {                      // 大文件：惰性逐行，需要关闭
    s.filter(l -> !l.isBlank()).forEach(System.out::println);
}
byte[] bytes = Files.readAllBytes(p);
```

### 写文件

```java
Files.writeString(p, "第一行\n第二行\n");                                       // 覆盖写
Files.writeString(p, "追加\n", StandardOpenOption.APPEND, StandardOpenOption.CREATE);
Files.write(p, List.of("a", "b", "c"));                                         // 按行写
```

### 复制、移动、删除

```java
Files.copy(src, dst, StandardCopyOption.REPLACE_EXISTING);
Files.move(src, dst, StandardCopyOption.REPLACE_EXISTING);
Files.delete(p);                  // 不存在会抛异常
Files.deleteIfExists(p);
Path tmp = Files.createTempFile("demo", ".txt");
```

### 遍历目录

```java
try (Stream<Path> s = Files.list(dir)) {                 // 只列一层
    s.forEach(System.out::println);
}
try (Stream<Path> s = Files.walk(dir)) {                 // 递归全部
    s.filter(Files::isRegularFile).filter(f -> f.toString().endsWith(".java")).forEach(System.out::println);
}
```

> `Files.list/walk/lines` 返回的 Stream 持有文件句柄，**必须 try-with-resources**。

## 3. 流（Stream）模型：字节 vs 字符

```
字节流（任何数据）：InputStream / OutputStream
字符流（文本）    ：Reader / Writer          （按"字符编码"把字节解码成字符）
缓冲包装          ：BufferedReader / BufferedWriter / BufferedInputStream（减少系统调用，提升性能）
```

```java
// 按行读（带编码，推荐写法）
try (BufferedReader br = Files.newBufferedReader(p, StandardCharsets.UTF_8)) {
    String line;
    while ((line = br.readLine()) != null) System.out.println(line);
}

// 写
try (BufferedWriter bw = Files.newBufferedWriter(p, StandardCharsets.UTF_8)) {
    bw.write("你好");
    bw.newLine();
}

// 二进制拷贝
try (InputStream in = Files.newInputStream(src); OutputStream out = Files.newOutputStream(dst)) {
    in.transferTo(out);          // Java 9+
}
```

**装饰器模式**：流可以一层层包装，如 `new BufferedReader(new InputStreamReader(new FileInputStream(f), UTF_8))`。

## 4. 字符编码

- 文本文件在磁盘上是字节，读写时要指定**编码**。统一使用 **UTF-8**。
- `Files.readString/writeString` 默认 UTF-8；老 API `new FileReader(f)` 在 Java 18 前使用平台默认编码（Windows 中文版常为 GBK），容易乱码。Java 18+ 默认 UTF-8。
- 乱码的本质：**写入与读取用了不同编码**。

## 5. Scanner 读文件 / 标准输入

```java
try (Scanner sc = new Scanner(Path.of("nums.txt"))) {
    while (sc.hasNextInt()) total += sc.nextInt();
}
```

## 6. 配置文件：Properties

```java
Properties props = new Properties();
try (Reader r = Files.newBufferedReader(Path.of("app.properties"))) { props.load(r); }
String port = props.getProperty("port", "8080");
```

## 7. 对象序列化与 JSON

- Java 自带的 `Serializable` 序列化：**存在安全风险，格式脆弱，不推荐**用于新项目。
- 实际开发中使用 **JSON**（库：Jackson / Gson）。本教程项目为保持零依赖，用手写的简易文本格式（见 `projects/01-todo-cli`）。

## 8. 常见错误

| 问题 | 解决 |
|------|------|
| `NoSuchFileException` / `FileNotFoundException` | 相对路径是相对于**启动目录**（IDE 里是项目根目录），先打印 `Path.of("").toAbsolutePath()` 确认 |
| `AccessDeniedException` | 权限或文件被占用 |
| 乱码 | 指定 `StandardCharsets.UTF_8` |
| 忘记关流导致文件被占用/数据丢失 | 一律 try-with-resources |
| `readAllLines` 读 GB 级大文件内存溢出 | 用 `Files.lines` 或缓冲流逐行处理 |
| 路径硬编码 `"C:\\..."` | 用 `Path.of(...)`，路径做成配置/参数 |

## 小结

- 优先 `Path` + `Files`；小文件 `readString`，大文件 `lines` / 缓冲流。
- 指定 UTF-8；用 try-with-resources；相对路径看"工作目录"。

**练习**：`exercises/exceptions`（含文件操作题）

package exceptions.problems;

/**
 * 【异常与文件 · 第 6 题】简易日志器    难度：★★★☆☆
 *
 * 题目描述：
 * 实现 FileLogger：info/warn/error 方法把带时间戳和级别的日志追加到文件（格式：2026-10-17 14:30:00 [INFO] message）。要求：线程安全、可设置最低级别、实现 AutoCloseable。
 *
 * 提示：
 * LocalDateTime + DateTimeFormatter；Files.writeString(..., APPEND, CREATE)；synchronized。
 *
 * 扩展思考（获取灵感）：
 * 日志每次打开关闭文件很慢，如何改进（BufferedWriter + 定时 flush）？看看 SLF4J + Logback 做了什么。
 *
 * 参考答案：暂无（鼓励先自己实现；做完可对照同类题的答案思路）
 */
public class Q06_SimpleLogger {
    public static void main(String[] args) {
        // TODO: 在这里编写你的代码
    }
}

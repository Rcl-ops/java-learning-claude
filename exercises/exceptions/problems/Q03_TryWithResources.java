package exceptions.problems;

/**
 * 【异常与文件 · 第 3 题】try-with-resources 与关闭顺序    难度：★★☆☆☆
 *
 * 题目描述：
 * 定义资源类 Res implements AutoCloseable（构造时打印"打开 X"，close 时打印"关闭 X"）。在一个 try-with-resources 中打开 A、B、C 三个资源，在块内抛出异常，观察：打开顺序、关闭顺序、异常如何传播。再让 close() 也抛异常，观察 getSuppressed()。
 *
 * 提示：
 * 资源按声明的逆序关闭；close 中的异常会作为 suppressed 附加到主异常上。
 *
 * 参考答案：solutions/A03_TryWithResources.java
 */
public class Q03_TryWithResources {
    static class Res implements AutoCloseable {
        // TODO
        @Override public void close() { }
    }

    public static void main(String[] args) {
        // TODO
    }
}

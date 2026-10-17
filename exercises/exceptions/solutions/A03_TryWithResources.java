package exceptions.solutions;

/**
 * 【参考答案】Q03_TryWithResources：try-with-resources 与关闭顺序
 *
 * 解题思路：
 * try-with-resources 保证逆序关闭，且主异常不会被 close 的异常覆盖。
 *
 * 运行：java -cp out exceptions.solutions.A03_TryWithResources
 */
public class A03_TryWithResources {
    static class Res implements AutoCloseable {
        private final String name;
        private final boolean failOnClose;
        Res(String name, boolean failOnClose) {
            this.name = name; this.failOnClose = failOnClose;
            System.out.println("打开 " + name);
        }
        @Override public void close() {
            System.out.println("关闭 " + name);
            if (failOnClose) throw new IllegalStateException("关闭 " + name + " 时出错");
        }
    }

    public static void main(String[] args) {
        try (Res a = new Res("A", false); Res b = new Res("B", true); Res c = new Res("C", false)) {
            System.out.println("执行业务逻辑");
            throw new RuntimeException("业务异常");
        } catch (Exception e) {
            System.out.println("捕获主异常：" + e.getMessage());
            for (Throwable s : e.getSuppressed()) System.out.println("  被抑制：" + s.getMessage());
        }
    }
}

package concurrency.problems;

/**
 * 【多线程与并发 · 第 6 题】虚拟线程：一万个任务    难度：★★★☆☆
 *
 * 题目描述：
 * 用 Executors.newVirtualThreadPerTaskExecutor() 提交 10,000 个各自 sleep 1 秒的任务，测量总耗时。再用 newFixedThreadPool(200) 做同样的事，对比耗时，并思考原因。
 *
 * 提示：
 * try-with-resources 管理 ExecutorService（Java 19+）。需要 JDK 21+。
 *
 * 扩展思考（获取灵感）：
 * 虚拟线程适合 CPU 密集任务吗？为什么不应池化虚拟线程？
 *
 * 参考答案：暂无（鼓励先自己实现；做完可对照同类题的答案思路）
 */
public class Q06_VirtualThreads {
    public static void main(String[] args) {
        // TODO: 在这里编写你的代码
    }
}

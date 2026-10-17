package concurrency.problems;

import java.util.concurrent.*;
/**
 * 【多线程与并发 · 第 1 题】创建线程的三种方式    难度：★☆☆☆☆
 *
 * 题目描述：
 * 分别用：(1) 继承 Thread；(2) 实现 Runnable（Lambda）；(3) Callable + FutureTask / ExecutorService 创建线程执行任务，每个任务打印线程名和 1~3 的数字。使用 join 等待全部完成后再打印"全部结束"。
 *
 * 扩展思考（获取灵感）：
 * 直接调用 run() 与调用 start() 的区别？线程名默认是什么？如何给线程命名？
 *
 * 参考答案：solutions/A01_ThreadBasics.java
 */
public class Q01_ThreadBasics {
    public static void main(String[] args) throws Exception {
        // TODO
    }
}

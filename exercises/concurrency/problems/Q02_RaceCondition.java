package concurrency.problems;

import java.util.concurrent.atomic.*;
/**
 * 【多线程与并发 · 第 2 题】竞态条件与修复    难度：★★☆☆☆
 *
 * 题目描述：
 * 两个线程各对共享计数器执行 10 万次自增：(1) 先用普通 int 演示结果小于 20 万；(2) 用 synchronized 修复；(3) 用 AtomicInteger 修复；(4) 用 LongAdder 修复。对比耗时。
 *
 * 提示：
 * n++ = 读、加、写三步，不是原子的。
 *
 * 扩展思考（获取灵感）：
 * volatile 能解决吗？为什么？ReentrantLock 与 synchronized 的差别？
 *
 * 参考答案：solutions/A02_RaceCondition.java
 */
public class Q02_RaceCondition {
    static int unsafeCount = 0;

    public static void main(String[] args) throws Exception {
        // TODO
    }
}

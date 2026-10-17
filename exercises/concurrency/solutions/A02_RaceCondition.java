package concurrency.solutions;

import java.util.concurrent.atomic.*;
/**
 * 【参考答案】Q02_RaceCondition：竞态条件与修复
 *
 * 解题思路：
 * 共享可变状态要么加锁，要么用原子类；无锁的原子类在竞争不激烈时通常更快。
 *
 * 运行：java -cp out concurrency.solutions.A02_RaceCondition
 */
public class A02_RaceCondition {
    static int unsafeCount = 0;
    static int syncCount = 0;
    static final Object LOCK = new Object();
    static final AtomicInteger atomic = new AtomicInteger();
    static final LongAdder adder = new LongAdder();
    static final int N = 100_000;

    static void runTwo(Runnable r) throws InterruptedException {
        Thread a = new Thread(r), b = new Thread(r);
        a.start(); b.start(); a.join(); b.join();
    }

    public static void main(String[] args) throws Exception {
        runTwo(() -> { for (int i = 0; i < N; i++) unsafeCount++; });
        System.out.println("不安全：" + unsafeCount + "（期望 " + 2 * N + "）");

        runTwo(() -> { for (int i = 0; i < N; i++) synchronized (LOCK) { syncCount++; } });
        System.out.println("synchronized：" + syncCount);

        runTwo(() -> { for (int i = 0; i < N; i++) atomic.incrementAndGet(); });
        System.out.println("AtomicInteger：" + atomic.get());

        runTwo(() -> { for (int i = 0; i < N; i++) adder.increment(); });
        System.out.println("LongAdder：" + adder.sum());
    }
}

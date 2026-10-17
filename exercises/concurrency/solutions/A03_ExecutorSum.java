package concurrency.solutions;

import java.util.*;
import java.util.concurrent.*;
/**
 * 【参考答案】Q03_ExecutorSum：线程池分段求和
 *
 * 解题思路：
 * 把大任务拆成互不相交的子任务，提交到线程池，再合并结果（分治思想）。
 *
 * 运行：java -cp out concurrency.solutions.A03_ExecutorSum
 */
public class A03_ExecutorSum {
    public static void main(String[] args) throws Exception {
        int n = 10_000_000, parts = 4;
        long[] data = new long[n];
        for (int i = 0; i < n; i++) data[i] = i + 1;

        long single = 0;
        for (long v : data) single += v;

        ExecutorService pool = Executors.newFixedThreadPool(parts);
        try {
            List<Future<Long>> futures = new ArrayList<>();
            int chunk = n / parts;
            for (int p = 0; p < parts; p++) {
                int from = p * chunk, to = (p == parts - 1) ? n : from + chunk;
                futures.add(pool.submit(() -> {
                    long s = 0;
                    for (int i = from; i < to; i++) s += data[i];
                    return s;
                }));
            }
            long total = 0;
            for (Future<Long> f : futures) total += f.get();
            System.out.println("并行：" + total + "，单线程：" + single + "，一致：" + (total == single));
        } finally {
            pool.shutdown();
        }
    }
}

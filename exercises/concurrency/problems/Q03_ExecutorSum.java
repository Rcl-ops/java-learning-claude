package concurrency.problems;

import java.util.*;
import java.util.concurrent.*;
/**
 * 【多线程与并发 · 第 3 题】线程池分段求和    难度：★★★☆☆
 *
 * 题目描述：
 * 把一个 1~10,000,000 的 long 数组分成 4 段，用固定大小为 4 的线程池并行求和（submit 返回 Future），汇总结果并与单线程结果对比。最后正确关闭线程池。
 *
 * 提示：
 * 每段一个 Callable<Long>；future.get() 汇总；finally 里 shutdown。
 *
 * 扩展思考（获取灵感）：
 * 数据量多大时并行才划算？对比 LongStream.parallel().sum() 与 ForkJoinPool。
 *
 * 参考答案：solutions/A03_ExecutorSum.java
 */
public class Q03_ExecutorSum {
    public static void main(String[] args) throws Exception {
        // TODO
    }
}

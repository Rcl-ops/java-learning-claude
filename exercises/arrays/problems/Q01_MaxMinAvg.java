package arrays.problems;

/**
 * 【数组、方法与递归 · 第 1 题】最大值、最小值、平均值    难度：★☆☆☆☆
 *
 * 题目描述：
 * 实现 max(int[])、min(int[])、average(int[])。数组为空时抛 IllegalArgumentException。
 *
 * 示例：
 * [3, 9, -2, 7] → max=9, min=-2, avg=4.25
 *
 * 提示：
 * 用第一个元素初始化 max/min，而不是 0（想想全是负数的数组）。
 *
 * 扩展思考（获取灵感）：
 * 一次遍历同时求出 max、min 与 sum；用 IntSummaryStatistics 或 Stream 怎样一行完成？
 *
 * 参考答案：solutions/A01_MaxMinAvg.java
 */
public class Q01_MaxMinAvg {
    static int max(int[] a) { throw new UnsupportedOperationException("TODO"); }
    static int min(int[] a) { throw new UnsupportedOperationException("TODO"); }
    static double average(int[] a) { throw new UnsupportedOperationException("TODO"); }

    public static void main(String[] args) {
        int[] a = {3, 9, -2, 7};
        System.out.println(max(a) + " " + min(a) + " " + average(a));
    }
}

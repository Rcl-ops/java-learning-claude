package arrays.problems;

/**
 * 【数组、方法与递归 · 第 6 题】斐波那契：递归、迭代与记忆化    难度：★★☆☆☆
 *
 * 题目描述：
 * 实现 fib(n)（fib(0)=0, fib(1)=1）三种版本：(1) 朴素递归；(2) 迭代；(3) 记忆化递归。比较 n=40 时三者的耗时。
 *
 * 提示：
 * 朴素递归会重复计算大量子问题，时间 O(2^n)；迭代只需两个变量。
 *
 * 扩展思考（获取灵感）：
 * n 很大时 long 会溢出，什么时候开始？用 BigInteger 如何解决？矩阵快速幂可以做到 O(log n)。
 *
 * 参考答案：solutions/A06_Fibonacci.java
 */
public class Q06_Fibonacci {
    static long fibRecursive(int n) { throw new UnsupportedOperationException("TODO"); }
    static long fibIterative(int n) { throw new UnsupportedOperationException("TODO"); }
    static long fibMemo(int n) { throw new UnsupportedOperationException("TODO"); }

    public static void main(String[] args) {
        int n = 40;
        // TODO: 用 System.nanoTime() 测量并打印三种方式的结果与耗时
    }
}

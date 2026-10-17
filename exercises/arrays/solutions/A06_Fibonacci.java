package arrays.solutions;

/**
 * 【参考答案】Q06_Fibonacci：斐波那契：递归、迭代与记忆化
 *
 * 解题思路：
 * 迭代法保持 a=fib(i-1)、b=fib(i)；记忆化用数组缓存已计算结果。
 *
 * 运行：java -cp out arrays.solutions.A06_Fibonacci
 */
public class A06_Fibonacci {
    static long fibRecursive(int n) { return n < 2 ? n : fibRecursive(n - 1) + fibRecursive(n - 2); }

    static long fibIterative(int n) {
        long a = 0, b = 1;
        for (int i = 0; i < n; i++) { long t = a + b; a = b; b = t; }
        return a;
    }

    static long fibMemo(int n) { return memo(n, new long[n + 2]); }

    private static long memo(int n, long[] cache) {
        if (n < 2) return n;
        if (cache[n] != 0) return cache[n];
        return cache[n] = memo(n - 1, cache) + memo(n - 2, cache);
    }

    public static void main(String[] args) {
        int n = 40;
        long t0 = System.nanoTime();
        long r1 = fibRecursive(n);
        long t1 = System.nanoTime();
        long r2 = fibIterative(n);
        long t2 = System.nanoTime();
        long r3 = fibMemo(n);
        long t3 = System.nanoTime();
        System.out.printf("递归 %d  %d ms%n", r1, (t1 - t0) / 1_000_000);
        System.out.printf("迭代 %d  %d µs%n", r2, (t2 - t1) / 1000);
        System.out.printf("记忆化 %d  %d µs%n", r3, (t3 - t2) / 1000);
    }
}

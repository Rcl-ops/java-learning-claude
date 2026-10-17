package algorithms.problems;

/**
 * 【算法与综合 · 第 1 题】最大公约数与最小公倍数    难度：★☆☆☆☆
 *
 * 题目描述：
 * 实现 gcd(long a, long b)（欧几里得辗转相除法，循环与递归两种）与 lcm(a, b)。并找出 1~100 中与 36 互质的数。
 *
 * 提示：
 * gcd(a, b) = gcd(b, a % b)，b==0 时返回 a；lcm = a / gcd * b（先除后乘防溢出）。
 *
 * 扩展思考（获取灵感）：
 * 扩展欧几里得算法求 ax + by = gcd 的整数解？
 *
 * 参考答案：solutions/A01_GcdLcm.java
 */
public class Q01_GcdLcm {
    static long gcd(long a, long b) { throw new UnsupportedOperationException("TODO"); }
    static long lcm(long a, long b) { throw new UnsupportedOperationException("TODO"); }

    public static void main(String[] args) {
        System.out.println(gcd(48, 18) + " " + lcm(4, 6));
    }
}

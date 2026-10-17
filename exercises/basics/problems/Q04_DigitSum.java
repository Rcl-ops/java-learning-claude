package basics.problems;

/**
 * 【基础：变量、类型、运算符 · 第 4 题】拆分数字    难度：★★☆☆☆
 *
 * 题目描述：
 * 给定一个正整数 n（例如 7359），不使用字符串，只用 / 和 %：
 * (1) 求各位数字之和；(2) 求位数；(3) 求反转后的数字（7359 → 9537）。
 *
 * 示例：
 * n=7359 → 数字和 24，位数 4，反转 9537
 *
 * 提示：
 * n % 10 取最后一位，n / 10 去掉最后一位；循环直到 n 变成 0。
 *
 * 扩展思考（获取灵感）：
 * 如果 n 是负数或 0 怎么办？反转结果超过 int 范围怎么办？（用 long 或 Math.multiplyExact 检测）
 *
 * 参考答案：solutions/A04_DigitSum.java
 */
public class Q04_DigitSum {
    static int digitSum(int n) { throw new UnsupportedOperationException("TODO"); }
    static int digitCount(int n) { throw new UnsupportedOperationException("TODO"); }
    static int reverse(int n) { throw new UnsupportedOperationException("TODO"); }

    public static void main(String[] args) {
        int n = 7359;
        System.out.println(digitSum(n) + " " + digitCount(n) + " " + reverse(n));
    }
}

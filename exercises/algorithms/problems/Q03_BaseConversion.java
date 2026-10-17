package algorithms.problems;

/**
 * 【算法与综合 · 第 3 题】进制转换    难度：★★☆☆☆
 *
 * 题目描述：
 * 不使用 Integer.toString(n, radix)，手写 toBase(int n, int base)（2~16 进制）和 fromBase(String s, int base)。再用标准库验证。
 *
 * 示例：
 * toBase(255, 16) → "FF"；toBase(10, 2) → "1010"；fromBase("FF", 16) → 255
 *
 * 提示：
 * 除基取余，倒序；字符表 "0123456789ABCDEF"。
 *
 * 扩展思考（获取灵感）：
 * 负数怎么表示（补码）？浮点数的二进制是什么样子（Double.doubleToLongBits）？
 *
 * 参考答案：solutions/A03_BaseConversion.java
 */
public class Q03_BaseConversion {
    static String toBase(int n, int base) { throw new UnsupportedOperationException("TODO"); }
    static int fromBase(String s, int base) { throw new UnsupportedOperationException("TODO"); }

    public static void main(String[] args) {
        System.out.println(toBase(255, 16) + " " + toBase(10, 2) + " " + fromBase("FF", 16));
    }
}

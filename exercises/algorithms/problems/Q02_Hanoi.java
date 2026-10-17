package algorithms.problems;

/**
 * 【算法与综合 · 第 2 题】汉诺塔    难度：★★★☆☆
 *
 * 题目描述：
 * 打印 n 个盘子从 A 柱经 B 柱移到 C 柱的全部步骤，并统计总步数（应为 2^n - 1）。
 *
 * 提示：
 * 把问题分成三步：n-1 个移到中转柱；最大的移到目标；n-1 个从中转柱移到目标。
 *
 * 扩展思考（获取灵感）：
 * n=64 需要多少步？每秒一步要多少年？能否不用递归（用栈模拟）？
 *
 * 参考答案：solutions/A02_Hanoi.java
 */
public class Q02_Hanoi {
    static int moves = 0;

    static void hanoi(int n, char from, char via, char to) {
        // TODO
    }

    public static void main(String[] args) {
        hanoi(3, 'A', 'B', 'C');
        System.out.println("共 " + moves + " 步");
    }
}

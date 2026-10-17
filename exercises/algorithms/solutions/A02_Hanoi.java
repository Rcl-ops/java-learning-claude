package algorithms.solutions;

/**
 * 【参考答案】Q02_Hanoi：汉诺塔
 *
 * 解题思路：
 * 递归：相信"hanoi(n-1)"已经能把 n-1 个盘子搬好，只需关注当前最大盘。
 *
 * 运行：java -cp out algorithms.solutions.A02_Hanoi
 */
public class A02_Hanoi {
    static int moves = 0;

    static void hanoi(int n, char from, char via, char to) {
        if (n == 0) return;
        hanoi(n - 1, from, to, via);
        System.out.println("第 " + (++moves) + " 步：盘 " + n + "  " + from + " → " + to);
        hanoi(n - 1, via, from, to);
    }

    public static void main(String[] args) {
        hanoi(3, 'A', 'B', 'C');
        System.out.println("共 " + moves + " 步");     // 7
    }
}

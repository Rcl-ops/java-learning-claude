package flow.solutions;

/**
 * 【参考答案】Q03_MultiplicationTable：九九乘法表
 *
 * 解题思路：
 * 外层控制行，内层控制每行的列数等于行号；每行结束换行。
 *
 * 运行：java -cp out flow.solutions.A03_MultiplicationTable
 */
public class A03_MultiplicationTable {
    public static void main(String[] args) {
        for (int i = 1; i <= 9; i++) {
            for (int j = 1; j <= i; j++) {
                System.out.printf("%d×%d=%-3d", j, i, i * j);
            }
            System.out.println();
        }
    }
}

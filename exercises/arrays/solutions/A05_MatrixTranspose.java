package arrays.solutions;

import java.util.Arrays;
/**
 * 【参考答案】Q05_MatrixTranspose：矩阵转置与对角线
 *
 * 解题思路：
 * 先创建 n×m 的新数组，双重循环赋值。
 *
 * 运行：java -cp out arrays.solutions.A05_MatrixTranspose
 */
public class A05_MatrixTranspose {
    static int[][] transpose(int[][] a) {
        int m = a.length, n = a[0].length;
        int[][] t = new int[n][m];
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++)
                t[j][i] = a[i][j];
        return t;
    }

    static int diagonalSum(int[][] sq, boolean main) {
        int s = 0, n = sq.length;
        for (int i = 0; i < n; i++) s += main ? sq[i][i] : sq[i][n - 1 - i];
        return s;
    }

    public static void main(String[] args) {
        int[][] a = {{1, 2, 3}, {4, 5, 6}};
        System.out.println(Arrays.deepToString(transpose(a)));            // [[1, 4], [2, 5], [3, 6]]
        int[][] sq = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        System.out.println(diagonalSum(sq, true) + " " + diagonalSum(sq, false));   // 15 15
    }
}

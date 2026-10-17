package arrays.problems;

import java.util.Arrays;
/**
 * 【数组、方法与递归 · 第 5 题】矩阵转置与对角线    难度：★★☆☆☆
 *
 * 题目描述：
 * 给定 m×n 的二维数组，实现 transpose(int[][]) 返回 n×m 的转置矩阵；再对 n×n 方阵求主对角线之和与副对角线之和。
 *
 * 示例：
 * [[1,2,3],[4,5,6]] → [[1,4],[2,5],[3,6]]
 *
 * 提示：
 * 新数组 t[j][i] = a[i][j]；方阵主对角线 a[i][i]，副对角线 a[i][n-1-i]。
 *
 * 扩展思考（获取灵感）：
 * 如何原地转置方阵？顺时针旋转 90° 与转置有什么关系？
 *
 * 参考答案：solutions/A05_MatrixTranspose.java
 */
public class Q05_MatrixTranspose {
    static int[][] transpose(int[][] a) {
        // TODO
        return null;
    }

    public static void main(String[] args) {
        int[][] a = {{1, 2, 3}, {4, 5, 6}};
        System.out.println(Arrays.deepToString(transpose(a)));
    }
}

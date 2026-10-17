package arrays.problems;

import java.util.Arrays;
/**
 * 【数组、方法与递归 · 第 2 题】原地反转数组    难度：★☆☆☆☆
 *
 * 题目描述：
 * 实现 reverse(int[] a)，原地反转（不创建新数组）。
 *
 * 示例：
 * [1,2,3,4,5] → [5,4,3,2,1]
 *
 * 提示：
 * 双指针 i=0, j=length-1，交换后向中间靠拢，直到 i >= j。
 *
 * 扩展思考（获取灵感）：
 * 如何原地旋转数组（向右移动 k 位）？提示：三次反转。
 *
 * 参考答案：solutions/A02_ReverseArray.java
 */
public class Q02_ReverseArray {
    static void reverse(int[] a) {
        // TODO
    }

    public static void main(String[] args) {
        int[] a = {1, 2, 3, 4, 5};
        reverse(a);
        System.out.println(Arrays.toString(a));
    }
}

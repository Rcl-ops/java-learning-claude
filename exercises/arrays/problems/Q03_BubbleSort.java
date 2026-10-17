package arrays.problems;

import java.util.Arrays;
/**
 * 【数组、方法与递归 · 第 3 题】冒泡排序    难度：★★☆☆☆
 *
 * 题目描述：
 * 实现冒泡排序 bubbleSort(int[] a)（升序）。优化：某一趟没有发生交换就提前结束。并输出每一趟之后的数组状态，观察"最大的数沉到最后"的过程。
 *
 * 提示：
 * 外层趟数 n-1；内层比较相邻元素；已排好的尾部不必再比较。
 *
 * 扩展思考（获取灵感）：
 * 再实现选择排序、插入排序，比较三者交换/比较次数。与 Arrays.sort（双轴快排/TimSort）相比差距多大？
 *
 * 参考答案：solutions/A03_BubbleSort.java
 */
public class Q03_BubbleSort {
    static void bubbleSort(int[] a) {
        // TODO
    }

    public static void main(String[] args) {
        int[] a = {5, 2, 9, 1, 5, 6};
        bubbleSort(a);
        System.out.println(Arrays.toString(a));
    }
}

package arrays.solutions;

import java.util.Arrays;
/**
 * 【参考答案】Q03_BubbleSort：冒泡排序
 *
 * 解题思路：
 * 每趟把当前最大值"冒"到末尾；用 swapped 标志位提前结束，最优情况 O(n)。
 *
 * 运行：java -cp out arrays.solutions.A03_BubbleSort
 */
public class A03_BubbleSort {
    static void bubbleSort(int[] a) {
        for (int i = 0; i < a.length - 1; i++) {
            boolean swapped = false;
            for (int j = 0; j < a.length - 1 - i; j++) {
                if (a[j] > a[j + 1]) {
                    int t = a[j]; a[j] = a[j + 1]; a[j + 1] = t;
                    swapped = true;
                }
            }
            System.out.println("第 " + (i + 1) + " 趟：" + Arrays.toString(a));
            if (!swapped) break;
        }
    }

    public static void main(String[] args) {
        int[] a = {5, 2, 9, 1, 5, 6};
        bubbleSort(a);
        System.out.println(Arrays.toString(a));
    }
}

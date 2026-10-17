package arrays.solutions;

import java.util.Arrays;
/**
 * 【参考答案】Q02_ReverseArray：原地反转数组
 *
 * 解题思路：
 * 双指针交换；奇数长度时中间元素不动。
 *
 * 运行：java -cp out arrays.solutions.A02_ReverseArray
 */
public class A02_ReverseArray {
    static void reverse(int[] a) {
        for (int i = 0, j = a.length - 1; i < j; i++, j--) {
            int t = a[i]; a[i] = a[j]; a[j] = t;
        }
    }

    public static void main(String[] args) {
        int[] a = {1, 2, 3, 4, 5};
        reverse(a);
        System.out.println(Arrays.toString(a));
    }
}

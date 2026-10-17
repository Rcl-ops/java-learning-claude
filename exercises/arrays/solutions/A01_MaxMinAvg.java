package arrays.solutions;

/**
 * 【参考答案】Q01_MaxMinAvg：最大值、最小值、平均值
 *
 * 解题思路：
 * 先校验空数组；循环一遍更新极值；平均值用 double 避免整数除法。
 *
 * 运行：java -cp out arrays.solutions.A01_MaxMinAvg
 */
public class A01_MaxMinAvg {
    static void check(int[] a) {
        if (a == null || a.length == 0) throw new IllegalArgumentException("数组不能为空");
    }

    static int max(int[] a) {
        check(a);
        int m = a[0];
        for (int x : a) if (x > m) m = x;
        return m;
    }

    static int min(int[] a) {
        check(a);
        int m = a[0];
        for (int x : a) if (x < m) m = x;
        return m;
    }

    static double average(int[] a) {
        check(a);
        long sum = 0;                       // 用 long 防溢出
        for (int x : a) sum += x;
        return (double) sum / a.length;
    }

    public static void main(String[] args) {
        int[] a = {3, 9, -2, 7};
        System.out.println(max(a) + " " + min(a) + " " + average(a));   // 9 -2 4.25
    }
}

package collections.problems;

import java.util.*;
/**
 * 【集合 · 第 3 题】两数之和    难度：★★☆☆☆
 *
 * 题目描述：
 * 给定整数数组 nums 和目标值 target，返回和为 target 的两个元素的下标（假设恰好一个解，同一元素不能用两次）。要求时间复杂度 O(n)。
 *
 * 示例：
 * nums=[2,7,11,15], target=9 → [0,1]
 *
 * 提示：
 * 遍历时把 "值 → 下标" 存入 HashMap，每次查 target - x 是否已出现。
 *
 * 扩展思考（获取灵感）：
 * 暴力 O(n²) 与哈希 O(n) 的实际差距？如果数组已排序，用双指针可以做到 O(1) 空间。
 *
 * 参考答案：solutions/A03_TwoSum.java
 */
public class Q03_TwoSum {
    static int[] twoSum(int[] nums, int target) {
        // TODO
        return null;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(twoSum(new int[]{2, 7, 11, 15}, 9)));
    }
}

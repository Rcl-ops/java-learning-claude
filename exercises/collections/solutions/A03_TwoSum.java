package collections.solutions;

import java.util.*;
/**
 * 【参考答案】Q03_TwoSum：两数之和
 *
 * 解题思路：
 * 用空间换时间：边遍历边记录，用 O(1) 的哈希查找替代内层循环。
 *
 * 运行：java -cp out collections.solutions.A03_TwoSum
 */
public class A03_TwoSum {
    static int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> idx = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            Integer j = idx.get(target - nums[i]);
            if (j != null) return new int[]{j, i};
            idx.put(nums[i], i);
        }
        throw new IllegalArgumentException("无解");
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(twoSum(new int[]{2, 7, 11, 15}, 9)));   // [0, 1]
    }
}

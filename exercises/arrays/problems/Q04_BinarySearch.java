package arrays.problems;

/**
 * 【数组、方法与递归 · 第 4 题】二分查找    难度：★★☆☆☆
 *
 * 题目描述：
 * 在升序数组中查找 target，返回下标，找不到返回 -1。分别用循环和递归各写一遍。
 *
 * 示例：
 * [1,3,5,7,9], target=7 → 3；target=4 → -1
 *
 * 提示：
 * mid = low + (high - low) / 2，避免 low+high 溢出。边界条件：low <= high。
 *
 * 扩展思考（获取灵感）：
 * 找第一个 >= target 的位置（lower bound）怎么写？这是处理"有重复元素"的关键。
 *
 * 参考答案：solutions/A04_BinarySearch.java
 */
public class Q04_BinarySearch {
    static int search(int[] a, int target) {
        // TODO
        return -1;
    }

    public static void main(String[] args) {
        int[] a = {1, 3, 5, 7, 9};
        System.out.println(search(a, 7) + " " + search(a, 4));
    }
}

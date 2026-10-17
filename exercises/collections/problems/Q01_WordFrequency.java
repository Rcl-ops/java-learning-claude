package collections.problems;

import java.util.*;
/**
 * 【集合 · 第 1 题】词频统计与排序    难度：★★☆☆☆
 *
 * 题目描述：
 * 统计一段英文文本中每个单词出现的次数（忽略大小写和标点），按次数降序（次数相同按字母序）输出前 5 个。
 *
 * 示例：
 * "the cat and the hat and the bat" → the=3, and=2, bat=1, cat=1, hat=1
 *
 * 提示：
 * 先用 replaceAll("[^a-zA-Z ]", "") 清理；Map.merge 计数；把 entrySet 放入 List 排序。
 *
 * 扩展思考（获取灵感）：
 * 用 Stream 的 groupingBy + counting 重写；如何做到 O(n log k) 的 Top-K（PriorityQueue）？
 *
 * 参考答案：solutions/A01_WordFrequency.java
 */
public class Q01_WordFrequency {
    public static void main(String[] args) {
        String text = "The cat and the hat and the bat. The end!";
        // TODO
    }
}

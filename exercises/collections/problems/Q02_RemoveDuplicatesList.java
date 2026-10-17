package collections.problems;

import java.util.*;
/**
 * 【集合 · 第 2 题】列表去重并保持顺序    难度：★☆☆☆☆
 *
 * 题目描述：
 * 给定 List<Integer>，去除重复元素并保持首次出现的顺序。再实现：找出出现超过一次的元素。
 *
 * 示例：
 * [3,1,3,2,1] → [3,1,2]；重复元素 [3,1]
 *
 * 提示：
 * LinkedHashSet；找重复：HashSet.add 返回 false 表示已存在。
 *
 * 扩展思考（获取灵感）：
 * Stream.distinct() 做同样的事；自定义对象去重需要什么（equals/hashCode）？
 *
 * 参考答案：solutions/A02_RemoveDuplicatesList.java
 */
public class Q02_RemoveDuplicatesList {
    static List<Integer> dedupe(List<Integer> in) {
        // TODO
        return null;
    }

    public static void main(String[] args) {
        System.out.println(dedupe(List.of(3, 1, 3, 2, 1)));
    }
}

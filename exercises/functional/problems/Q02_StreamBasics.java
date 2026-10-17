package functional.problems;

import java.util.*;
import java.util.stream.*;
/**
 * 【Lambda 与 Stream · 第 2 题】Stream 基础操作    难度：★★☆☆☆
 *
 * 题目描述：
 * 给定 List<Integer> nums = 1..20，用 Stream 完成：(1) 偶数的平方列表；(2) 所有奇数之和；(3) 最大值与平均值；(4) 是否存在大于 19 的数；(5) 前 5 个能被 3 整除的数；(6) 用逗号连接成字符串。
 *
 * 参考答案：solutions/A02_StreamBasics.java
 */
public class Q02_StreamBasics {
    public static void main(String[] args) {
        List<Integer> nums = IntStream.rangeClosed(1, 20).boxed().toList();
        // TODO
    }
}

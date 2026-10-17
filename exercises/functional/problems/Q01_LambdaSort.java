package functional.problems;

import java.util.*;
/**
 * 【Lambda 与 Stream · 第 1 题】Lambda 与方法引用排序    难度：★☆☆☆☆
 *
 * 题目描述：
 * 对 List<String> 分别：(1) 按长度升序（Lambda）；(2) 按字典序忽略大小写（方法引用 String::compareToIgnoreCase）；(3) 按长度降序，长度相同按字典序（Comparator 链）。
 *
 * 参考答案：solutions/A01_LambdaSort.java
 */
public class Q01_LambdaSort {
    public static void main(String[] args) {
        List<String> words = new ArrayList<>(List.of("banana", "Apple", "fig", "cherry", "date", "Kiwi"));
        // TODO
    }
}

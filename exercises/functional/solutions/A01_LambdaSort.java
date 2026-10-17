package functional.solutions;

import java.util.*;
/**
 * 【参考答案】Q01_LambdaSort：Lambda 与方法引用排序
 *
 * 解题思路：
 * Comparator.comparingInt(String::length).reversed().thenComparing(Comparator.naturalOrder())。
 *
 * 运行：java -cp out functional.solutions.A01_LambdaSort
 */
public class A01_LambdaSort {
    public static void main(String[] args) {
        List<String> words = new ArrayList<>(List.of("banana", "Apple", "fig", "cherry", "date", "Kiwi"));

        words.sort((a, b) -> a.length() - b.length());
        System.out.println("长度升序：" + words);

        words.sort(String::compareToIgnoreCase);
        System.out.println("字典序（忽略大小写）：" + words);

        words.sort(Comparator.comparingInt(String::length).reversed().thenComparing(Comparator.naturalOrder()));
        System.out.println("长度降序+字典序：" + words);
    }
}

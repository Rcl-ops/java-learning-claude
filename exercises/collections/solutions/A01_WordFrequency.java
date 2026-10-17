package collections.solutions;

import java.util.*;
/**
 * 【参考答案】Q01_WordFrequency：词频统计与排序
 *
 * 解题思路：
 * HashMap 计数 → 转为 List<Map.Entry> → 自定义 Comparator（次数降序，再按键升序）。
 *
 * 运行：java -cp out collections.solutions.A01_WordFrequency
 */
public class A01_WordFrequency {
    public static void main(String[] args) {
        String text = "The cat and the hat and the bat. The end!";
        Map<String, Integer> freq = new HashMap<>();
        for (String w : text.toLowerCase().replaceAll("[^a-z\\s]", "").split("\\s+")) {
            if (!w.isEmpty()) freq.merge(w, 1, Integer::sum);
        }
        List<Map.Entry<String, Integer>> list = new ArrayList<>(freq.entrySet());
        list.sort(Map.Entry.<String, Integer>comparingByValue().reversed().thenComparing(Map.Entry.comparingByKey()));
        for (int i = 0; i < Math.min(5, list.size()); i++) {
            System.out.println(list.get(i).getKey() + "=" + list.get(i).getValue());
        }
    }
}

package collections.solutions;

import java.util.*;
/**
 * 【参考答案】Q02_RemoveDuplicatesList：列表去重并保持顺序
 *
 * 解题思路：
 * LinkedHashSet = 去重 + 保序；两个 Set 区分"见过"和"重复"。
 *
 * 运行：java -cp out collections.solutions.A02_RemoveDuplicatesList
 */
public class A02_RemoveDuplicatesList {
    static List<Integer> dedupe(List<Integer> in) {
        return new ArrayList<>(new LinkedHashSet<>(in));
    }

    static Set<Integer> duplicates(List<Integer> in) {
        Set<Integer> seen = new HashSet<>(), dup = new LinkedHashSet<>();
        for (int x : in) if (!seen.add(x)) dup.add(x);
        return dup;
    }

    public static void main(String[] args) {
        List<Integer> in = List.of(3, 1, 3, 2, 1);
        System.out.println(dedupe(in));          // [3, 1, 2]
        System.out.println(duplicates(in));      // [3, 1]
    }
}

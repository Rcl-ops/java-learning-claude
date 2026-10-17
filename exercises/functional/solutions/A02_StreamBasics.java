package functional.solutions;

import java.util.*;
import java.util.stream.*;
/**
 * 【参考答案】Q02_StreamBasics：Stream 基础操作
 *
 * 解题思路：
 * filter / map / mapToInt / limit / collect(joining) 是最常用的组合。
 *
 * 运行：java -cp out functional.solutions.A02_StreamBasics
 */
public class A02_StreamBasics {
    public static void main(String[] args) {
        List<Integer> nums = IntStream.rangeClosed(1, 20).boxed().toList();
        System.out.println(nums.stream().filter(n -> n % 2 == 0).map(n -> n * n).toList());
        System.out.println(nums.stream().filter(n -> n % 2 != 0).mapToInt(Integer::intValue).sum());
        System.out.println(nums.stream().mapToInt(Integer::intValue).max().getAsInt());
        System.out.println(nums.stream().mapToInt(Integer::intValue).average().orElse(0));
        System.out.println(nums.stream().anyMatch(n -> n > 19));
        System.out.println(nums.stream().filter(n -> n % 3 == 0).limit(5).toList());
        System.out.println(nums.stream().map(String::valueOf).collect(Collectors.joining(",")));
    }
}

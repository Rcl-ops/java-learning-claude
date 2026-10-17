package functional.solutions;

import java.util.*;
import java.util.function.*;
/**
 * 【参考答案】Q04_CustomFunctionalInterface：自定义函数式接口
 *
 * 解题思路：
 * 函数式接口 = 一个抽象方法；高阶函数 = 接受或返回函数的方法。
 *
 * 运行：java -cp out functional.solutions.A04_CustomFunctionalInterface
 */
public class A04_CustomFunctionalInterface {
    @FunctionalInterface
    interface TriFunction<A, B, C, R> { R apply(A a, B b, C c); }

    static <T> List<T> filter(List<T> list, Predicate<T> p) {
        List<T> out = new ArrayList<>();
        for (T t : list) if (p.test(t)) out.add(t);
        return out;
    }

    static <T, R> List<R> map(List<T> list, Function<T, R> f) {
        List<R> out = new ArrayList<>();
        for (T t : list) out.add(f.apply(t));
        return out;
    }

    public static void main(String[] args) {
        TriFunction<Integer, Integer, Integer, Integer> vol = (a, b, c) -> a * b * c;
        System.out.println(vol.apply(2, 3, 4));                                    // 24

        List<String> words = List.of("apple", "fig", "banana", "kiwi");
        System.out.println(filter(words, w -> w.length() > 3));                    // [apple, banana, kiwi]
        System.out.println(map(words, String::length));                            // [5, 3, 6, 4]

        Function<Integer, Function<Integer, Integer>> add = a -> b -> a + b;       // 柯里化
        System.out.println(add.apply(3).apply(4));                                  // 7
    }
}

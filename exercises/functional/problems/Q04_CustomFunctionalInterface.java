package functional.problems;

import java.util.*;
import java.util.function.*;
/**
 * 【Lambda 与 Stream · 第 4 题】自定义函数式接口    难度：★★☆☆☆
 *
 * 题目描述：
 * 定义 @FunctionalInterface TriFunction<A,B,C,R>（apply 三个参数）。再写一个泛型方法 <T> List<T> filter(List<T> list, Predicate<T> p) 与 <T,R> List<R> map(List<T> list, Function<T,R> f)（不使用 Stream）。用 Lambda 与方法引用调用它们。
 *
 * 扩展思考（获取灵感）：
 * 再实现一个柯里化：Function<Integer, Function<Integer, Integer>> add；组合 andThen。
 *
 * 参考答案：solutions/A04_CustomFunctionalInterface.java
 */
public class Q04_CustomFunctionalInterface {
    // TODO: TriFunction、filter、map

    public static void main(String[] args) {
        // TODO
    }
}

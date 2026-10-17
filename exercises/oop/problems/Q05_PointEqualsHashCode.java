package oop.problems;

import java.util.*;
/**
 * 【面向对象 · 第 5 题】重写 equals / hashCode / toString    难度：★★☆☆☆
 *
 * 题目描述：
 * 设计不可变类 Point(int x, int y)，正确重写 equals、hashCode、toString。验证：两个坐标相同的 Point 放入 HashSet 只保留一个；放入 HashMap 作为 key 能取回。再演示：只重写 equals 不重写 hashCode 会发生什么。
 *
 * 提示：
 * equals 要满足自反、对称、传递、一致，并处理 null 与类型；hashCode 用 Objects.hash。
 *
 * 扩展思考（获取灵感）：
 * record Point(int x, int y) 一行搞定的原理是什么？继承场景下 equals 为什么难写对？
 *
 * 参考答案：solutions/A05_PointEqualsHashCode.java
 */
public class Q05_PointEqualsHashCode {
    static class Point {
        // TODO
    }

    public static void main(String[] args) {
        // TODO
    }
}

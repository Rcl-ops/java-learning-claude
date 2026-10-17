package oop.problems;

import java.util.*;
/**
 * 【面向对象 · 第 3 题】图形继承与多态    难度：★★☆☆☆
 *
 * 题目描述：
 * 定义抽象类 Shape（抽象方法 area()、perimeter()）及子类 Circle、Rectangle、Triangle(三边，海伦公式)。在 main 中把它们放进 List<Shape>，按面积降序排序并打印；统计总面积。
 *
 * 提示：
 * Comparator.comparingDouble(Shape::area).reversed()。
 *
 * 扩展思考（获取灵感）：
 * 用 sealed interface + record + switch 模式匹配重写；新增图形时哪种设计改动最少？
 *
 * 参考答案：solutions/A03_ShapeHierarchy.java
 */
public class Q03_ShapeHierarchy {
    static abstract class Shape {
        abstract double area();
        abstract double perimeter();
    }

    // TODO: Circle、Rectangle、Triangle

    public static void main(String[] args) {
        // TODO
    }
}

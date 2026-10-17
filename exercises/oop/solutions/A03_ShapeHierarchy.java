package oop.solutions;

import java.util.*;
/**
 * 【参考答案】Q03_ShapeHierarchy：图形继承与多态
 *
 * 解题思路：
 * 多态：调用方只依赖 Shape；排序使用比较器；新增子类无需改调用代码。
 *
 * 运行：java -cp out oop.solutions.A03_ShapeHierarchy
 */
public class A03_ShapeHierarchy {
    static abstract class Shape {
        abstract double area();
        abstract double perimeter();
        @Override public String toString() {
            return String.format("%s[面积=%.2f, 周长=%.2f]", getClass().getSimpleName(), area(), perimeter());
        }
    }

    static class Circle extends Shape {
        final double r;
        Circle(double r) { this.r = r; }
        double area() { return Math.PI * r * r; }
        double perimeter() { return 2 * Math.PI * r; }
    }

    static class Rectangle extends Shape {
        final double w, h;
        Rectangle(double w, double h) { this.w = w; this.h = h; }
        double area() { return w * h; }
        double perimeter() { return 2 * (w + h); }
    }

    static class Triangle extends Shape {
        final double a, b, c;
        Triangle(double a, double b, double c) {
            if (a + b <= c || a + c <= b || b + c <= a) throw new IllegalArgumentException("不能构成三角形");
            this.a = a; this.b = b; this.c = c;
        }
        double perimeter() { return a + b + c; }
        double area() { double p = perimeter() / 2; return Math.sqrt(p * (p - a) * (p - b) * (p - c)); }
    }

    public static void main(String[] args) {
        List<Shape> shapes = new ArrayList<>(List.of(new Circle(1.5), new Rectangle(3, 4), new Triangle(3, 4, 5)));
        shapes.sort(Comparator.comparingDouble(Shape::area).reversed());
        shapes.forEach(System.out::println);
        double total = 0;
        for (Shape s : shapes) total += s.area();
        System.out.printf("总面积 = %.2f%n", total);
    }
}

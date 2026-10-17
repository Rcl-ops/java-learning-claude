package oop.problems;

/**
 * 【面向对象 · 第 1 题】封装：学生类    难度：★☆☆☆☆
 *
 * 题目描述：
 * 设计 Student 类：私有字段 name、age(0~150)、score(0~100)；提供构造器、getter/setter（setter 中校验范围，非法值抛 IllegalArgumentException）、toString。
 *
 * 提示：
 * 构造器里也应调用 setter 复用校验逻辑。
 *
 * 扩展思考（获取灵感）：
 * 如何让它不可变（final 字段 + 无 setter）？用 record 怎么写？两种方案的取舍？
 *
 * 参考答案：solutions/A01_StudentEncapsulation.java
 */
public class Q01_StudentEncapsulation {
    static class Student {
        // TODO: 私有字段、构造器、getter/setter、toString
    }

    public static void main(String[] args) {
        // TODO: 创建对象，并测试非法值
    }
}

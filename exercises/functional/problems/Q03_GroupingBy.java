package functional.problems;

import java.util.*;
import java.util.stream.*;
/**
 * 【Lambda 与 Stream · 第 3 题】分组与统计    难度：★★☆☆☆
 *
 * 题目描述：
 * 给定 record Employee(String name, String dept, double salary)。求：(1) 每个部门的人数；(2) 每个部门的平均工资；(3) 每个部门工资最高的员工；(4) 工资 ≥ 10000 与 < 10000 的两组姓名列表（partitioningBy）。
 *
 * 参考答案：solutions/A03_GroupingBy.java
 */
public class Q03_GroupingBy {
    record Employee(String name, String dept, double salary) { }

    public static void main(String[] args) {
        List<Employee> list = List.of(
                new Employee("Ann", "研发", 15000), new Employee("Bob", "研发", 12000),
                new Employee("Cid", "销售", 9000), new Employee("Dan", "销售", 11000),
                new Employee("Eve", "人事", 8000));
        // TODO
    }
}

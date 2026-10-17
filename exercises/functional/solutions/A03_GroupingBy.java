package functional.solutions;

import java.util.*;
import java.util.stream.*;
/**
 * 【参考答案】Q03_GroupingBy：分组与统计
 *
 * 解题思路：
 * groupingBy 的第二个参数是"下游收集器"：counting、averagingDouble、maxBy ……
 *
 * 运行：java -cp out functional.solutions.A03_GroupingBy
 */
public class A03_GroupingBy {
    record Employee(String name, String dept, double salary) { }

    public static void main(String[] args) {
        List<Employee> list = List.of(
                new Employee("Ann", "研发", 15000), new Employee("Bob", "研发", 12000),
                new Employee("Cid", "销售", 9000), new Employee("Dan", "销售", 11000),
                new Employee("Eve", "人事", 8000));

        Map<String, Long> count = list.stream().collect(Collectors.groupingBy(Employee::dept, TreeMap::new, Collectors.counting()));
        Map<String, Double> avg = list.stream().collect(Collectors.groupingBy(Employee::dept, TreeMap::new, Collectors.averagingDouble(Employee::salary)));
        System.out.println(count);
        System.out.println(avg);
        Map<String, Optional<Employee>> top = list.stream().collect(
                Collectors.groupingBy(Employee::dept, TreeMap::new, Collectors.maxBy(Comparator.comparingDouble(Employee::salary))));
        top.forEach((d, e) -> System.out.println(d + " 最高：" + e.get().name()));
        System.out.println(list.stream().collect(Collectors.partitioningBy(e -> e.salary() >= 10000,
                Collectors.mapping(Employee::name, Collectors.toList()))));
    }
}

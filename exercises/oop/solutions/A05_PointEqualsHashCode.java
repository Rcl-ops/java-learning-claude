package oop.solutions;

import java.util.*;
/**
 * 【参考答案】Q05_PointEqualsHashCode：重写 equals / hashCode / toString
 *
 * 解题思路：
 * equals/hashCode 使用同一组字段；用 final 字段保证 hash 稳定；与 record 的自动实现对比。
 *
 * 运行：java -cp out oop.solutions.A05_PointEqualsHashCode
 */
public class A05_PointEqualsHashCode {
    static final class Point {
        private final int x, y;
        Point(int x, int y) { this.x = x; this.y = y; }

        @Override public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Point p)) return false;
            return x == p.x && y == p.y;
        }
        @Override public int hashCode() { return Objects.hash(x, y); }
        @Override public String toString() { return "(" + x + "," + y + ")"; }
    }

    static final class BadPoint {                       // 故意只重写 equals
        final int x, y;
        BadPoint(int x, int y) { this.x = x; this.y = y; }
        @Override public boolean equals(Object o) { return o instanceof BadPoint p && x == p.x && y == p.y; }
    }

    public static void main(String[] args) {
        Set<Point> set = new HashSet<>();
        set.add(new Point(1, 2));
        set.add(new Point(1, 2));
        System.out.println("Point 去重后大小：" + set.size());          // 1

        Map<Point, String> map = new HashMap<>();
        map.put(new Point(3, 4), "A");
        System.out.println(map.get(new Point(3, 4)));                  // A

        Set<BadPoint> bad = new HashSet<>();
        bad.add(new BadPoint(1, 2));
        bad.add(new BadPoint(1, 2));
        System.out.println("BadPoint 大小（通常为 2）：" + bad.size());
    }
}

package flow.problems;

/**
 * 【流程控制 · 第 2 题】成绩等级    难度：★☆☆☆☆
 *
 * 题目描述：
 * 实现 grade(int score)：90~100 返回 "A"，80~89 "B"，70~79 "C"，60~69 "D"，0~59 "F"，其他（<0 或 >100）抛 IllegalArgumentException。
 * 要求：使用 switch 表达式（提示：score / 10）。
 *
 * 提示：
 * score / 10 得到十位；case 10, 9 -> "A"。先校验范围。
 *
 * 扩展思考（获取灵感）：
 * 如果等级边界不规则（如 85 分才是 A-），switch 还适合吗？换成 if-else 链或 TreeMap.floorEntry 怎样？
 *
 * 参考答案：solutions/A02_GradeLevel.java
 */
public class Q02_GradeLevel {
    static String grade(int score) {
        // TODO
        return null;
    }

    public static void main(String[] args) {
        for (int s : new int[]{100, 95, 85, 72, 60, 59, 0}) System.out.println(s + " -> " + grade(s));
    }
}

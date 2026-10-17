package flow.problems;

/**
 * 【流程控制 · 第 1 题】判断闰年    难度：★☆☆☆☆
 *
 * 题目描述：
 * 实现 isLeap(int year)：能被 4 整除但不能被 100 整除，或能被 400 整除的年份是闰年。
 *
 * 示例：
 * 2000 → true；1900 → false；2024 → true；2023 → false
 *
 * 提示：
 * 一行布尔表达式即可：注意 && 与 || 的组合和括号。
 *
 * 扩展思考（获取灵感）：
 * 再实现 daysInMonth(int year, int month)，用 switch 表达式。（Java 标准库也有 Year.isLeap）
 *
 * 参考答案：solutions/A01_LeapYear.java
 */
public class Q01_LeapYear {
    static boolean isLeap(int year) {
        // TODO
        return false;
    }

    public static void main(String[] args) {
        for (int y : new int[]{2000, 1900, 2024, 2023}) {
            System.out.println(y + " -> " + isLeap(y));
        }
    }
}

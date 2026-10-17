package flow.solutions;

/**
 * 【参考答案】Q02_GradeLevel：成绩等级
 *
 * 解题思路：
 * 先范围校验，再 switch (score / 10) 的箭头形式，多值 case 用逗号。
 *
 * 运行：java -cp out flow.solutions.A02_GradeLevel
 */
public class A02_GradeLevel {
    static String grade(int score) {
        if (score < 0 || score > 100) throw new IllegalArgumentException("分数非法：" + score);
        return switch (score / 10) {
            case 10, 9 -> "A";
            case 8 -> "B";
            case 7 -> "C";
            case 6 -> "D";
            default -> "F";
        };
    }

    public static void main(String[] args) {
        for (int s : new int[]{100, 95, 85, 72, 60, 59, 0}) System.out.println(s + " -> " + grade(s));
    }
}

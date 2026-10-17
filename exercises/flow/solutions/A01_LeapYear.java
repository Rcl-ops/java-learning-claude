package flow.solutions;

/**
 * 【参考答案】Q01_LeapYear：判断闰年
 *
 * 解题思路：
 * (year % 4 == 0 && year % 100 != 0) || year % 400 == 0。
 *
 * 运行：java -cp out flow.solutions.A01_LeapYear
 */
public class A01_LeapYear {
    static boolean isLeap(int year) {
        return (year % 4 == 0 && year % 100 != 0) || year % 400 == 0;
    }

    static int daysInMonth(int year, int month) {
        return switch (month) {
            case 1, 3, 5, 7, 8, 10, 12 -> 31;
            case 4, 6, 9, 11 -> 30;
            case 2 -> isLeap(year) ? 29 : 28;
            default -> throw new IllegalArgumentException("月份非法：" + month);
        };
    }

    public static void main(String[] args) {
        for (int y : new int[]{2000, 1900, 2024, 2023}) {
            System.out.println(y + " -> " + isLeap(y));
        }
        System.out.println(daysInMonth(2024, 2));   // 29
    }
}

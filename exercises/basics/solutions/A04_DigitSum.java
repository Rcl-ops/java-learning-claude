package basics.solutions;

/**
 * 【参考答案】Q04_DigitSum：拆分数字
 *
 * 解题思路：
 * 循环：digit = n % 10；累加；n /= 10。反转：rev = rev * 10 + digit。
 *
 * 运行：java -cp out basics.solutions.A04_DigitSum
 */
public class A04_DigitSum {
    static int digitSum(int n) {
        int sum = 0;
        for (; n > 0; n /= 10) sum += n % 10;
        return sum;
    }

    static int digitCount(int n) {
        int c = 0;
        do { c++; n /= 10; } while (n > 0);       // do-while 保证 0 也算 1 位
        return c;
    }

    static int reverse(int n) {
        int rev = 0;
        while (n > 0) { rev = rev * 10 + n % 10; n /= 10; }
        return rev;
    }

    public static void main(String[] args) {
        int n = 7359;
        System.out.println(digitSum(n) + " " + digitCount(n) + " " + reverse(n));   // 24 4 9537
    }
}

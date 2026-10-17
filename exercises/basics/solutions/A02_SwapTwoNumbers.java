package basics.solutions;

/**
 * 【参考答案】Q02_SwapTwoNumbers：交换两个变量的值
 *
 * 解题思路：
 * 三种方法结果相同；临时变量最清晰，是工程中的首选。
 *
 * 运行：java -cp out basics.solutions.A02_SwapTwoNumbers
 */
public class A02_SwapTwoNumbers {
    public static void main(String[] args) {
        int a = 3, b = 7;
        int t = a; a = b; b = t;                       // 1. 临时变量
        System.out.println("临时变量：a=" + a + " b=" + b);

        a = 3; b = 7;
        a = a + b; b = a - b; a = a - b;               // 2. 加减法
        System.out.println("加减法：a=" + a + " b=" + b);

        a = 3; b = 7;
        a ^= b; b ^= a; a ^= b;                        // 3. 异或
        System.out.println("异或：a=" + a + " b=" + b);
    }
}

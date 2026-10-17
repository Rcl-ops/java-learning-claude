package flow.solutions;

/**
 * 【参考答案】Q04_PrimeNumbers：100 以内的素数
 *
 * 解题思路：
 * i * i <= n 作为循环条件即可避免调用 sqrt。
 *
 * 运行：java -cp out flow.solutions.A04_PrimeNumbers
 */
public class A04_PrimeNumbers {
    static boolean isPrime(int n) {
        if (n < 2) return false;
        for (int i = 2; (long) i * i <= n; i++) {
            if (n % i == 0) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        int count = 0;
        for (int i = 1; i <= 100; i++) {
            if (isPrime(i)) { System.out.print(i + " "); count++; }
        }
        System.out.println("\n共 " + count + " 个");
    }
}

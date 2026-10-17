package algorithms.solutions;

/**
 * 【参考答案】Q01_GcdLcm：最大公约数与最小公倍数
 *
 * 解题思路：
 * 余数递减，必然在有限步内到 0；lcm 利用 gcd * lcm = a * b。
 *
 * 运行：java -cp out algorithms.solutions.A01_GcdLcm
 */
public class A01_GcdLcm {
    static long gcd(long a, long b) {
        while (b != 0) { long t = a % b; a = b; b = t; }
        return a;
    }

    static long gcdRec(long a, long b) { return b == 0 ? a : gcdRec(b, a % b); }

    static long lcm(long a, long b) { return a / gcd(a, b) * b; }

    public static void main(String[] args) {
        System.out.println(gcd(48, 18) + " " + gcdRec(48, 18) + " " + lcm(4, 6));   // 6 6 12
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= 100; i++) if (gcd(i, 36) == 1) sb.append(i).append(' ');
        System.out.println("与 36 互质：" + sb);
    }
}

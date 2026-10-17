package algorithms.solutions;

/**
 * 【参考答案】Q03_BaseConversion：进制转换
 *
 * 解题思路：
 * 除基取余得到低位到高位；反向则逐位乘基累加（秦九韶/霍纳法）。
 *
 * 运行：java -cp out algorithms.solutions.A03_BaseConversion
 */
public class A03_BaseConversion {
    private static final String DIGITS = "0123456789ABCDEF";

    static String toBase(int n, int base) {
        if (base < 2 || base > 16) throw new IllegalArgumentException("base: " + base);
        if (n == 0) return "0";
        boolean neg = n < 0;
        long v = Math.abs((long) n);
        StringBuilder sb = new StringBuilder();
        while (v > 0) { sb.append(DIGITS.charAt((int) (v % base))); v /= base; }
        if (neg) sb.append('-');
        return sb.reverse().toString();
    }

    static int fromBase(String s, int base) {
        int result = 0;
        for (char c : s.toUpperCase().toCharArray()) {
            int d = DIGITS.indexOf(c);
            if (d < 0 || d >= base) throw new IllegalArgumentException("非法字符：" + c);
            result = result * base + d;
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(toBase(255, 16) + " " + toBase(10, 2) + " " + fromBase("FF", 16));   // FF 1010 255
        System.out.println(toBase(255, 16).equals(Integer.toHexString(255).toUpperCase()));     // true
    }
}

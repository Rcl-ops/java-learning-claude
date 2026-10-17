package strings.solutions;

/**
 * 【参考答案】Q04_CaesarCipher：凯撒密码
 *
 * 解题思路：
 * 解密即反向位移；用 floorMod 解决负数取模。
 *
 * 运行：java -cp out strings.solutions.A04_CaesarCipher
 */
public class A04_CaesarCipher {
    static String encrypt(String text, int shift) {
        StringBuilder sb = new StringBuilder();
        for (char c : text.toCharArray()) {
            if (c >= 'a' && c <= 'z') sb.append((char) ('a' + Math.floorMod(c - 'a' + shift, 26)));
            else if (c >= 'A' && c <= 'Z') sb.append((char) ('A' + Math.floorMod(c - 'A' + shift, 26)));
            else sb.append(c);
        }
        return sb.toString();
    }

    static String decrypt(String text, int shift) { return encrypt(text, -shift); }

    public static void main(String[] args) {
        String c = encrypt("Hello, World!", 3);
        System.out.println(c + " -> " + decrypt(c, 3));    // Khoor, Zruog! -> Hello, World!
    }
}

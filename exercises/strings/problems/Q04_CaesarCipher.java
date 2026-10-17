package strings.problems;

/**
 * 【字符串 · 第 4 题】凯撒密码    难度：★★☆☆☆
 *
 * 题目描述：
 * 实现 encrypt(String text, int shift) 与 decrypt。字母循环位移（大小写分别处理），其他字符保持不变，shift 可为负或大于 26。
 *
 * 示例：
 * encrypt("Hello, World!", 3) → "Khoor, Zruog!"
 *
 * 提示：
 * (c - 'a' + shift) 对 26 取模；负数用 Math.floorMod。
 *
 * 扩展思考（获取灵感）：
 * 如何破解凯撒密码（频率分析）？维吉尼亚密码怎么写？
 *
 * 参考答案：solutions/A04_CaesarCipher.java
 */
public class Q04_CaesarCipher {
    static String encrypt(String text, int shift) {
        // TODO
        return null;
    }

    static String decrypt(String text, int shift) {
        // TODO
        return null;
    }

    public static void main(String[] args) {
        String c = encrypt("Hello, World!", 3);
        System.out.println(c + " -> " + decrypt(c, 3));
    }
}

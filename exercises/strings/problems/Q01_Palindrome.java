package strings.problems;

/**
 * 【字符串 · 第 1 题】回文判断    难度：★★☆☆☆
 *
 * 题目描述：
 * 实现 isPalindrome(String s)：忽略大小写与非字母数字字符，判断是否为回文。
 *
 * 示例：
 * "A man, a plan, a canal: Panama" → true；"race a car" → false
 *
 * 提示：
 * 双指针从两端向中间，跳过不是字母/数字的字符（Character.isLetterOrDigit）。
 *
 * 扩展思考（获取灵感）：
 * 用 StringBuilder.reverse() 一行实现与双指针各有什么优缺点？如何判断"最长回文子串"？
 *
 * 参考答案：solutions/A01_Palindrome.java
 */
public class Q01_Palindrome {
    static boolean isPalindrome(String s) {
        // TODO
        return false;
    }

    public static void main(String[] args) {
        System.out.println(isPalindrome("A man, a plan, a canal: Panama"));
        System.out.println(isPalindrome("race a car"));
    }
}

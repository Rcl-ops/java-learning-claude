package strings.problems;

/**
 * 【字符串 · 第 3 题】反转单词顺序    难度：★★☆☆☆
 *
 * 题目描述：
 * 把句子中单词的顺序反转，单词内部不变，并规整多余空格。
 *
 * 示例：
 * "  the sky   is blue " → "blue is sky the"
 *
 * 提示：
 * trim + split("\\s+") 后倒序拼接，String.join(" ", ...)。
 *
 * 扩展思考（获取灵感）：
 * 不使用 split，只用一次遍历和 StringBuilder 怎么做？再试试"每个单词内部反转、顺序不变"。
 *
 * 参考答案：solutions/A03_ReverseWords.java
 */
public class Q03_ReverseWords {
    static String reverseWords(String s) {
        // TODO
        return null;
    }

    public static void main(String[] args) {
        System.out.println("[" + reverseWords("  the sky   is blue ") + "]");
    }
}

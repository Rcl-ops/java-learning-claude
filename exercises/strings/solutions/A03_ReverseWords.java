package strings.solutions;

/**
 * 【参考答案】Q03_ReverseWords：反转单词顺序
 *
 * 解题思路：
 * 按空白切分得到单词数组，倒序后用空格连接。
 *
 * 运行：java -cp out strings.solutions.A03_ReverseWords
 */
public class A03_ReverseWords {
    static String reverseWords(String s) {
        String[] words = s.trim().split("\\s+");
        StringBuilder sb = new StringBuilder();
        for (int i = words.length - 1; i >= 0; i--) {
            sb.append(words[i]);
            if (i > 0) sb.append(' ');
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println("[" + reverseWords("  the sky   is blue ") + "]");   // [blue is sky the]
    }
}

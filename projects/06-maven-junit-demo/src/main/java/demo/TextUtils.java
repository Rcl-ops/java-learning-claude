package demo;

public final class TextUtils {

    private TextUtils() { }

    /** 忽略大小写与非字母数字字符的回文判断。 */
    public static boolean isPalindrome(String s) {
        if (s == null) return false;
        StringBuilder sb = new StringBuilder();
        for (char c : s.toCharArray()) {
            if (Character.isLetterOrDigit(c)) sb.append(Character.toLowerCase(c));
        }
        return sb.toString().contentEquals(new StringBuilder(sb).reverse());
    }

    /** 统计单词数（按空白切分）。 */
    public static int wordCount(String s) {
        if (s == null || s.isBlank()) return 0;
        return s.trim().split("\\s+").length;
    }
}

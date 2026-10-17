package strings.solutions;

/**
 * 【参考答案】Q02_CountChars：统计字符类别
 *
 * 解题思路：
 * 遍历 toCharArray，用 Character 的判断方法分类计数。
 *
 * 运行：java -cp out strings.solutions.A02_CountChars
 */
public class A02_CountChars {
    public static void main(String[] args) {
        String s = "Hello 世界 123!";
        int letters = 0, digits = 0, spaces = 0, others = 0;
        for (char c : s.toCharArray()) {
            if (Character.isLetter(c)) letters++;
            else if (Character.isDigit(c)) digits++;
            else if (Character.isWhitespace(c)) spaces++;
            else others++;
        }
        System.out.printf("字母 %d，数字 %d，空白 %d，其他 %d%n", letters, digits, spaces, others);
    }
}

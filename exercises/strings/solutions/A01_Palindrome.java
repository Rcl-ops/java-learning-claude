package strings.solutions;

/**
 * 【参考答案】Q01_Palindrome：回文判断
 *
 * 解题思路：
 * 双指针 + 跳过无效字符 + 忽略大小写比较，O(n) 时间 O(1) 空间。
 *
 * 运行：java -cp out strings.solutions.A01_Palindrome
 */
public class A01_Palindrome {
    static boolean isPalindrome(String s) {
        int i = 0, j = s.length() - 1;
        while (i < j) {
            while (i < j && !Character.isLetterOrDigit(s.charAt(i))) i++;
            while (i < j && !Character.isLetterOrDigit(s.charAt(j))) j--;
            if (Character.toLowerCase(s.charAt(i)) != Character.toLowerCase(s.charAt(j))) return false;
            i++; j--;
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(isPalindrome("A man, a plan, a canal: Panama"));   // true
        System.out.println(isPalindrome("race a car"));                        // false
    }
}

package collections.problems;

import java.util.*;
/**
 * 【集合 · 第 4 题】括号匹配    难度：★★☆☆☆
 *
 * 题目描述：
 * 判断只包含 ()[]{} 的字符串是否有效：左括号必须以正确顺序闭合。
 *
 * 示例：
 * "()[]{}" → true；"([)]" → false；"{[]}" → true
 *
 * 提示：
 * 遇到左括号入栈；遇到右括号检查栈顶是否匹配；最后栈必须为空。用 ArrayDeque 作栈。
 *
 * 扩展思考（获取灵感）：
 * 扩展为表达式求值（含 + - * / 与括号）：双栈算法或调度场算法。
 *
 * 参考答案：solutions/A04_ValidParentheses.java
 */
public class Q04_ValidParentheses {
    static boolean isValid(String s) {
        // TODO
        return false;
    }

    public static void main(String[] args) {
        for (String s : new String[]{"()[]{}", "([)]", "{[]}", "(("}) System.out.println(s + " -> " + isValid(s));
    }
}

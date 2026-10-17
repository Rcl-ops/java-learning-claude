package collections.solutions;

import java.util.*;
/**
 * 【参考答案】Q04_ValidParentheses：括号匹配
 *
 * 解题思路：
 * 栈的"后进先出"天然匹配括号嵌套结构。
 *
 * 运行：java -cp out collections.solutions.A04_ValidParentheses
 */
public class A04_ValidParentheses {
    static boolean isValid(String s) {
        Deque<Character> stack = new ArrayDeque<>();
        Map<Character, Character> pairs = Map.of(')', '(', ']', '[', '}', '{');
        for (char c : s.toCharArray()) {
            if (pairs.containsValue(c)) {
                stack.push(c);
            } else if (pairs.containsKey(c)) {
                if (stack.isEmpty() || stack.pop() != pairs.get(c)) return false;
            }
        }
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        for (String s : new String[]{"()[]{}", "([)]", "{[]}", "(("}) System.out.println(s + " -> " + isValid(s));
    }
}

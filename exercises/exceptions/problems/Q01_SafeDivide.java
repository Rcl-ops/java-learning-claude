package exceptions.problems;

/**
 * 【异常与文件 · 第 1 题】安全除法与输入解析    难度：★☆☆☆☆
 *
 * 题目描述：
 * 实现 safeDivide(String a, String b)：把两个字符串解析为整数并相除。分别处理 NumberFormatException（格式错误）与 ArithmeticException（除零），返回友好的提示字符串而不是让程序崩溃；并在 finally 中打印"计算结束"。
 *
 * 示例：
 * ("10","2") → "结果 5"；("x","2") → "不是合法整数：x"；("1","0") → "除数不能为 0"
 *
 * 扩展思考（获取灵感）：
 * 哪些异常应该捕获，哪些应该让它抛出去？把返回字符串改为返回 Optional<Integer> 或抛出自定义异常的利弊？
 *
 * 参考答案：solutions/A01_SafeDivide.java
 */
public class Q01_SafeDivide {
    static String safeDivide(String a, String b) {
        // TODO
        return null;
    }

    public static void main(String[] args) {
        System.out.println(safeDivide("10", "2"));
        System.out.println(safeDivide("x", "2"));
        System.out.println(safeDivide("1", "0"));
    }
}

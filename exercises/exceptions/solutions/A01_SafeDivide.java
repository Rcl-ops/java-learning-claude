package exceptions.solutions;

/**
 * 【参考答案】Q01_SafeDivide：安全除法与输入解析
 *
 * 解题思路：
 * 多个 catch 针对不同异常给出不同提示；finally 无论如何都会执行。
 *
 * 运行：java -cp out exceptions.solutions.A01_SafeDivide
 */
public class A01_SafeDivide {
    static String safeDivide(String a, String b) {
        try {
            int x = Integer.parseInt(a.trim());
            int y = Integer.parseInt(b.trim());
            return "结果 " + (x / y);
        } catch (NumberFormatException e) {
            return "不是合法整数：" + e.getMessage();
        } catch (ArithmeticException e) {
            return "除数不能为 0";
        } finally {
            System.out.println("(计算结束)");
        }
    }

    public static void main(String[] args) {
        System.out.println(safeDivide("10", "2"));
        System.out.println(safeDivide("x", "2"));
        System.out.println(safeDivide("1", "0"));
    }
}

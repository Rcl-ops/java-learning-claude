package flow.solutions;

/**
 * 【参考答案】Q05_FizzBuzz：FizzBuzz 及变种
 *
 * 解题思路：
 * 用 StringBuilder 依次拼接，最后若为空则输出数字，这样新增规则只需加一行。
 *
 * 运行：java -cp out flow.solutions.A05_FizzBuzz
 */
public class A05_FizzBuzz {
    public static void main(String[] args) {
        for (int i = 1; i <= 30; i++) {
            StringBuilder sb = new StringBuilder();
            if (i % 3 == 0) sb.append("Fizz");
            if (i % 5 == 0) sb.append("Buzz");
            System.out.println(sb.isEmpty() ? String.valueOf(i) : sb.toString());
        }
    }
}

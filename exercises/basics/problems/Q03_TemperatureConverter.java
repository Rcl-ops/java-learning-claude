package basics.problems;

/**
 * 【基础：变量、类型、运算符 · 第 3 题】温度转换    难度：★☆☆☆☆
 *
 * 题目描述：
 * 编写两个方法：celsiusToFahrenheit(double c) 与 fahrenheitToCelsius(double f)。
 * 公式 F = C × 9 / 5 + 32。在 main 中打印 -40、0、36.6、100 摄氏度对应的华氏度，保留 1 位小数。
 *
 * 示例：
 * 0.0°C = 32.0°F
 *
 * 提示：
 * 注意整数除法陷阱：9 / 5 的结果是 1，要写 9.0 / 5。
 *
 * 扩展思考（获取灵感）：
 * -40 为什么在两种温标下相等？写一个方法验证。
 *
 * 参考答案：solutions/A03_TemperatureConverter.java
 */
public class Q03_TemperatureConverter {
    static double celsiusToFahrenheit(double c) {
        throw new UnsupportedOperationException("TODO");
    }

    static double fahrenheitToCelsius(double f) {
        throw new UnsupportedOperationException("TODO");
    }

    public static void main(String[] args) {
        double[] cs = {-40, 0, 36.6, 100};
        for (double c : cs) {
            // TODO: 打印 c 与对应华氏度
        }
    }
}

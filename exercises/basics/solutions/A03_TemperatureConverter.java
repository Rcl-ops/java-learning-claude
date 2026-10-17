package basics.solutions;

/**
 * 【参考答案】Q03_TemperatureConverter：温度转换
 *
 * 解题思路：
 * 公式里用 9.0 / 5 避免整数除法；printf 用 %.1f。
 *
 * 运行：java -cp out basics.solutions.A03_TemperatureConverter
 */
public class A03_TemperatureConverter {
    static double celsiusToFahrenheit(double c) { return c * 9.0 / 5 + 32; }

    static double fahrenheitToCelsius(double f) { return (f - 32) * 5.0 / 9; }

    public static void main(String[] args) {
        double[] cs = {-40, 0, 36.6, 100};
        for (double c : cs) {
            System.out.printf("%.1f°C = %.1f°F%n", c, celsiusToFahrenheit(c));
        }
        System.out.println(fahrenheitToCelsius(212));   // 100.0
    }
}

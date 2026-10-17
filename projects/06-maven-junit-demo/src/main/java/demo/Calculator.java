package demo;

public class Calculator {

    public int add(int a, int b) {
        return Math.addExact(a, b);          // 溢出时抛 ArithmeticException，而不是悄悄回绕
    }

    public int divide(int a, int b) {
        if (b == 0) throw new IllegalArgumentException("除数不能为 0");
        return a / b;
    }

    public long factorial(int n) {
        if (n < 0) throw new IllegalArgumentException("n 不能为负：" + n);
        long r = 1;
        for (int i = 2; i <= n; i++) r = Math.multiplyExact(r, i);
        return r;
    }
}

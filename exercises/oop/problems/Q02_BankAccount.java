package oop.problems;

import java.math.BigDecimal;
/**
 * 【面向对象 · 第 2 题】银行账户    难度：★★☆☆☆
 *
 * 题目描述：
 * 设计 BankAccount：账号、户主、余额(BigDecimal)。方法：deposit、withdraw（余额不足抛自定义的 InsufficientFundsException）、transfer(to, amount)（保证要么都成功要么都失败）。
 *
 * 提示：
 * 先校验金额 > 0；转账时先 withdraw 再 deposit。金额用 BigDecimal，不用 double。
 *
 * 扩展思考（获取灵感）：
 * 多线程并发转账会出什么问题？如何避免死锁（统一按账号大小顺序加锁）？
 *
 * 参考答案：solutions/A02_BankAccount.java
 */
public class Q02_BankAccount {
    static class InsufficientFundsException extends Exception {
        InsufficientFundsException(String msg) { super(msg); }
    }

    static class BankAccount {
        // TODO
    }

    public static void main(String[] args) throws Exception {
        // TODO
    }
}

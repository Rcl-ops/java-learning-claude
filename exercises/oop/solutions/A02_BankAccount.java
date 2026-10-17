package oop.solutions;

import java.math.BigDecimal;
/**
 * 【参考答案】Q02_BankAccount：银行账户
 *
 * 解题思路：
 * 自定义受检异常表达业务失败；所有修改余额的入口都做校验；转账封装成一个方法。
 *
 * 运行：java -cp out oop.solutions.A02_BankAccount
 */
public class A02_BankAccount {
    static class InsufficientFundsException extends Exception {
        InsufficientFundsException(String msg) { super(msg); }
    }

    static class BankAccount {
        private final String id;
        private final String owner;
        private BigDecimal balance = BigDecimal.ZERO;

        BankAccount(String id, String owner) { this.id = id; this.owner = owner; }

        BigDecimal getBalance() { return balance; }

        void deposit(BigDecimal amount) {
            requirePositive(amount);
            balance = balance.add(amount);
        }

        void withdraw(BigDecimal amount) throws InsufficientFundsException {
            requirePositive(amount);
            if (balance.compareTo(amount) < 0) {
                throw new InsufficientFundsException(owner + " 余额不足：" + balance + " < " + amount);
            }
            balance = balance.subtract(amount);
        }

        void transfer(BankAccount to, BigDecimal amount) throws InsufficientFundsException {
            this.withdraw(amount);      // 失败会抛异常，不会执行下一行
            to.deposit(amount);
        }

        private static void requirePositive(BigDecimal a) {
            if (a == null || a.signum() <= 0) throw new IllegalArgumentException("金额必须为正");
        }

        @Override public String toString() { return id + "(" + owner + "): " + balance; }
    }

    public static void main(String[] args) throws Exception {
        BankAccount a = new BankAccount("001", "Alice"), b = new BankAccount("002", "Bob");
        a.deposit(new BigDecimal("100.50"));
        a.transfer(b, new BigDecimal("40.25"));
        System.out.println(a + " | " + b);
        try {
            a.transfer(b, new BigDecimal("999"));
        } catch (InsufficientFundsException e) {
            System.out.println("失败：" + e.getMessage());
        }
    }
}

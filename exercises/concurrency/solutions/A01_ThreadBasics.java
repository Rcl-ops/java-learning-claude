package concurrency.solutions;

import java.util.concurrent.*;
/**
 * 【参考答案】Q01_ThreadBasics：创建线程的三种方式
 *
 * 解题思路：
 * start 才开新线程；join 等待结束；Callable 可以有返回值。
 *
 * 运行：java -cp out concurrency.solutions.A01_ThreadBasics
 */
public class A01_ThreadBasics {
    static class MyThread extends Thread {
        MyThread() { super("继承Thread"); }
        @Override public void run() { work(); }
    }

    static void work() {
        for (int i = 1; i <= 3; i++) System.out.println(Thread.currentThread().getName() + " : " + i);
    }

    public static void main(String[] args) throws Exception {
        Thread t1 = new MyThread();
        Thread t2 = new Thread(A01_ThreadBasics::work, "Runnable-Lambda");
        t1.start(); t2.start();

        FutureTask<String> task = new FutureTask<>(() -> {
            work();
            return "Callable 返回值";
        });
        Thread t3 = new Thread(task, "Callable");
        t3.start();

        t1.join(); t2.join();
        System.out.println(task.get());
        System.out.println("全部结束");
    }

}

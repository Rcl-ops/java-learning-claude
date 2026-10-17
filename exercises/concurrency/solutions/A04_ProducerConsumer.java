package concurrency.solutions;

import java.util.concurrent.*;
/**
 * 【参考答案】Q04_ProducerConsumer：生产者-消费者
 *
 * 解题思路：
 * BlockingQueue 封装了等待/通知，使生产者和消费者彻底解耦。
 *
 * 运行：java -cp out concurrency.solutions.A04_ProducerConsumer
 */
public class A04_ProducerConsumer {
    static final int POISON = -1;

    public static void main(String[] args) throws Exception {
        BlockingQueue<Integer> q = new ArrayBlockingQueue<>(3);
        int consumers = 2;

        Thread producer = new Thread(() -> {
            try {
                for (int i = 1; i <= 10; i++) { q.put(i); System.out.println("生产 " + i); }
                for (int i = 0; i < consumers; i++) q.put(POISON);
            } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
        }, "producer");

        Thread[] cs = new Thread[consumers];
        for (int c = 0; c < consumers; c++) {
            cs[c] = new Thread(() -> {
                try {
                    for (int v; (v = q.take()) != POISON; ) {
                        System.out.println(Thread.currentThread().getName() + " 消费 " + v);
                        Thread.sleep(20);
                    }
                } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
            }, "consumer-" + c);
            cs[c].start();
        }
        producer.start();
        producer.join();
        for (Thread t : cs) t.join();
        System.out.println("全部完成");
    }
}

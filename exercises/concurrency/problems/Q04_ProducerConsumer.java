package concurrency.problems;

import java.util.concurrent.*;
/**
 * 【多线程与并发 · 第 4 题】生产者-消费者    难度：★★★☆☆
 *
 * 题目描述：
 * 用 ArrayBlockingQueue(容量 3) 实现：1 个生产者生产 1~10，2 个消费者消费；用"毒丸"（poison pill，如 -1）通知消费者结束；打印时带线程名，观察队列满/空时的阻塞。
 *
 * 提示：
 * put/take 会阻塞；有 N 个消费者就要投放 N 个毒丸。
 *
 * 扩展思考（获取灵感）：
 * 用 wait/notify 手写阻塞队列；对比 SynchronousQueue、LinkedBlockingQueue。
 *
 * 参考答案：solutions/A04_ProducerConsumer.java
 */
public class Q04_ProducerConsumer {
    public static void main(String[] args) throws Exception {
        // TODO
    }
}

# 18 · 多线程与并发

**学习目标**：理解线程与并发问题；会创建线程、使用线程池、用 `synchronized`/原子类/并发集合保证线程安全；了解虚拟线程。

## 1. 基本概念

- **进程**：运行中的程序，拥有独立内存。**线程**：进程内的执行流，共享进程内存。
- **并发**（concurrency）：多个任务交替推进；**并行**（parallelism）：多个任务同时执行。
- 为什么用：充分利用多核、让 I/O 等待时不空转（如同时处理多个请求）。
- 代价：**线程安全问题**——多线程同时读写共享可变数据会出错。

## 2. 创建线程

```java
// ① 实现 Runnable（推荐）+ Lambda
Thread t1 = new Thread(() -> System.out.println("hello from " + Thread.currentThread().getName()));
t1.start();                 // 启动新线程；直接调用 run() 只是普通方法调用，不会并发！

// ② 继承 Thread
class MyThread extends Thread { @Override public void run() { ... } }
new MyThread().start();

// ③ 需要返回值：Callable + Future（配合线程池，见下）
```

常用方法：

```java
t1.join();                 // 等待 t1 结束
Thread.sleep(500);         // 当前线程休眠 500ms（抛 InterruptedException）
t1.interrupt();            // 请求中断（协作式）；被阻塞的线程会抛 InterruptedException
t1.setDaemon(true);        // 守护线程：JVM 在只剩守护线程时退出
Thread.currentThread().getName();
```

线程状态：`NEW → RUNNABLE ⇄ BLOCKED / WAITING / TIMED_WAITING → TERMINATED`。

## 3. 线程安全问题（竞态条件）

```java
class Counter { int n = 0; void inc() { n++; } }     // n++ 不是原子操作：读 → 加 → 写

Counter c = new Counter();
Thread a = new Thread(() -> { for (int i = 0; i < 100_000; i++) c.inc(); });
Thread b = new Thread(() -> { for (int i = 0; i < 100_000; i++) c.inc(); });
a.start(); b.start(); a.join(); b.join();
System.out.println(c.n);     // 几乎一定小于 200000，且每次不同
```

三类问题：**原子性**（一组操作要么全做要么不做）、**可见性**（一个线程的修改别的线程看不见）、**有序性**（指令重排）。

## 4. 解决方案

### synchronized（内置锁）

```java
class Counter {
    private int n;
    public synchronized void inc() { n++; }          // 锁住 this
    public synchronized int get() { return n; }
}
// 或同步块：缩小锁范围
synchronized (lock) { ... }                          // lock 是任意共享对象，如 private final Object lock = new Object();
```

同一时刻只有一个线程能持有同一把锁。`static synchronized` 锁的是 `Class` 对象。

### volatile

保证变量的**可见性**与禁止重排序，**不保证原子性**（`volatile int n; n++` 依旧不安全）。适合做"状态标志"：

```java
private volatile boolean running = true;
```

### 原子类（无锁，CAS）

```java
AtomicInteger n = new AtomicInteger();
n.incrementAndGet(); n.addAndGet(5); n.compareAndSet(5, 10);
LongAdder adder = new LongAdder();     // 高并发计数更快
adder.increment(); adder.sum();
```

### Lock（可中断、可超时、可公平）

```java
private final ReentrantLock lock = new ReentrantLock();
lock.lock();
try { ... } finally { lock.unlock(); }          // 必须在 finally 里解锁
```

## 5. 线程池（ExecutorService）

不要为每个任务 `new Thread`；用线程池**复用线程、控制并发数**。

```java
ExecutorService pool = Executors.newFixedThreadPool(4);

Future<Integer> f = pool.submit(() -> {         // Callable：有返回值
    Thread.sleep(100);
    return 42;
});
System.out.println(f.get());                     // 阻塞等待结果；get(timeout, unit) 可超时

pool.execute(() -> System.out.println("无返回值任务"));

pool.shutdown();                                 // 不再接收新任务，等已提交的执行完
pool.awaitTermination(10, TimeUnit.SECONDS);
```

Java 19+ 的 `ExecutorService` 实现了 `AutoCloseable`，可以：

```java
try (var pool = Executors.newFixedThreadPool(4)) {
    List<Future<Integer>> fs = new ArrayList<>();
    for (int i = 0; i < 10; i++) { int x = i; fs.add(pool.submit(() -> x * x)); }
    for (var f : fs) System.out.println(f.get());
}   // close() 会等待任务完成
```

> 实际生产建议直接构造 `ThreadPoolExecutor` 并明确队列大小，避免 `Executors.newFixedThreadPool` 使用无界队列导致内存堆积。

## 6. CompletableFuture：异步编排

```java
CompletableFuture<String> cf = CompletableFuture
        .supplyAsync(() -> fetchUser())                       // 异步执行
        .thenApply(user -> user.toUpperCase())                // 转换结果
        .thenCombine(CompletableFuture.supplyAsync(() -> fetchOrder()), (u, o) -> u + ":" + o)   // 合并两个异步结果
        .exceptionally(ex -> "出错：" + ex.getMessage());
System.out.println(cf.join());

CompletableFuture.allOf(cf1, cf2, cf3).join();                // 等待全部
```

## 7. 并发集合与工具

| 工具 | 用途 |
|------|------|
| `ConcurrentHashMap` | 线程安全的 Map（`merge`/`compute` 是原子的） |
| `CopyOnWriteArrayList` | 读多写少的 List |
| `BlockingQueue`（`ArrayBlockingQueue`、`LinkedBlockingQueue`） | **生产者-消费者**：`put` 满则阻塞，`take` 空则阻塞 |
| `CountDownLatch` | 等待 N 个任务完成 |
| `Semaphore` | 限制同时访问数量 |
| `ConcurrentLinkedQueue` | 无锁队列 |

生产者-消费者：

```java
BlockingQueue<Integer> q = new ArrayBlockingQueue<>(5);
Thread producer = new Thread(() -> {
    try { for (int i = 1; i <= 10; i++) q.put(i); q.put(-1); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
});
Thread consumer = new Thread(() -> {
    try { for (int v; (v = q.take()) != -1; ) System.out.println("消费 " + v); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
});
producer.start(); consumer.start();
```

## 8. 虚拟线程（Java 21 正式）

传统线程（平台线程）= 操作系统线程，开销大，一般只能开几千个。**虚拟线程**由 JVM 调度，极轻量，可开**百万级**，非常适合"大量阻塞 I/O"的场景（HTTP 请求、数据库调用）。

```java
Thread.startVirtualThread(() -> System.out.println("virtual"));

try (var exec = Executors.newVirtualThreadPerTaskExecutor()) {      // 每个任务一个虚拟线程，无需池化
    for (int i = 0; i < 10_000; i++) {
        exec.submit(() -> { Thread.sleep(Duration.ofSeconds(1)); return 1; });
    }
}   // 1 万个任务约 1 秒完成
```

使用提示：不需要池化虚拟线程；它们适合 I/O 密集，不适合 CPU 密集；避免在 `synchronized` 内长时间阻塞（新版本已大幅改善）。

## 9. 死锁与最佳实践

死锁 = 线程 A 持有锁 1 等待锁 2，线程 B 持有锁 2 等待锁 1。预防：**统一加锁顺序**、缩小锁范围、用 `tryLock` 带超时。

最佳实践清单：
1. 能不共享就不共享（局部变量、不可变对象、`ThreadLocal`）。
2. 共享时优先用并发容器/原子类，其次才是 `synchronized`/`Lock`。
3. 用线程池/虚拟线程，别到处 `new Thread`。
4. 正确处理 `InterruptedException`（恢复中断标志或向上抛）。
5. 为线程起有意义的名字，便于排查问题。
6. 多线程 bug 难以复现——**设计时就要保证正确**，不要靠"多测几次"。

## 小结

- `start()` 才会开新线程；共享可变数据必须同步。
- `synchronized`、`Atomic*`、并发集合、`BlockingQueue` 是四件套。
- 线程池 / `CompletableFuture` 做任务编排；I/O 密集用虚拟线程。

**练习**：`exercises/concurrency`

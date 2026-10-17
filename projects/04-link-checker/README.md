# 项目 4：并发链接检查器（Link Checker）

**难度**：★★★☆☆　**依赖**：无（纯 JDK 21+）　**对应章节**：13、15、16、18（虚拟线程）、22（HttpClient）

读入一个链接列表，**并发**检查每个地址是否可访问，输出状态码与耗时。可以用来检查博客/文档里的死链、监控自己的服务是否存活。1000 个链接几秒钟就能查完——因为它用的是 **虚拟线程**。

## 运行

```bash
cd projects/04-link-checker
javac -encoding UTF-8 -d out src/linkcheck/*.java

# 先启动项目 3 的服务当作"被检查对象"（另开一个终端）：
#   java -cp ../03-mini-rest-server/out notes.NotesServer 9090

java -cp out linkcheck.Main urls-sample.txt
echo $?          # 0 = 全部正常，1 = 有失败（Windows: echo %ERRORLEVEL%）
```

输出示例：

```
检查 8 个链接……
OK   200      12 ms  http://localhost:9090/
OK   200       3 ms  http://localhost:9090/health
OK   200       4 ms  http://localhost:9090/notes
FAIL 404       3 ms  http://localhost:9090/notes/12345
OK   200     180 ms  https://dev.java
FAIL ConnectException  2 ms  http://localhost:9/
FAIL 地址格式错误 / 非 http(s) 链接 ...
---- 完成：5 正常，3 失败，总耗时 190 ms ----
```

## 代码结构与知识点

| 文件 | 知识点 |
|------|--------|
| `CheckResult.java` | `record` + 派生方法 `ok()` |
| `LinkChecker.java` | `HttpClient`、HEAD→GET 回退、**异常分类处理**、`InterruptedException` 的正确处理、`ExecutorService` + `Future`、`newVirtualThreadPerTaskExecutor` + try-with-resources |
| `Main.java` | Stream 清洗输入（`strip/filter/distinct`）、退出码约定 |

关键设计：每个检查任务都会**等待网络**（I/O 密集），用虚拟线程时每个任务一个线程，代码写成最直观的阻塞风格，却能同时处理成千上万个连接。若用传统线程池，需要调大池或改写成异步回调。

## 练习与扩展

1. 加 `--concurrency N` 参数，用 `Semaphore` 限制同时请求数（避免对同一站点发起过多请求——**礼貌地使用网络**）。
2. 失败自动重试 2 次（指数退避）。
3. 输出 CSV / Markdown 报告到文件。
4. 从一个网页里抓取所有 `<a href>` 并检查（正则或 jsoup 库），做成真正的"死链检测器"。
5. 做成定时任务：`ScheduledExecutorService` 每分钟检查一次，状态变化时打印告警。
6. 对比实验：把虚拟线程换成 `newFixedThreadPool(10)`，检查 500 个慢链接的耗时差别。

# 24 · 下一步学什么

完成前 23 章 + 练习 + 至少两个项目后，你已经具备 Java 基础。以下是几个方向。

## 方向 A：服务端开发（就业主流）

| 阶段 | 内容 |
|------|------|
| 1 | SQL 基础 + **JDBC**（Java 连数据库）+ SQLite/MySQL/PostgreSQL |
| 2 | **Spring Boot**：REST 控制器、依赖注入、配置、Spring Data JPA |
| 3 | 测试（JUnit 5 + Mockito）、日志、Docker 部署 |
| 4 | 缓存（Redis）、消息队列、微服务、可观测性 |

## 方向 B：Minecraft 模组开发（本教程的彩蛋）

1. 完成 `projects/05-neoforge-hello-mod`，让游戏里出现你自己的物品。
2. 阅读 [NeoForge 官方文档](https://docs.neoforged.net/)（选对你的 Minecraft 版本），依次学习：**物品 → 方块 → 创造模式标签页 → 配方/战利品表 → 方块实体 → 实体 → 网络**。
3. 学习数据生成（Datagen）：用代码生成 JSON 资源，避免手写。
4. 阅读优秀开源模组的源码；加入 NeoForged Discord 社区提问。
5. Java 基础要点在模组里的对应：**注解**（`@Mod`、`@SubscribeEvent`）、**泛型**（`DeferredRegister<T>`）、**Lambda/Supplier**（延迟注册）、**事件总线**（观察者模式）、**record + Codec**（数据序列化）。

> 模组 API 随 Minecraft 版本更新较频繁（例如 1.21.x → 26.1 有较大变化），遇到不一致时**以对应版本的官方文档和 Porting Primer 为准**。

## 方向 C：算法与数据结构

- 巩固：数组、链表、栈队列、哈希、树、图、排序、二分、递归/回溯、动态规划。
- 练习平台：LeetCode、AtCoder、洛谷、Codeforces。
- 用 Java 写出每个数据结构的最简实现（`exercises/algorithms` 有开头）。

## 方向 D：深入 Java 本身

- **JVM**：内存结构、类加载、垃圾回收（G1/ZGC）、JIT；工具：`jcmd`、`jstack`、`jmap`、JFR、VisualVM。
- **并发进阶**：Java 内存模型、AQS、并发包源码、虚拟线程与结构化并发。
- **性能**：JMH 基准测试、逃逸分析。
- **模块系统**（JPMS）、`jlink`/`jpackage`（打包桌面应用）、GraalVM Native Image。

## 方向 E：桌面 / 工具

- JavaFX 做图形界面；Picocli 写命令行工具；`jpackage` 生成安装包。

## 推荐资料

| 类型 | 资源 |
|------|------|
| 官方 | [dev.java](https://dev.java)（官方教程）、Oracle Java Tutorials、JDK API 文档、JLS 语言规范 |
| 书籍 | 《Effective Java》（必读，学完基础再读）、《Java 核心技术》、《Head First Java》（入门友好）、《Java 并发编程实战》、《深入理解 Java 虚拟机》 |
| 练习 | 本项目 `exercises/`；Exercism 的 Java 路线；LeetCode |
| 社区 | Stack Overflow、Reddit r/learnjava、NeoForged Discord（模组） |

## 给自己的"毕业作品"建议

任选一个，独立完成，**写 README 与测试**，上传 GitHub：
1. 个人记账本（命令行 → 加文件存储 → 加统计图表）。
2. 迷你博客 REST API（Spring Boot + SQLite）。
3. 自己设计的 Minecraft 模组（新物品 + 方块 + 配方 + 一个小玩法）。
4. 终端版文字冒险/贪吃蛇/扫雷游戏（练习面向对象设计）。

> 学编程最重要的是**持续写代码**。每天 30 分钟，好过周末突击 5 小时。祝你学习愉快！

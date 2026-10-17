# 项目 1：命令行待办清单（Todo CLI）

**难度**：★★☆☆☆　**依赖**：无（纯 JDK 21+）　**对应章节**：05、07、09、11（record）、14、16

一个真正能用的小工具：在终端里添加、查看、完成、删除待办，数据保存到文本文件，下次启动自动读取。

## 运行

```bash
cd projects/01-todo-cli
javac -encoding UTF-8 -d out src/todo/*.java
java -cp out todo.TodoApp            # 数据默认保存在当前目录的 todo.tsv
java -cp out todo.TodoApp my.tsv     # 也可以指定文件
```

示例会话：

```
> add 买牛奶
已添加：[ ] 1. 买牛奶
> add 写 Java 作业
已添加：[ ] 2. 写 Java 作业
> done 1
已完成
> list
[x] 1. 买牛奶
[ ] 2. 写 Java 作业
共 2 条，已完成 1 条
> quit
```

## 代码结构与学到的知识点

| 文件 | 职责 | 知识点 |
|------|------|--------|
| `Task.java` | 数据模型 | `record`、紧凑构造器校验、不可变对象 |
| `TaskStore.java` | 存储与业务逻辑 | `ArrayList`、`Optional`、Stream、`Files` 读写、封装（返回 `List.copyOf`） |
| `TodoApp.java` | 命令行交互 | `Scanner` 循环、`switch` 箭头语法、文本块、异常处理 |

三层分工（模型 / 存储 / 界面）是后端项目的常见结构：以后把"命令行"换成"网页"，只需要重写 `TodoApp`。

## 练习与扩展（按难度）

1. 增加 `edit <id> <新内容>` 命令。
2. 增加优先级（枚举 `Priority { LOW, MEDIUM, HIGH }`）和 `list high` 过滤。
3. 增加截止日期 `LocalDate`，`list overdue` 显示过期事项。
4. 把存储格式改为 JSON（引入 Jackson，体验 Maven/Gradle 依赖管理）。
5. 给 `TaskStore` 写 JUnit 测试（参考 `projects/06-maven-junit-demo`）。
6. 把 `TaskStore` 抽成接口 `Repository`，再实现一个 `InMemoryStore` 用于测试。

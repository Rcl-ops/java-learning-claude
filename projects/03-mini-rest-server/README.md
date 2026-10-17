# 项目 3：迷你 REST 服务（笔记 API + 网页）

**难度**：★★★☆☆　**依赖**：无（纯 JDK 21+，使用 `com.sun.net.httpserver`）　**对应章节**：11、13、14、18、22

用 JDK 自带的 `HttpServer` 写一个真正可访问的 Web 服务：浏览器里有网页界面，命令行里有 REST API。理解了它，再学 Spring Boot 时你会明白框架替你做了什么。

## 运行

```bash
cd projects/03-mini-rest-server
javac -encoding UTF-8 -d out src/notes/*.java
java -cp out notes.NotesServer          # 默认端口 8080；或 java -cp out notes.NotesServer 9000
```

打开浏览器访问 <http://localhost:8080/>，可以添加、删除笔记。

## 用命令行调用 API

```bash
curl -i -X POST localhost:8080/notes -d '{"text":"买牛奶"}'     # 201 Created
curl localhost:8080/notes                                        # 列表
curl localhost:8080/notes/1                                      # 单条
curl -i -X DELETE localhost:8080/notes/1                         # 204 No Content
curl -i localhost:8080/notes/999                                 # 404
```

| 方法 | 路径 | 说明 | 成功状态码 |
|------|------|------|-----------|
| GET | `/` | 网页界面 | 200 |
| GET | `/notes` | 全部笔记 | 200 |
| GET | `/notes/{id}` | 单条笔记 | 200 / 404 |
| POST | `/notes` | 新建，body：`{"text":"..."}` 或纯文本 | 201 |
| DELETE | `/notes/{id}` | 删除 | 204 / 404 |
| GET | `/health` | 健康检查 | 200 |

## 代码结构与知识点

| 文件 | 知识点 |
|------|--------|
| `Note.java` | `record`、`Instant`、手写 JSON 序列化 |
| `Json.java` | 字符串转义（防注入）、正则、`Optional`、工具类（私有构造器） |
| `NoteStore.java` | `ConcurrentHashMap` + `AtomicInteger`：**多线程安全**的内存存储 |
| `NotesServer.java` | HTTP 方法/状态码、Lambda 作处理器、`switch`、异常→HTTP 状态码映射、**虚拟线程**执行器、文本块内嵌网页 |

安全小细节（值得学习的习惯）：只监听 `127.0.0.1`；限制请求体大小与文本长度；网页用 `textContent` 而不是 `innerHTML` 避免 XSS；JSON 输出统一转义。

## 局限（刻意保持简单）

- 数据只存在内存里，重启就没了；没有认证；JSON 解析是简化版（真实项目用 Jackson）。

## 练习与扩展

1. 新增 `PUT /notes/{id}` 修改笔记内容。
2. 增加 `GET /notes?q=关键字` 搜索。
3. 把 `NoteStore` 持久化到文件（参考项目 1），启动时加载。
4. 引入 Jackson 替换 `Json.java`（Maven 项目，见项目 6 的 pom 写法）。
5. 用 Spring Boot 重写同样的 API，对比代码量与功能（校验、日志、测试支持）。
6. 用项目 4（链接检查器）去检查这个服务的各个地址。

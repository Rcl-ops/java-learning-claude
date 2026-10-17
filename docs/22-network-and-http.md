# 22 · 网络编程与 HTTP

**学习目标**：理解 HTTP 基本概念；会用 JDK 自带的 `HttpClient` 发请求、用 `HttpServer` 写最简服务；了解 Socket 与 JSON。

## 1. HTTP 速览

```
客户端                              服务器
GET /notes?id=1 HTTP/1.1  ───────▶
Host: localhost:8080
Accept: application/json
                           ◀───────  HTTP/1.1 200 OK
                                     Content-Type: application/json
                                     {"id":1,"text":"买牛奶"}
```

- 请求 = **方法 + URL + 头 + 体**；响应 = **状态码 + 头 + 体**。
- 方法：`GET` 查询、`POST` 新建、`PUT` 整体更新、`PATCH` 部分更新、`DELETE` 删除。
- 状态码：`2xx` 成功（200 OK、201 Created、204 No Content）；`3xx` 重定向；`4xx` 客户端错误（400、401、403、404）；`5xx` 服务器错误（500）。
- **REST** 风格：用 URL 表示资源（`/notes/3`），用 HTTP 方法表示动作。

## 2. HttpClient（Java 11+）

```java
import java.net.URI;
import java.net.http.*;
import java.time.Duration;

HttpClient client = HttpClient.newBuilder()
        .connectTimeout(Duration.ofSeconds(5))
        .followRedirects(HttpClient.Redirect.NORMAL)
        .build();                                       // 客户端可复用，线程安全

HttpRequest req = HttpRequest.newBuilder(URI.create("https://example.com/api"))
        .timeout(Duration.ofSeconds(10))
        .header("Accept", "application/json")
        .GET()
        .build();

HttpResponse<String> resp = client.send(req, HttpResponse.BodyHandlers.ofString());   // 同步
System.out.println(resp.statusCode());
System.out.println(resp.body());

// POST JSON
HttpRequest post = HttpRequest.newBuilder(URI.create("http://localhost:8080/notes"))
        .header("Content-Type", "application/json")
        .POST(HttpRequest.BodyPublishers.ofString("{\"text\":\"hello\"}"))
        .build();

// 异步
client.sendAsync(req, HttpResponse.BodyHandlers.ofString())
      .thenApply(HttpResponse::body)
      .thenAccept(System.out::println)
      .join();
```

常见异常：`IOException`（连接失败）、`HttpTimeoutException`、`InterruptedException`（需要处理）。**HTTP 4xx/5xx 不会抛异常**，要自己检查 `statusCode()`。

## 3. JDK 自带的 HttpServer（教学与小工具）

```java
import com.sun.net.httpserver.HttpServer;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;

HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
server.createContext("/hello", exchange -> {
    byte[] body = "你好，HTTP".getBytes(StandardCharsets.UTF_8);
    exchange.getResponseHeaders().add("Content-Type", "text/plain; charset=utf-8");
    exchange.sendResponseHeaders(200, body.length);
    try (var os = exchange.getResponseBody()) { os.write(body); }
});
server.start();      // 浏览器访问 http://localhost:8080/hello
```

完整示例：`projects/03-mini-rest-server`。真实项目用 **Spring Boot / Javalin / Vert.x** 等框架。

## 4. JSON

JSON 是服务间交换数据的事实标准：

```json
{"id": 1, "text": "买牛奶", "done": false, "tags": ["家", "紧急"]}
```

Java 标准库**不包含**通用 JSON 解析器。实际开发使用：
- **Jackson**（最主流）：`new ObjectMapper().writeValueAsString(obj)` / `readValue(json, Note.class)`，对 `record` 支持良好。
- **Gson**：API 更简单。

为保持"零依赖"，教学项目里的 JSON 是手写拼接/正则提取，**仅用于演示，生产环境请使用库**（手写 JSON 极易出安全与转义问题）。

## 5. Socket：TCP 底层通信

```java
// 回声服务器
try (ServerSocket server = new ServerSocket(9000)) {
    while (true) {
        Socket s = server.accept();                       // 阻塞等待连接
        Thread.startVirtualThread(() -> {                 // 每个连接一个虚拟线程
            try (s;
                 var in = new BufferedReader(new InputStreamReader(s.getInputStream(), StandardCharsets.UTF_8));
                 var out = new PrintWriter(s.getOutputStream(), true, StandardCharsets.UTF_8)) {
                String line;
                while ((line = in.readLine()) != null) out.println("echo: " + line);
            } catch (IOException e) { e.printStackTrace(); }
        });
    }
}
// 客户端：new Socket("localhost", 9000)，用 getInputStream/getOutputStream 读写
```

HTTP、数据库协议、Minecraft 的网络通信，底层都是 TCP Socket 之上的应用协议。

## 6. URL 编码与安全提醒

```java
URLEncoder.encode("a b&c", StandardCharsets.UTF_8);   // a+b%26c
```

- 不要信任任何来自网络的输入：**校验、转义、限制大小**。
- 不要在代码里写死密码/Token；用环境变量或配置文件，并加入 `.gitignore`。
- HTTPS 是默认选择；自签证书仅限本地测试。

## 小结

- HTTP：方法 + URL + 状态码；REST 用 URL 表示资源。
- `HttpClient` 发请求，`HttpServer` 写迷你服务，JSON 用 Jackson/Gson。

**项目**：`projects/03-mini-rest-server`、`projects/04-link-checker`

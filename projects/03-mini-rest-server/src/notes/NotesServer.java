package notes;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.concurrent.Executors;

/**
 * 迷你 REST 服务（只依赖 JDK）。
 *   GET    /            网页界面
 *   GET    /notes       所有笔记
 *   GET    /notes/{id}  单条笔记
 *   POST   /notes       新建（body：{"text":"..."} 或纯文本）
 *   DELETE /notes/{id}  删除
 *   GET    /health      健康检查
 * 运行：java -cp out notes.NotesServer [端口，默认 8080]
 */
public class NotesServer {

    private static final int MAX_BODY = 10_000;          // 限制请求体大小，防止被恶意大请求拖垮
    private final NoteStore store = new NoteStore();

    public static void main(String[] args) throws IOException {
        int port = args.length > 0 ? Integer.parseInt(args[0]) : 8080;
        HttpServer server = new NotesServer().create(port);
        server.start();
        System.out.println("服务已启动： http://localhost:" + port + "/   （Ctrl+C 停止）");
    }

    HttpServer create(int port) throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress("127.0.0.1", port), 0);   // 只监听本机，更安全
        server.createContext("/", this::handleRoot);
        server.createContext("/notes", this::handleNotes);
        server.createContext("/health", ex -> send(ex, 200, "text/plain; charset=utf-8", "ok"));
        server.setExecutor(Executors.newVirtualThreadPerTaskExecutor());                     // 每个请求一个虚拟线程
        return server;
    }

    // ---------------- 处理器 ----------------

    private void handleRoot(HttpExchange ex) throws IOException {
        if (!ex.getRequestURI().getPath().equals("/")) {
            send(ex, 404, "text/plain; charset=utf-8", "Not Found");
            return;
        }
        send(ex, 200, "text/html; charset=utf-8", PAGE);
    }

    private void handleNotes(HttpExchange ex) throws IOException {
        try {
            String method = ex.getRequestMethod();
            String path = ex.getRequestURI().getPath();                  // /notes 或 /notes/3
            String rest = path.substring("/notes".length());             // "" 或 "/3"

            if (rest.isEmpty() || rest.equals("/")) {
                switch (method) {
                    case "GET" -> sendJson(ex, 200, Json.array(store.list().stream().map(Note::toJson).toList()));
                    case "POST" -> createNote(ex);
                    default -> sendJson(ex, 405, error("不支持的方法：" + method));
                }
                return;
            }

            int id = parseId(rest.substring(1));
            switch (method) {
                case "GET" -> store.get(id).ifPresentOrElse(
                        n -> safeSend(ex, 200, n.toJson()),
                        () -> safeSend(ex, 404, error("找不到笔记 " + id)));
                case "DELETE" -> {
                    if (store.delete(id)) ex.sendResponseHeaders(204, -1);   // 204：成功且无内容
                    else sendJson(ex, 404, error("找不到笔记 " + id));
                }
                default -> sendJson(ex, 405, error("不支持的方法：" + method));
            }
        } catch (IllegalArgumentException e) {
            sendJson(ex, 400, error(e.getMessage()));
        } catch (RuntimeException e) {
            e.printStackTrace();
            sendJson(ex, 500, error("服务器内部错误"));
        } finally {
            ex.close();
        }
    }

    private void createNote(HttpExchange ex) throws IOException {
        String body = readBody(ex.getRequestBody());
        String text = Json.extractText(body).orElseThrow(() -> new IllegalArgumentException("缺少 text"));
        if (text.length() > 500) throw new IllegalArgumentException("text 过长（最多 500 字）");
        Note n = store.add(text);
        ex.getResponseHeaders().add("Location", "/notes/" + n.id());
        sendJson(ex, 201, n.toJson());
    }

    // ---------------- 工具方法 ----------------

    private static int parseId(String s) {
        try {
            return Integer.parseInt(s);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("id 必须是整数：" + s);
        }
    }

    private static String readBody(InputStream in) throws IOException {
        byte[] data = in.readNBytes(MAX_BODY + 1);
        if (data.length > MAX_BODY) throw new IllegalArgumentException("请求体过大");
        return new String(data, StandardCharsets.UTF_8);
    }

    private static String error(String msg) {
        return "{\"error\":\"" + Json.escape(msg) + "\"}";
    }

    private static void sendJson(HttpExchange ex, int status, String json) throws IOException {
        send(ex, status, "application/json; charset=utf-8", json);
    }

    /** lambda 里不能抛受检异常，所以包一层。 */
    private static void safeSend(HttpExchange ex, int status, String json) {
        try {
            sendJson(ex, status, json);
        } catch (IOException e) {
            throw new java.io.UncheckedIOException(e);
        }
    }

    private static void send(HttpExchange ex, int status, String contentType, String body) throws IOException {
        byte[] bytes = body.getBytes(StandardCharsets.UTF_8);
        ex.getResponseHeaders().set("Content-Type", contentType);
        ex.sendResponseHeaders(status, bytes.length);
        try (var os = ex.getResponseBody()) {
            os.write(bytes);
        }
    }

    // ---------------- 内置网页 ----------------

    private static final String PAGE = """
            <!doctype html>
            <html lang="zh">
            <head>
              <meta charset="utf-8">
              <meta name="viewport" content="width=device-width, initial-scale=1">
              <title>迷你笔记</title>
              <style>
                body { font-family: sans-serif; max-width: 560px; margin: 2rem auto; padding: 0 1rem; }
                li { margin: .4rem 0; } button { margin-left: .5rem; }
                input { width: 70%; padding: .4rem; }
              </style>
            </head>
            <body>
              <h1>迷你笔记</h1>
              <input id="t" placeholder="写点什么……" maxlength="200">
              <button onclick="add()">添加</button>
              <ul id="list"></ul>
              <script>
                async function load() {
                  const notes = await (await fetch('/notes')).json();
                  const ul = document.getElementById('list');
                  ul.textContent = '';
                  for (const n of notes) {
                    const li = document.createElement('li');
                    li.textContent = '#' + n.id + ' ' + n.text;       // textContent：自动转义，防 XSS
                    const b = document.createElement('button');
                    b.textContent = '删除';
                    b.onclick = async () => { await fetch('/notes/' + n.id, {method: 'DELETE'}); load(); };
                    li.appendChild(b);
                    ul.appendChild(li);
                  }
                }
                async function add() {
                  const t = document.getElementById('t');
                  if (!t.value.trim()) return;
                  await fetch('/notes', {method: 'POST', body: JSON.stringify({text: t.value})});
                  t.value = '';
                  load();
                }
                load();
              </script>
            </body>
            </html>
            """;
}

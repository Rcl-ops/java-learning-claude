package linkcheck;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/** 并发检查一批 URL 是否可访问。每个 URL 一个虚拟线程。 */
public class LinkChecker {

    private final HttpClient client = HttpClient.newBuilder()
            .connectTimeout(Duration.ofSeconds(5))
            .followRedirects(HttpClient.Redirect.NORMAL)
            .build();                                                   // HttpClient 线程安全，可共享

    /** 检查单个 URL：先 HEAD（只要响应头，省流量）；服务器不支持 HEAD（405/501）则退回 GET。 */
    public CheckResult check(String url) {
        long start = System.nanoTime();
        try {
            URI uri = URI.create(url);
            if (uri.getScheme() == null || !uri.getScheme().startsWith("http")) {
                return new CheckResult(url, -1, 0, "非 http(s) 链接");
            }
            int status = send(uri, "HEAD");
            if (status == 405 || status == 501) status = send(uri, "GET");
            return new CheckResult(url, status, elapsed(start), null);
        } catch (IllegalArgumentException e) {
            return new CheckResult(url, -1, 0, "地址格式错误");
        } catch (IOException e) {
            return new CheckResult(url, -1, elapsed(start), e.getClass().getSimpleName());
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();                          // 恢复中断标志
            return new CheckResult(url, -1, elapsed(start), "被中断");
        }
    }

    private int send(URI uri, String method) throws IOException, InterruptedException {
        HttpRequest req = HttpRequest.newBuilder(uri)
                .timeout(Duration.ofSeconds(10))
                .header("User-Agent", "link-checker-demo/1.0")
                .method(method, HttpRequest.BodyPublishers.noBody())
                .build();
        return client.send(req, HttpResponse.BodyHandlers.discarding()).statusCode();
    }

    private static long elapsed(long startNanos) {
        return (System.nanoTime() - startNanos) / 1_000_000;
    }

    /** 并发检查全部链接，按输入顺序返回结果。 */
    public List<CheckResult> checkAll(List<String> urls) throws InterruptedException, ExecutionException {
        try (ExecutorService pool = Executors.newVirtualThreadPerTaskExecutor()) {   // 虚拟线程：I/O 等待时几乎零成本
            List<Future<CheckResult>> futures = new ArrayList<>();
            for (String u : urls) futures.add(pool.submit(() -> check(u)));
            List<CheckResult> results = new ArrayList<>();
            for (Future<CheckResult> f : futures) results.add(f.get());
            return results;
        }
    }
}

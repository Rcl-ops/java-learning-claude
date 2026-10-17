package linkcheck;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * 用法：java -cp out linkcheck.Main <urls.txt>
 * urls.txt：每行一个地址，空行与以 # 开头的行会被忽略。
 * 退出码：全部正常 0；有失败 1；用法错误 2（方便放进脚本/CI 中使用）。
 */
public class Main {

    public static void main(String[] args) throws IOException, InterruptedException, ExecutionException {
        if (args.length != 1) {
            System.err.println("用法：java -cp out linkcheck.Main <urls.txt>");
            System.exit(2);
        }
        List<String> urls = Files.readAllLines(Path.of(args[0])).stream()
                .map(String::strip)
                .filter(l -> !l.isEmpty() && !l.startsWith("#"))
                .distinct()
                .toList();

        System.out.println("检查 " + urls.size() + " 个链接……");
        long t0 = System.nanoTime();
        List<CheckResult> results = new LinkChecker().checkAll(urls);
        results.forEach(System.out::println);

        long bad = results.stream().filter(r -> !r.ok()).count();
        long ms = (System.nanoTime() - t0) / 1_000_000;
        System.out.printf("---- 完成：%d 正常，%d 失败，总耗时 %d ms ----%n", results.size() - bad, bad, ms);
        System.exit(bad == 0 ? 0 : 1);
    }
}

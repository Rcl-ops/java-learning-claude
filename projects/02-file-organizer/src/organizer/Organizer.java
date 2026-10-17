package organizer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.EnumMap;
import java.util.Map;
import java.util.stream.Stream;

/**
 * 按扩展名把一个目录里的文件整理到分类子目录。
 * 用法：java -cp out organizer.Organizer <目录> [--dry-run]
 * --dry-run 只显示将要做什么，不真正移动（强烈建议第一次先这样运行）。
 */
public class Organizer {

    private final Path root;
    private final boolean dryRun;
    private final Map<FileCategory, Integer> stats = new EnumMap<>(FileCategory.class);

    public Organizer(Path root, boolean dryRun) {
        this.root = root;
        this.dryRun = dryRun;
    }

    public static void main(String[] args) throws IOException {
        if (args.length == 0) {
            System.out.println("用法：java -cp out organizer.Organizer <目录> [--dry-run]");
            return;
        }
        Path dir = Path.of(args[0]);
        boolean dry = args.length > 1 && args[1].equals("--dry-run");
        if (!Files.isDirectory(dir)) {
            System.out.println("不是有效目录：" + dir);
            return;
        }
        new Organizer(dir, dry).run();
    }

    public void run() throws IOException {
        System.out.println((dryRun ? "[预演] " : "") + "整理目录：" + root.toAbsolutePath());
        try (Stream<Path> files = Files.list(root)) {                 // 只处理第一层，不递归
            // 小技巧：lambda 里不能直接抛受检异常 IOException，所以把 Stream 转成 Iterable 用增强 for 遍历
            for (Path f : (Iterable<Path>) files.sorted()::iterator) {
                if (!Files.isRegularFile(f)) continue;                // 跳过子目录
                String name = f.getFileName().toString();
                if (name.startsWith(".")) continue;                   // 跳过隐藏文件
                moveToCategory(f, FileCategory.of(name));
            }
        }
        printSummary();
    }

    private void moveToCategory(Path file, FileCategory category) throws IOException {
        Path targetDir = root.resolve(category.folderName());
        Path target = uniqueTarget(targetDir, file.getFileName().toString());
        System.out.printf("  %-30s -> %s/%s%n", file.getFileName(), category.folderName(), target.getFileName());
        if (!dryRun) {
            Files.createDirectories(targetDir);
            Files.move(file, target);                                 // 不覆盖：目标名已保证唯一
        }
        stats.merge(category, 1, Integer::sum);
    }

    /** 如果目标已有同名文件，生成 "name (1).ext"、"name (2).ext" …… */
    static Path uniqueTarget(Path dir, String fileName) {
        Path candidate = dir.resolve(fileName);
        if (!Files.exists(candidate)) return candidate;
        int dot = fileName.lastIndexOf('.');
        String base = dot > 0 ? fileName.substring(0, dot) : fileName;
        String ext = dot > 0 ? fileName.substring(dot) : "";
        for (int i = 1; ; i++) {
            candidate = dir.resolve(base + " (" + i + ")" + ext);
            if (!Files.exists(candidate)) return candidate;
        }
    }

    private void printSummary() {
        int total = stats.values().stream().mapToInt(Integer::intValue).sum();
        System.out.println("---- 汇总 ----");
        stats.forEach((c, n) -> System.out.printf("%-10s %d%n", c.folderName(), n));
        System.out.println((dryRun ? "预演完成，" : "完成，") + "共处理 " + total + " 个文件" + (dryRun ? "（未移动）" : ""));
    }
}

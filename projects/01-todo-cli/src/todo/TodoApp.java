package todo;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Scanner;

/** 命令行待办清单。运行：java -cp out todo.TodoApp [数据文件路径] */
public class TodoApp {

    private static final String HELP = """
            命令：
              add <内容>   添加待办
              list         查看全部
              done <id>    标记完成
              rm <id>      删除
              clear        清除所有已完成
              help         显示帮助
              quit         保存并退出
            """;

    public static void main(String[] args) throws IOException {
        Path file = Path.of(args.length > 0 ? args[0] : "todo.tsv");
        TaskStore store = new TaskStore(file);
        store.load();
        System.out.println("待办清单（数据文件：" + file.toAbsolutePath() + "）  输入 help 查看命令");

        Scanner in = new Scanner(System.in);
        while (true) {
            System.out.print("> ");
            if (!in.hasNextLine()) break;                      // 输入结束（Ctrl+D / 管道结束）
            String line = in.nextLine().strip();
            if (line.isEmpty()) continue;

            String[] parts = line.split("\\s+", 2);            // 命令 + 其余参数
            String cmd = parts[0].toLowerCase();
            String arg = parts.length > 1 ? parts[1] : "";

            try {
                boolean quit = handle(store, cmd, arg);
                if (quit) break;
            } catch (IllegalArgumentException e) {             // 包括 NumberFormatException
                System.out.println("输入有误：" + e.getMessage());
            }
        }
        store.save();
        System.out.println("已保存，再见！");
    }

    /** @return 是否退出 */
    private static boolean handle(TaskStore store, String cmd, String arg) {
        switch (cmd) {
            case "add" -> System.out.println("已添加：" + store.add(arg));
            case "list" -> printAll(store.all());
            case "done" -> System.out.println(store.markDone(parseId(arg)) ? "已完成" : "找不到该编号");
            case "rm" -> System.out.println(store.remove(parseId(arg)) ? "已删除" : "找不到该编号");
            case "clear" -> System.out.println("已清除 " + store.clearDone() + " 条已完成事项");
            case "help" -> System.out.print(HELP);
            case "quit", "exit" -> { return true; }
            default -> System.out.println("未知命令：" + cmd + "（输入 help 查看帮助）");
        }
        return false;
    }

    private static int parseId(String s) {
        try {
            return Integer.parseInt(s.trim());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("需要一个数字编号，而不是 \"" + s + "\"");
        }
    }

    private static void printAll(List<Task> tasks) {
        if (tasks.isEmpty()) {
            System.out.println("(空)");
            return;
        }
        tasks.forEach(System.out::println);
        long done = tasks.stream().filter(Task::done).count();
        System.out.printf("共 %d 条，已完成 %d 条%n", tasks.size(), done);
    }
}

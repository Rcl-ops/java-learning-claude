package todo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/** 负责保存、读取待办事项（简单的文本文件：id \t done \t text）。 */
public class TaskStore {

    private final Path file;
    private final List<Task> tasks = new ArrayList<>();
    private int nextId = 1;

    public TaskStore(Path file) {
        this.file = file;
    }

    // ---------- 持久化 ----------

    public void load() throws IOException {
        tasks.clear();
        nextId = 1;
        if (!Files.exists(file)) return;                 // 第一次运行：还没有文件
        for (String line : Files.readAllLines(file)) {
            if (line.isBlank()) continue;
            String[] parts = line.split("\t", 3);        // 最多切成 3 段，text 里即使有 tab 也不会丢
            if (parts.length < 3) continue;              // 容错：跳过坏行
            int id = Integer.parseInt(parts[0]);
            tasks.add(new Task(id, parts[2], Boolean.parseBoolean(parts[1])));
            nextId = Math.max(nextId, id + 1);
        }
    }

    public void save() throws IOException {
        List<String> lines = tasks.stream()
                .map(t -> t.id() + "\t" + t.done() + "\t" + t.text())
                .toList();
        Files.write(file, lines);                        // 默认 UTF-8，覆盖写
    }

    // ---------- 业务操作 ----------

    public Task add(String text) {
        Task t = new Task(nextId++, text.replace('\t', ' ').replace('\n', ' '), false);
        tasks.add(t);
        return t;
    }

    public List<Task> all() {
        return List.copyOf(tasks);                       // 返回不可变副本，保护内部数据
    }

    public Optional<Task> find(int id) {
        return tasks.stream().filter(t -> t.id() == id).findFirst();
    }

    /** @return 是否找到并标记成功 */
    public boolean markDone(int id) {
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).id() == id) {
                tasks.set(i, tasks.get(i).markDone());
                return true;
            }
        }
        return false;
    }

    public boolean remove(int id) {
        return tasks.removeIf(t -> t.id() == id);
    }

    public int clearDone() {
        int before = tasks.size();
        tasks.removeIf(Task::done);
        return before - tasks.size();
    }
}

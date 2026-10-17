# 项目 2：文件自动整理工具（File Organizer）

**难度**：★★☆☆☆　**依赖**：无（纯 JDK 21+）　**对应章节**：09、11（枚举）、14、16（NIO.2）

下载文件夹乱成一团？这个工具按扩展名把文件分类移动到 `Images/`、`Documents/`、`Archives/`、`Videos/`、`Audio/`、`Code/`、`Others/` 子目录；同名文件自动改名为 `name (1).ext`，**不会覆盖**。

## 运行

```bash
cd projects/02-file-organizer
javac -encoding UTF-8 -d out src/organizer/*.java

# 1) 先预演，看看会发生什么（不会移动任何文件）
java -cp out organizer.Organizer ~/Downloads --dry-run

# 2) 确认无误后真正执行
java -cp out organizer.Organizer ~/Downloads
```

> ⚠ 对重要目录先备份，或者先在一个测试目录里试验。`--dry-run` 是写"会修改用户数据的程序"的良好习惯。

快速试验目录：

```bash
mkdir demo && cd demo && touch a.jpg b.PNG c.pdf d.zip e.mp3 f.java g.unknown README
cd .. && java -cp out organizer.Organizer demo --dry-run
```

## 代码结构与知识点

| 文件 | 知识点 |
|------|--------|
| `FileCategory.java` | 枚举带字段/构造器/静态工厂方法；`Set.of`；`values()` 遍历 |
| `Organizer.java` | `Path`/`Files.list/move/createDirectories`；`EnumMap`；try-with-resources 关闭目录流；`Map.merge` 计数；命令行参数 |

## 练习与扩展

1. 支持 `--recursive`：递归整理子目录（`Files.walk`），注意不要重复整理已分类的目录。
2. 按**修改日期**分类：`Documents/2026-10/xxx.pdf`（`Files.getLastModifiedTime`、`YearMonth`）。
3. 增加 `--undo`：把每次移动记录到日志文件，之后可以还原。
4. 从配置文件 `categories.properties` 读取分类规则，而不是写死在枚举里。
5. 用 `WatchService` 监听目录，新文件下载完成后自动整理（做成后台小工具）。
6. 为 `FileCategory.of`、`uniqueTarget` 写 JUnit 测试（使用 `@TempDir`）。

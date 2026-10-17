# Java 入门教学项目（零基础 → 能做小项目 → 入门 NeoForge 模组）

一套面向初学者的 Java 自学资料，由三部分组成，全部为源码/Markdown，可直接在 IDE 或命令行里使用。

| 部分 | 目录 | 内容 |
|------|------|------|
| ① 核心语法教学 | [`docs/`](docs/) | 25 篇 Markdown：环境、语法、面向对象、泛型、异常、集合、Lambda/Stream、IO、并发、反射、现代 Java、构建与测试、网络、调试与最佳实践 |
| ② 练习题 | [`exercises/`](exercises/) | 10 个类别、69 道题；其中 44 道带参考答案。**题目与答案分开存放**（`problems/` 与 `solutions/`） |
| ③ 应用案例与项目 | [`projects/`](projects/) | 4 个纯 JDK 小项目 + **1 个最简 NeoForge 模组** + 1 个 Maven/JUnit 示例 |

## 建议的学习顺序

1. 阅读 [`docs/00-roadmap.md`](docs/00-roadmap.md)（学习路线与 8 周计划）与 [`docs/01-environment-setup.md`](docs/01-environment-setup.md) 配好环境（**建议 JDK 25**；最低 JDK 21）。
2. 按编号阅读 `docs/`，每读完一章做对应类别的练习。
3. 学完第 15 章后开始做 `projects/01~04`；学完第 19、21 章后做 `projects/05`（NeoForge 模组）。

## 目录总览

```
java-learning/
├── README.md
├── docs/                          核心语法教学（00 ~ 24）
├── exercises/
│   ├── README.md                  题目索引与使用说明
│   └── basics · flow · arrays · strings · oop · collections · exceptions · functional · concurrency · algorithms
│       ├── problems/              Q01_xxx.java …（题目说明在文件头注释里）
│       └── solutions/             A01_xxx.java …（与题目编号对应；部分题目没有答案）
└── projects/
    ├── 01-todo-cli                命令行待办清单           （文件存储、record、Stream）
    ├── 02-file-organizer          文件自动整理工具         （NIO.2、枚举、--dry-run）
    ├── 03-mini-rest-server        迷你 REST 服务 + 网页    （HttpServer、并发安全、虚拟线程）
    ├── 04-link-checker            并发链接检查器           （HttpClient、虚拟线程、退出码）
    ├── 05-neoforge-hello-mod      最简单的 NeoForge 模组   （注册物品、事件总线、Gradle）
    └── 06-maven-junit-demo        Maven + JUnit 5 示例     （依赖管理、单元测试）
```

## 快速上手

```bash
# 练习题：编译全部并运行一道参考答案
cd exercises
javac -encoding UTF-8 -d out $(find . -name "*.java")
java -cp out basics.solutions.A01_HelloProfile

# 项目 1：待办清单
cd ../projects/01-todo-cli
javac -encoding UTF-8 -d out src/todo/*.java && java -cp out todo.TodoApp
```

> Windows 控制台中文乱码时：`chcp 65001`，或运行时加 `-Dstdout.encoding=UTF-8`（JDK 19+）。

## 验证说明（诚实交代）

- `exercises/` 下全部 69 个源码文件（题目骨架 + 参考答案）已在 JDK 21 下编译通过；44 个参考答案均已实际运行。
- `projects/01~04` 已编译并实际运行/测试（含本地启动服务器并用 curl、链接检查器互相验证）。
- `projects/05-neoforge-hello-mod` 依据 NeoForge 官方 26.1 文档与官方 MDK 模板编写，但**未能在无网络的环境里下载 Minecraft 实际构建**——请在本机构建；NeoForge/Minecraft 版本变化很快，若遇到 API 差异请见该项目 README 里的"方式 A"与官方文档。
- `projects/06-maven-junit-demo` 的主代码已编译并做过冒烟测试，Maven 测试需联网在本机运行。

## 版本约定

语法示例以 **Java 21** 为基线；第 20 章介绍到 Java 25；NeoForge 26.1 模组需要 **JDK 25**。

祝学习顺利！遇到问题先读报错，再缩小问题，再提问。

# 项目 5：最简单的 NeoForge 模组（Hello Mod）

**难度**：★★★☆☆　**需要**：JDK 25、Gradle 9.1+（项目自带 wrapper 的话无需单独安装）、IntelliJ IDEA 或 Eclipse
**对应章节**：09、11、12、15、19、21（先读这些会更轻松）
**目标版本**：Minecraft **26.1** · NeoForge **26.1.0.19-beta** · ModDevGradle 2.0.148 · Java 25

这个模组只做一件事：给游戏添加两个新物品——**红宝石（ruby）**和**红宝石碎片（ruby_shard）**，并放进创造模式的"原材料"标签页。但它包含了模组开发的全部骨架：入口类、事件总线、注册器、资源文件、构建配置。弄懂它，你就能继续添加方块、实体、配方……

> ⚠ 关于版本：Minecraft 自 2026 年起采用新版本号（26.1、26.2……），模组 API 变化较快。本项目的构建文件与 API 用法依据 2026 年 10 月时官方 26.1 文档与官方 MDK 模板编写。
> 因为沙箱环境没有网络，**我没能在这里实际下载 Minecraft 并构建它**——Java 代码与资源按文档写成，请你在本机构建验证。如果遇到 API 差异，最稳妥的办法见下方"方式 A"，并对照 [官方文档](https://docs.neoforged.net/) 与对应版本的 Porting Primer。

## 目录结构

```
05-neoforge-hello-mod/
├── build.gradle            构建脚本（ModDevGradle 插件，Java 25）
├── settings.gradle
├── gradle.properties       版本号、模组 ID、模组名 ……（常改的都在这里）
├── TUTORIAL.md             ← 逐步讲解（请继续阅读）
└── src/main/
    ├── java/com/example/hellomod/
    │   ├── HelloMod.java   模组入口（@Mod）
    │   └── ModItems.java   物品注册
    ├── templates/META-INF/neoforge.mods.toml     模组元数据（构建时由 gradle.properties 填充）
    └── resources/assets/hellomod/
        ├── items/          客户端物品定义（指向模型）
        ├── models/item/    物品模型
        ├── textures/item/  16×16 贴图
        └── lang/           中英文名称
```

## 快速开始

### 方式 A（推荐）：用官方 Mod Generator 生成骨架，再覆盖本项目的源码

这种方式能保证 Gradle wrapper、版本号、模板都是官方最新的。

1. 打开 <https://neoforged.net/mod-generator/>，填写：Mod 名 `Hello Mod`、Mod ID `hellomod`、包名 `com.example.hellomod`，选择 Minecraft **26.1**（或你想用的版本）、Gradle 插件 **ModDevGradle**，下载并解压。
2. 把本项目的 `src/main/java`、`src/main/resources`、`src/main/templates` **覆盖**到生成的项目对应位置（删掉生成的示例类 `ExampleMod` 等，避免两个入口并存）。
3. 如果生成项目的 `gradle.properties` 里 `mod_id` 不是 `hellomod`，请改成 `hellomod`。
4. 用 IDE 导入 Gradle 项目，等待首次同步（要下载并反编译 Minecraft，可能很久，需要联网）。

### 方式 B：直接使用本项目

```bash
cd projects/05-neoforge-hello-mod
gradle wrapper --gradle-version 9.1.0     # 需要先装 Gradle；或从 Mod Generator 项目里复制 gradlew、gradlew.bat、gradle/ 目录
./gradlew runClient                        # Windows: gradlew.bat runClient
```

需要 JDK 25：`settings.gradle` 里启用了 foojay 工具链解析器，Gradle 可以自动下载；也可以自己安装 JDK 25 并设置 `JAVA_HOME`。

## 运行与验证

```bash
./gradlew runClient     # 启动开发用的游戏客户端（带你的模组）
./gradlew runServer     # 启动专用服务器测试（首次需要把 run/ 下的 eula.txt 改为 eula=true）
./gradlew build         # 打包：build/libs/hellomod-1.0.0.jar
```

进入游戏后：
1. 创建一个**创造模式**世界；
2. 打开创造物品栏 → **原材料**标签页，末尾能看到红宝石与红宝石碎片；
3. 或者在聊天栏输入 `/give @s hellomod:ruby`；
4. 查看日志，应该能看到：`Hello from hellomod! 模组已加载。`

> 提示：始终要在**专用服务器**上也测试一下你的模组，确保它不会因为"只在客户端存在的类"而崩溃。

## 下一步

阅读 [`TUTORIAL.md`](TUTORIAL.md)：逐文件讲解每一行代码，并说明它用到了哪些 Java 知识，以及如何继续扩展（食物、右键物品、方块、配方……）。

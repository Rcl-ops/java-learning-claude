# 01 · 开发环境搭建

**学习目标**：安装 JDK、能在命令行编译运行程序、选好一个 IDE。

## 1. JDK、JRE、JVM 是什么关系

- **JVM**（Java Virtual Machine）：执行字节码的虚拟机，Java"一次编写，到处运行"的基础。
- **JRE**：JVM + 标准类库，只能**运行**程序。
- **JDK**：JRE + 编译器 `javac` + 工具（`jar`、`jshell`、`javadoc` 等），**开发必须装 JDK**。

```
Hello.java  --javac-->  Hello.class(字节码)  --java(JVM)-->  运行结果
 源代码          编译                              解释执行 + JIT 即时编译
```

## 2. 选择版本

| 版本 | 类型 | 说明 |
|------|------|------|
| 8 / 11 / 17 | 旧 LTS | 老项目常见，了解即可 |
| **21** | LTS | 本教程语法基线（虚拟线程、record、switch 模式匹配） |
| **25** | LTS | 当前最新 LTS；Minecraft 26.1+ / NeoForge 26.1 使用 |

LTS = 长期支持版本，企业主要使用 LTS。**新手建议装 JDK 25。**

常见发行版（功能等价，任选其一）：Eclipse Temurin、Microsoft Build of OpenJDK、Amazon Corretto、Oracle OpenJDK。

## 3. 安装与验证

安装后打开终端（Windows 用 PowerShell / CMD）：

```bash
java -version
javac -version
```

两条命令都能输出版本号即成功。

### 常见问题

| 现象 | 原因 / 解决 |
|------|-------------|
| `'javac' 不是内部或外部命令` | 装的是 JRE，或没配 PATH。重新安装 JDK，或把 `JDK目录/bin` 加入 PATH |
| `java -version` 与 `javac -version` 版本不同 | 机器上有多个 JDK，PATH 顺序问题；检查 `JAVA_HOME` 与 PATH |
| 中文乱码 | 源文件用 **UTF-8** 保存；编译时 `javac -encoding UTF-8`；Windows 控制台输出乱码时先执行 `chcp 65001`，或运行时加 `-Dstdout.encoding=UTF-8`（JDK 19+） |
| `Unsupported class file major version` | 用低版本 JDK 运行了高版本编译的类，统一版本 |

配置 `JAVA_HOME`（可选但推荐，Gradle/Maven 会用到）：

- Windows：系统环境变量新建 `JAVA_HOME=C:\Program Files\...\jdk-25`，PATH 添加 `%JAVA_HOME%\bin`
- macOS/Linux：在 `~/.zshrc` 或 `~/.bashrc` 加 `export JAVA_HOME=/path/to/jdk` 与 `export PATH=$JAVA_HOME/bin:$PATH`

## 4. 三种运行方式

新建 `Hello.java`：

```java
public class Hello {
    public static void main(String[] args) {
        System.out.println("你好，Java！");
    }
}
```

**方式 A：先编译再运行（最经典，理解原理）**

```bash
javac Hello.java      # 生成 Hello.class
java Hello            # 注意：不带 .class
```

**方式 B：单文件源码直接运行（Java 11+，学习阶段最方便）**

```bash
java Hello.java
```

**方式 C：`jshell` 交互式试验（Java 9+，适合验证小想法）**

```bash
jshell
jshell> 1 + 2
$1 ==> 3
jshell> "abc".toUpperCase()
$2 ==> "ABC"
jshell> /exit
```

## 5. 选择 IDE

| 工具 | 说明 |
|------|------|
| **IntelliJ IDEA** | Java 首选，补全/重构/调试最强。NeoForge 官方支持 |
| Eclipse | 老牌免费，NeoForge 官方也支持 |
| VS Code + Java 扩展包 | 轻量，适合小项目 |

IDE 使用建议：
1. 新建 *Java / Gradle / Maven* 项目，选择 JDK 21 或 25。
2. 学习阶段善用：**自动补全**（Ctrl+Space）、**快速修复**（Alt+Enter）、**格式化**（Ctrl+Alt+L）、**调试**（Shift+F9）。
3. 先别装一堆插件，保持简单。

## 6. 本项目中练习题的运行方式

`exercises/` 的源码根目录就是 `exercises/` 本身（包名形如 `basics.problems`）。

```bash
cd exercises
# 编译全部（输出到 out/）
javac -encoding UTF-8 -d out $(find . -name "*.java")          # Linux/macOS
# 运行某一题（类的完整名）
java -cp out basics.solutions.A01_HelloProfile
# 或直接单文件运行（不用编译全部）
java basics/solutions/A01_HelloProfile.java
```

Windows PowerShell 编译全部：`javac -encoding UTF-8 -d out (Get-ChildItem -Recurse -Filter *.java).FullName`。
IDE 里把 `exercises` 目录标记为 **Sources Root** 即可。

## 小结

- 开发要装 **JDK**；`java -version` 与 `javac -version` 要一致。
- `javac` 编译为字节码，`java` 启动 JVM 运行；单文件可 `java Xxx.java` 直接跑。
- 先用 IntelliJ IDEA（或 VS Code）开始，不要折腾配置。

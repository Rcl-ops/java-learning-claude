# 21 · 构建工具与单元测试

**学习目标**：理解包、jar、classpath；会读写最简单的 Maven `pom.xml` 与 Gradle 脚本；会用 JUnit 5 写测试。

## 1. 手工编译与打包（理解原理）

```
project/
└── src/com/example/App.java        （package com.example;）
```

```bash
javac -d out src/com/example/App.java        # -d 指定输出目录，会按包名建子目录
java -cp out com.example.App                 # -cp(classpath) 指定类文件/jar 的位置，运行类要写全限定名

# 打成可执行 jar
echo "Main-Class: com.example.App" > manifest.txt
jar cfm app.jar manifest.txt -C out .
java -jar app.jar
```

**classpath**：JVM 查找类的路径列表（Windows 用 `;` 分隔，Linux/macOS 用 `:`）。依赖第三方 jar 时需要加进去——手工管理很快失控，于是有了构建工具。

## 2. 构建工具做什么

依赖管理（自动下载并解决传递依赖）+ 编译 + 测试 + 打包 + 发布，约定统一的目录结构：

```
my-app/
├── pom.xml 或 build.gradle
├── src/main/java/...        主代码
├── src/main/resources/...   资源（配置、图片、语言文件）
├── src/test/java/...        测试代码
└── target/ 或 build/        输出目录（不提交到 git）
```

## 3. Maven

核心：`pom.xml`，依赖用 **坐标**（groupId : artifactId : version）定位，从中央仓库下载。

```xml
<project xmlns="http://maven.apache.org/POM/4.0.0">
  <modelVersion>4.0.0</modelVersion>
  <groupId>com.example</groupId>
  <artifactId>demo</artifactId>
  <version>1.0.0</version>
  <properties>
    <maven.compiler.release>21</maven.compiler.release>
    <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
  </properties>
  <dependencies>
    <dependency>
      <groupId>org.junit.jupiter</groupId>
      <artifactId>junit-jupiter</artifactId>
      <version>5.13.4</version>
      <scope>test</scope>                 <!-- 只在测试时使用 -->
    </dependency>
  </dependencies>
</project>
```

常用命令：

```bash
mvn compile          # 编译
mvn test             # 运行测试
mvn package          # 打包（target/*.jar）
mvn clean            # 清理 target
mvn clean install    # 安装到本地仓库
mvn dependency:tree  # 查看依赖树
```

完整可运行示例见 `projects/06-maven-junit-demo`。

## 4. Gradle

用 Groovy/Kotlin DSL 写脚本，增量构建更快；**NeoForge 模组使用 Gradle**。

```groovy
plugins {
    id 'java'
}
repositories { mavenCentral() }
java { toolchain { languageVersion = JavaLanguageVersion.of(21) } }
dependencies {
    testImplementation 'org.junit.jupiter:junit-jupiter:5.13.4'
    testRuntimeOnly 'org.junit.platform:junit-platform-launcher'
}
tasks.test { useJUnitPlatform() }
```

```bash
./gradlew build        # Windows: gradlew.bat build ；wrapper 保证大家用同一个 Gradle 版本
./gradlew test
./gradlew runClient    # NeoForge 模组：启动开发用客户端
```

> 建议始终使用项目自带的 **wrapper**（`gradlew`），而不是自己安装的 gradle。

## 5. 单元测试：JUnit 5

测试 = 用代码验证代码。写一个被测类：

```java
public class Calculator {
    public int divide(int a, int b) {
        if (b == 0) throw new IllegalArgumentException("b == 0");
        return a / b;
    }
}
```

测试类（`src/test/java/.../CalculatorTest.java`）：

```java
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {
    private Calculator calc;

    @BeforeEach void setUp() { calc = new Calculator(); }      // 每个测试前执行

    @Test @DisplayName("正常除法")
    void divideNormal() { assertEquals(3, calc.divide(9, 3)); }

    @Test
    void divideByZero() {
        var ex = assertThrows(IllegalArgumentException.class, () -> calc.divide(1, 0));
        assertEquals("b == 0", ex.getMessage());
    }

    @ParameterizedTest   // 需要 junit-jupiter-params（junit-jupiter 聚合依赖已包含）
    @org.junit.jupiter.params.provider.CsvSource({"10,2,5", "9,3,3", "-8,2,-4"})
    void divideMany(int a, int b, int expected) { assertEquals(expected, calc.divide(a, b)); }
}
```

常用断言：`assertEquals(期望, 实际)`、`assertTrue/False`、`assertNull/NotNull`、`assertThrows`、`assertAll`、`assertArrayEquals`、`assertTimeout`。

好测试的特点（F.I.R.S.T）：**F**ast 快、**I**ndependent 独立、**R**epeatable 可重复、**S**elf-validating 自动判断、**T**imely 及时。
测试命名：描述行为 `shouldThrowWhenDivideByZero`。覆盖：正常值、边界值（0、空、最大）、异常输入。

## 6. 日志（替代 System.out）

```java
private static final Logger LOGGER = LoggerFactory.getLogger(MyClass.class);   // SLF4J（NeoForge 也使用它）
LOGGER.info("用户 {} 登录", name);        // {} 占位符，避免无谓字符串拼接
LOGGER.warn("..."); LOGGER.error("失败", exception);
```

级别：`TRACE < DEBUG < INFO < WARN < ERROR`；线上默认 INFO。

## 7. Git 与 .gitignore（简述）

```
# .gitignore
target/
build/
.gradle/
.idea/
*.class
run/
```

## 小结

- 构建工具统一目录结构，管理依赖与流程；Maven 用 XML，Gradle 用脚本。
- JUnit 5：`@Test` + 断言；日志用 SLF4J。

**项目**：`projects/06-maven-junit-demo`、`projects/05-neoforge-hello-mod`

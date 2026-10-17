# 项目 6：Maven + JUnit 5 示例

**难度**：★★☆☆☆　**需要**：JDK 21+、Maven 3.9+（需联网下载依赖）　**对应章节**：21

演示"现实中的 Java 项目长什么样"：标准目录结构、`pom.xml` 管理依赖、JUnit 5 单元测试（普通测试、异常断言、参数化测试、`assertAll`）。

```
06-maven-junit-demo/
├── pom.xml
└── src
    ├── main/java/demo/   Calculator.java、TextUtils.java      ← 被测代码
    └── test/java/demo/   CalculatorTest.java、TextUtilsTest.java  ← 测试代码
```

## 运行

```bash
cd projects/06-maven-junit-demo
mvn test               # 编译并运行全部测试
mvn package            # 打包到 target/maven-junit-demo-1.0.0.jar
mvn clean              # 清理 target/
```

> 说明：Maven 和 JUnit 需要联网下载依赖，这个项目是按标准写法编写的，主代码已在 JDK 21 下编译通过；测试需要你在本机用 `mvn test` 运行。

## 练习

1. 故意把 `Calculator.divide` 改错，观察测试如何失败、报告如何指出问题（**测试的价值就是抓住这种回归**）。
2. 为项目 1 的 `TaskStore` 编写测试（用 `@TempDir` 提供临时目录）。
3. 为 `TextUtils` 增加 `reverseWords`，**先写测试再写实现**（测试驱动开发 TDD：红 → 绿 → 重构）。
4. 把 JUnit 依赖改成 Gradle 的写法（见第 21 章），体验两个工具的差异。

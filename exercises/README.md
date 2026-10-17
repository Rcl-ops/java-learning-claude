# 练习题

共 **69** 道题，其中 **44** 道附参考答案（其余留给你自己探索，也欢迎自己设计测试用例）。

## 目录结构

```
exercises/
└── <类别>/            basics · flow · arrays · strings · oop · collections · exceptions · functional · concurrency · algorithms
    ├── problems/      题目（Q01_xxx.java ...），文件头注释里有题目说明，类里有 TODO
    └── solutions/     参考答案（A01_xxx.java ...），与题目编号一一对应
```

源码根目录就是 `exercises/` 本身，包名形如 `basics.problems`。

## 怎么练

1. 读题（文件头的注释），把代码写在 `problems/` 对应文件的 `TODO` 处。
2. 运行并**自己设计测试数据**，包含边界情况（0、负数、空、重复……）。
3. 做完（或卡住超过 30 分钟）再看 `solutions/` 里的答案，比较**思路**而不只是代码。
4. 看"扩展思考"，尝试用更好的方法（比如 Stream、record、switch 表达式）重写。
5. 没有答案的题：把你的实现与同类题思路对照；也可以把代码贴给 AI 或同学做 review。

## 编译与运行

```bash
cd exercises
javac -encoding UTF-8 -d out $(find . -name "*.java")     # Linux / macOS：编译全部
java -cp out basics.solutions.A01_HelloProfile            # 运行某个类
java -cp out basics.problems.Q01_HelloProfile             # 运行你的作答

# 或单文件直接运行（不需先编译全部）
java basics/solutions/A01_HelloProfile.java
```

Windows PowerShell 编译全部：`javac -encoding UTF-8 -d out (Get-ChildItem -Recurse -Filter *.java).FullName`

> 题目文件骨架可以直接编译；部分题目的方法体抛出 `UnsupportedOperationException("TODO")`，实现后才能正常运行。
> 建议在 IDE 中把 `exercises` 标记为 Sources Root。

## 题目索引

### 基础：变量、类型、运算符（`basics`，对应教程第 03、04 章）

| # | 题目 | 难度 | 题目文件 | 答案 |
|---|------|------|----------|------|
| 01 | 打印个人名片 | ★☆☆☆☆ | `Q01_HelloProfile` | ✔ `A01_HelloProfile` |
| 02 | 交换两个变量的值 | ★☆☆☆☆ | `Q02_SwapTwoNumbers` | ✔ `A02_SwapTwoNumbers` |
| 03 | 温度转换 | ★☆☆☆☆ | `Q03_TemperatureConverter` | ✔ `A03_TemperatureConverter` |
| 04 | 拆分数字 | ★★☆☆☆ | `Q04_DigitSum` | ✔ `A04_DigitSum` |
| 05 | 探索：溢出、精度与强转 | ★★☆☆☆ | `Q05_IntegerOverflow` | — |
| 06 | 位运算：权限标志位 | ★★★☆☆ | `Q06_BitFlags` | — |

### 流程控制（`flow`，对应教程第 05 章）

| # | 题目 | 难度 | 题目文件 | 答案 |
|---|------|------|----------|------|
| 01 | 判断闰年 | ★☆☆☆☆ | `Q01_LeapYear` | ✔ `A01_LeapYear` |
| 02 | 成绩等级 | ★☆☆☆☆ | `Q02_GradeLevel` | ✔ `A02_GradeLevel` |
| 03 | 九九乘法表 | ★☆☆☆☆ | `Q03_MultiplicationTable` | ✔ `A03_MultiplicationTable` |
| 04 | 100 以内的素数 | ★★☆☆☆ | `Q04_PrimeNumbers` | ✔ `A04_PrimeNumbers` |
| 05 | FizzBuzz 及变种 | ★☆☆☆☆ | `Q05_FizzBuzz` | ✔ `A05_FizzBuzz` |
| 06 | 打印菱形 | ★★★☆☆ | `Q06_Diamond` | — |
| 07 | 猜数字游戏 | ★★★☆☆ | `Q07_GuessNumber` | — |

### 数组、方法与递归（`arrays`，对应教程第 06、07 章）

| # | 题目 | 难度 | 题目文件 | 答案 |
|---|------|------|----------|------|
| 01 | 最大值、最小值、平均值 | ★☆☆☆☆ | `Q01_MaxMinAvg` | ✔ `A01_MaxMinAvg` |
| 02 | 原地反转数组 | ★☆☆☆☆ | `Q02_ReverseArray` | ✔ `A02_ReverseArray` |
| 03 | 冒泡排序 | ★★☆☆☆ | `Q03_BubbleSort` | ✔ `A03_BubbleSort` |
| 04 | 二分查找 | ★★☆☆☆ | `Q04_BinarySearch` | ✔ `A04_BinarySearch` |
| 05 | 矩阵转置与对角线 | ★★☆☆☆ | `Q05_MatrixTranspose` | ✔ `A05_MatrixTranspose` |
| 06 | 斐波那契：递归、迭代与记忆化 | ★★☆☆☆ | `Q06_Fibonacci` | ✔ `A06_Fibonacci` |
| 07 | 有序数组去重 | ★★☆☆☆ | `Q07_RemoveDuplicates` | — |
| 08 | 杨辉三角 | ★★★☆☆ | `Q08_PascalTriangle` | — |

### 字符串（`strings`，对应教程第 08 章）

| # | 题目 | 难度 | 题目文件 | 答案 |
|---|------|------|----------|------|
| 01 | 回文判断 | ★★☆☆☆ | `Q01_Palindrome` | ✔ `A01_Palindrome` |
| 02 | 统计字符类别 | ★☆☆☆☆ | `Q02_CountChars` | ✔ `A02_CountChars` |
| 03 | 反转单词顺序 | ★★☆☆☆ | `Q03_ReverseWords` | ✔ `A03_ReverseWords` |
| 04 | 凯撒密码 | ★★☆☆☆ | `Q04_CaesarCipher` | ✔ `A04_CaesarCipher` |
| 05 | 拼接性能对比 | ★★☆☆☆ | `Q05_StringBuilderPerf` | — |
| 06 | 密码强度与邮箱校验 | ★★★☆☆ | `Q06_PasswordStrength` | — |
| 07 | 变位词 | ★★☆☆☆ | `Q07_Anagram` | — |

### 面向对象（`oop`，对应教程第 09~11 章）

| # | 题目 | 难度 | 题目文件 | 答案 |
|---|------|------|----------|------|
| 01 | 封装：学生类 | ★☆☆☆☆ | `Q01_StudentEncapsulation` | ✔ `A01_StudentEncapsulation` |
| 02 | 银行账户 | ★★☆☆☆ | `Q02_BankAccount` | ✔ `A02_BankAccount` |
| 03 | 图形继承与多态 | ★★☆☆☆ | `Q03_ShapeHierarchy` | ✔ `A03_ShapeHierarchy` |
| 04 | 接口：会飞会游的动物 | ★★☆☆☆ | `Q04_DuckInterfaces` | ✔ `A04_DuckInterfaces` |
| 05 | 重写 equals / hashCode / toString | ★★☆☆☆ | `Q05_PointEqualsHashCode` | ✔ `A05_PointEqualsHashCode` |
| 06 | 枚举：星期与运算符 | ★★☆☆☆ | `Q06_WeekdayEnum` | — |
| 07 | 综合：图书馆系统 | ★★★★☆ | `Q07_LibrarySystem` | — |
| 08 | 不可变值对象：Money | ★★★☆☆ | `Q08_ImmutableMoney` | — |
| 09 | 设计模式初体验 | ★★★★☆ | `Q09_DesignPatterns` | — |

### 集合（`collections`，对应教程第 14 章）

| # | 题目 | 难度 | 题目文件 | 答案 |
|---|------|------|----------|------|
| 01 | 词频统计与排序 | ★★☆☆☆ | `Q01_WordFrequency` | ✔ `A01_WordFrequency` |
| 02 | 列表去重并保持顺序 | ★☆☆☆☆ | `Q02_RemoveDuplicatesList` | ✔ `A02_RemoveDuplicatesList` |
| 03 | 两数之和 | ★★☆☆☆ | `Q03_TwoSum` | ✔ `A03_TwoSum` |
| 04 | 括号匹配 | ★★☆☆☆ | `Q04_ValidParentheses` | ✔ `A04_ValidParentheses` |
| 05 | LRU 缓存 | ★★★☆☆ | `Q05_LruCache` | ✔ `A05_LruCache` |
| 06 | 字母异位词分组 | ★★★☆☆ | `Q06_GroupAnagrams` | — |
| 07 | 出现频率最高的 K 个元素 | ★★★☆☆ | `Q07_TopKFrequent` | — |
| 08 | 多条件排序 | ★★☆☆☆ | `Q08_StudentRanking` | — |

### 异常与文件（`exceptions`，对应教程第 13、16 章）

| # | 题目 | 难度 | 题目文件 | 答案 |
|---|------|------|----------|------|
| 01 | 安全除法与输入解析 | ★☆☆☆☆ | `Q01_SafeDivide` | ✔ `A01_SafeDivide` |
| 02 | 自定义异常与异常链 | ★★☆☆☆ | `Q02_CustomException` | ✔ `A02_CustomException` |
| 03 | try-with-resources 与关闭顺序 | ★★☆☆☆ | `Q03_TryWithResources` | ✔ `A03_TryWithResources` |
| 04 | 读取文件并统计 | ★★☆☆☆ | `Q04_ReadFileLines` | ✔ `A04_ReadFileLines` |
| 05 | 递归复制目录 | ★★★☆☆ | `Q05_CopyDirectory` | — |
| 06 | 简易日志器 | ★★★☆☆ | `Q06_SimpleLogger` | — |

### Lambda 与 Stream（`functional`，对应教程第 15 章）

| # | 题目 | 难度 | 题目文件 | 答案 |
|---|------|------|----------|------|
| 01 | Lambda 与方法引用排序 | ★☆☆☆☆ | `Q01_LambdaSort` | ✔ `A01_LambdaSort` |
| 02 | Stream 基础操作 | ★★☆☆☆ | `Q02_StreamBasics` | ✔ `A02_StreamBasics` |
| 03 | 分组与统计 | ★★☆☆☆ | `Q03_GroupingBy` | ✔ `A03_GroupingBy` |
| 04 | 自定义函数式接口 | ★★☆☆☆ | `Q04_CustomFunctionalInterface` | ✔ `A04_CustomFunctionalInterface` |
| 05 | Optional 链式调用 | ★★☆☆☆ | `Q05_OptionalChain` | — |
| 06 | Stream 统计文本 | ★★★☆☆ | `Q06_StreamWordCount` | — |

### 多线程与并发（`concurrency`，对应教程第 18 章）

| # | 题目 | 难度 | 题目文件 | 答案 |
|---|------|------|----------|------|
| 01 | 创建线程的三种方式 | ★☆☆☆☆ | `Q01_ThreadBasics` | ✔ `A01_ThreadBasics` |
| 02 | 竞态条件与修复 | ★★☆☆☆ | `Q02_RaceCondition` | ✔ `A02_RaceCondition` |
| 03 | 线程池分段求和 | ★★★☆☆ | `Q03_ExecutorSum` | ✔ `A03_ExecutorSum` |
| 04 | 生产者-消费者 | ★★★☆☆ | `Q04_ProducerConsumer` | ✔ `A04_ProducerConsumer` |
| 05 | CompletableFuture 编排 | ★★★☆☆ | `Q05_CompletableFutureChain` | — |
| 06 | 虚拟线程：一万个任务 | ★★★☆☆ | `Q06_VirtualThreads` | — |

### 算法与综合（`algorithms`，对应教程第 05~07、17 章）

| # | 题目 | 难度 | 题目文件 | 答案 |
|---|------|------|----------|------|
| 01 | 最大公约数与最小公倍数 | ★☆☆☆☆ | `Q01_GcdLcm` | ✔ `A01_GcdLcm` |
| 02 | 汉诺塔 | ★★★☆☆ | `Q02_Hanoi` | ✔ `A02_Hanoi` |
| 03 | 进制转换 | ★★☆☆☆ | `Q03_BaseConversion` | ✔ `A03_BaseConversion` |
| 04 | 归并排序 | ★★★☆☆ | `Q04_MergeSort` | — |
| 05 | 约瑟夫环 | ★★★☆☆ | `Q05_Josephus` | — |
| 06 | 综合小项目：文字冒险游戏 | ★★★★★ | `Q06_TextAdventure` | — |

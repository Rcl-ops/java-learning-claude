# 05 · 流程控制

**学习目标**：掌握分支（`if`、`switch`）、循环（`for`、`while`、`do-while`、增强 for）及跳转（`break`、`continue`）。

## 1. if / else

```java
int score = 85;
if (score >= 90) {
    System.out.println("优秀");
} else if (score >= 60) {
    System.out.println("及格");
} else {
    System.out.println("不及格");
}
```

- 条件必须是 `boolean`（不像 C 可以用整数）。
- 即使只有一条语句，**也建议总是写大括号**，避免悬空 else 等错误。
- 先处理异常/边界情况并提前 `return`，可以减少嵌套（卫语句）：

```java
static void register(String name) {
    if (name == null || name.isBlank()) return;   // 卫语句
    // 正常逻辑……
}
```

## 2. switch

### 传统写法（有"贯穿"陷阱）

```java
int day = 3;
switch (day) {
    case 1:
        System.out.println("周一");
        break;            // 漏掉 break 会继续执行下一个 case！
    case 2:
    case 3:               // 多个 case 共用一段逻辑
        System.out.println("周二或周三");
        break;
    default:
        System.out.println("其他");
}
```

### 现代写法：箭头 + switch 表达式（Java 14+，推荐）

```java
String name = switch (day) {
    case 1, 7 -> "周末或周一";
    case 2, 3, 4, 5, 6 -> "工作日";
    default -> "非法";
};
```

- 箭头形式**没有贯穿**，不需要 `break`。
- 作为表达式时必须**穷尽**所有情况（枚举/密封类可省 `default`）。
- 多行逻辑用 `yield` 返回值：

```java
int len = switch (name) {
    case "a" -> 1;
    default -> {
        int t = name.length();
        yield t * 2;
    }
};
```

可用于 `switch` 的类型：`byte short char int`（及包装类）、`String`、枚举，以及 Java 21 起的**任意类型模式匹配**：

```java
static String describe(Object o) {
    return switch (o) {
        case null -> "空";
        case Integer i when i > 100 -> "大整数 " + i;
        case Integer i -> "整数 " + i;
        case String s -> "字符串，长度 " + s.length();
        default -> "其他";
    };
}
```

## 3. for 循环

```java
for (int i = 0; i < 5; i++) {        // 初始化; 条件; 更新
    System.out.println(i);
}
// 倒序、步长
for (int i = 10; i > 0; i -= 3) { ... }
// 多变量
for (int i = 0, j = 10; i < j; i++, j--) { ... }
```

执行顺序：初始化（一次）→ 判断条件 → 循环体 → 更新 → 判断条件 → …

## 4. while 与 do-while

```java
int n = 0;
while (n < 3) {          // 先判断后执行，可能一次也不执行
    n++;
}
do {                     // 先执行后判断，至少执行一次
    n--;
} while (n > 0);
```

典型用法：次数不确定的循环，如读输入直到用户输入 `quit`。

## 5. 增强 for（遍历数组 / 集合）

```java
int[] nums = {1, 2, 3};
for (int x : nums) System.out.println(x);

List<String> names = List.of("a", "b");
for (String s : names) System.out.println(s);
```

不需要下标时优先使用；需要下标或要修改元素时用普通 for。

## 6. break / continue / 标签

```java
for (int i = 1; i <= 10; i++) {
    if (i % 2 == 0) continue;   // 跳过本次，进入下一次
    if (i > 7) break;           // 结束整个循环
    System.out.println(i);      // 1 3 5 7
}

outer:                           // 标签：跳出多层循环
for (int i = 0; i < 3; i++) {
    for (int j = 0; j < 3; j++) {
        if (i * j == 2) break outer;
    }
}
```

## 7. 常见循环套路

```java
// 累加
int sum = 0;
for (int i = 1; i <= 100; i++) sum += i;

// 找最大值
int max = Integer.MIN_VALUE;
for (int x : nums) if (x > max) max = x;

// 计数
int count = 0;
for (int i = 1; i <= 100; i++) if (i % 7 == 0) count++;

// 嵌套：打印三角形
for (int i = 1; i <= 5; i++) {
    System.out.println("*".repeat(i));
}

// 死循环 + 内部退出
while (true) {
    String line = scanner.nextLine();
    if (line.equals("quit")) break;
}
```

## 8. 常见错误

| 问题 | 提示 |
|------|------|
| 死循环 | 检查更新语句是否漏写、条件是否永远为 true |
| 差一错误（off-by-one） | `<` 与 `<=` 用错；数组遍历用 `i < arr.length` |
| 在 `for` 括号后误加 `;` | `for (...); { }` 循环体变成空语句 |
| `switch` 漏 `break` | 改用箭头语法 |
| 循环内修改集合 | 遍历 List 时删除元素会 `ConcurrentModificationException`，见第 14 章 |

## 小结

- 优先用 **箭头 switch 表达式**；需要下标用 `for`，不需要用增强 `for`。
- 次数确定用 `for`，不确定用 `while`，至少一次用 `do-while`。
- `break` 结束循环，`continue` 跳过本轮，标签可跳出多层。

**练习**：`exercises/flow`

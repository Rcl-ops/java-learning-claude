# 17 · 常用 API 与日期时间

**学习目标**：熟悉 `Math`、随机数、`BigDecimal`、`Objects`、`System`；掌握 `java.time` 处理日期时间。

## 1. Math

```java
Math.abs(-5); Math.max(1, 2); Math.min(1, 2);
Math.pow(2, 10);      // 1024.0
Math.sqrt(16);        // 4.0
Math.ceil(1.2);       // 2.0   向上取整
Math.floor(1.8);      // 1.0   向下取整
Math.round(2.5);      // 3     四舍五入（返回 long）
Math.floorMod(-7, 3); // 2
Math.addExact(a, b);  // 溢出时抛 ArithmeticException（还有 multiplyExact 等）
Math.PI; Math.E;
Math.hypot(3, 4);     // 5.0
```

## 2. 随机数

```java
Random r = new Random();
r.nextInt(10);                 // [0, 10)
r.nextDouble();                // [0, 1)
new Random(42).nextInt(100);   // 固定种子 → 序列可复现（测试、游戏存档）

ThreadLocalRandom.current().nextInt(1, 7);   // 多线程场景首选；[1, 7)，掷骰子
List<Integer> l = new ArrayList<>(List.of(1, 2, 3)); Collections.shuffle(l);
```

> 需要**密码学安全**的随机数（令牌、密钥）用 `java.security.SecureRandom`，不要用 `Random`。

## 3. BigDecimal 与 BigInteger

```java
BigDecimal a = new BigDecimal("0.1");      // 一定用字符串构造；new BigDecimal(0.1) 会带入二进制误差
BigDecimal b = new BigDecimal("0.2");
a.add(b);                                  // 0.3
a.multiply(b); a.subtract(b);
new BigDecimal("10").divide(new BigDecimal("3"), 2, RoundingMode.HALF_UP);   // 3.33，除不尽必须指定精度和舍入
a.compareTo(b);                            // 比较大小用 compareTo；equals 会比较 scale（0.1 与 0.10 不相等）
a.setScale(2, RoundingMode.HALF_UP);

BigInteger big = BigInteger.valueOf(2).pow(100);   // 任意大整数
```

金额运算：用 `BigDecimal`（或以"分"为单位的 `long`），**永远不要用 `double`**。

## 4. Objects 工具类

```java
Objects.equals(a, b);                // null 安全的 equals
Objects.hash(x, y, z);               // 生成 hashCode
Objects.requireNonNull(obj, "obj 不能为 null");
Objects.requireNonNullElse(s, "默认");
Objects.toString(obj, "null 时的文本");
```

## 5. System 与 Runtime

```java
long t0 = System.nanoTime();         // 测量耗时用 nanoTime（单调）；currentTimeMillis 是墙上时间
System.getProperty("user.dir");      // 当前工作目录
System.getProperty("java.version");
System.getenv("PATH");               // 环境变量
System.lineSeparator();
System.exit(0);
System.err.println("错误输出流");
Runtime.getRuntime().availableProcessors();
Runtime.getRuntime().totalMemory();
```

## 6. UUID

```java
String id = UUID.randomUUID().toString();   // 如 3f2b8a52-...；全局唯一标识
```

## 7. java.time：现代日期时间 API（Java 8+）

**不要再使用** `Date`、`Calendar`、`SimpleDateFormat`（可变、线程不安全、设计混乱）。`java.time` 的类全部**不可变**且线程安全。

| 类 | 含义 | 例 |
|----|------|----|
| `LocalDate` | 日期 | 2026-10-17 |
| `LocalTime` | 时间 | 14:30:00 |
| `LocalDateTime` | 日期+时间（无时区） | 2026-10-17T14:30 |
| `ZonedDateTime` | 带时区 | 2026-10-17T14:30+08:00[Asia/Shanghai] |
| `Instant` | 时间线上的一个瞬间（UTC，机器时间） | 时间戳 |
| `Duration` | 时间量（秒/纳秒） | 90 分钟 |
| `Period` | 日期量（年月日） | 3 个月 |

```java
LocalDate today = LocalDate.now();
LocalDate d = LocalDate.of(2026, 10, 17);
d.plusDays(30); d.minusMonths(1); d.withDayOfMonth(1);
d.getDayOfWeek();                    // SATURDAY
d.isLeapYear(); d.lengthOfMonth();
d.isBefore(LocalDate.of(2027, 1, 1));

LocalDateTime now = LocalDateTime.now();
ZonedDateTime sh = ZonedDateTime.now(ZoneId.of("Asia/Shanghai"));
ZonedDateTime ny = sh.withZoneSameInstant(ZoneId.of("America/New_York"));

Duration du = Duration.between(LocalTime.of(9, 0), LocalTime.of(17, 30));   // PT8H30M
du.toMinutes();                      // 510
Period pe = Period.between(LocalDate.of(2000, 1, 1), LocalDate.now());
pe.getYears();
long days = java.time.temporal.ChronoUnit.DAYS.between(d1, d2);

Instant.now().toEpochMilli();        // 毫秒时间戳
```

### 格式化与解析

```java
DateTimeFormatter f = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
String s = LocalDateTime.now().format(f);
LocalDateTime parsed = LocalDateTime.parse("2026-10-17 14:30:00", f);
LocalDate iso = LocalDate.parse("2026-10-17");   // ISO 格式无需 formatter
```

模式字母：`yyyy` 年、`MM` 月、`dd` 日、`HH` 24 小时、`hh` 12 小时、`mm` 分、`ss` 秒、`SSS` 毫秒。注意**大小写区分**（`MM` 月 vs `mm` 分钟）。

## 8. 包装类常用方法

```java
Integer.MAX_VALUE; Integer.compare(a, b); Integer.toString(255, 16);   // "ff"
Integer.parseInt("ff", 16);       // 255
Integer.bitCount(7);              // 3
Long.parseLong("123"); Double.compare(a, b); Double.isNaN(x);
Character.isDigit('5'); Boolean.parseBoolean("true");
```

## 小结

- 精确小数用 `BigDecimal`（字符串构造）；随机数多线程用 `ThreadLocalRandom`。
- 日期时间只用 `java.time`；格式化模式区分大小写。
- 计时用 `System.nanoTime()`，非空检查用 `Objects.requireNonNull`。

**练习**：`exercises/algorithms`、`exercises/basics`

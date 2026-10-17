# 15 · Lambda 与 Stream

**学习目标**：理解函数式接口与 Lambda；掌握方法引用；会用 Stream 做过滤/映射/分组/统计；会用 `Optional` 避免空指针。

## 1. Lambda 表达式

Lambda 是"**匿名函数**"，用来简洁地实现**函数式接口**（只有一个抽象方法的接口）。

```java
// 语法： (参数) -> 表达式    或    (参数) -> { 语句; return 值; }
Runnable r = () -> System.out.println("hi");
Comparator<String> byLen = (a, b) -> a.length() - b.length();
Function<Integer, Integer> square = x -> x * x;          // 单参数可省括号
BiFunction<Integer, Integer, Integer> add = (a, b) -> {
    int sum = a + b;
    return sum;
};
```

- 参数类型可由编译器推断，通常省略。
- Lambda 里使用的外部局部变量必须是 **有效 final**（不能被重新赋值）。

## 2. 常用函数式接口（`java.util.function`）

| 接口 | 方法 | 含义 | 例 |
|------|------|------|----|
| `Function<T,R>` | `R apply(T)` | 转换 | `s -> s.length()` |
| `Predicate<T>` | `boolean test(T)` | 判断 | `n -> n > 0` |
| `Consumer<T>` | `void accept(T)` | 消费 | `System.out::println` |
| `Supplier<T>` | `T get()` | 提供 | `() -> new ArrayList<>()` |
| `UnaryOperator<T>` | `T apply(T)` | 同类型转换 | `String::trim` |
| `BinaryOperator<T>` | `T apply(T,T)` | 两个同类型合并 | `Integer::sum` |
| `BiFunction<T,U,R>` | | 两个参数 | |

组合：

```java
Predicate<Integer> pos = n -> n > 0, even = n -> n % 2 == 0;
pos.and(even).test(4);        // true
pos.negate().test(4);         // false
Function<Integer, Integer> plus1 = x -> x + 1, dbl = x -> x * 2;
plus1.andThen(dbl).apply(3);  // (3+1)*2 = 8
plus1.compose(dbl).apply(3);  // 3*2+1 = 7
```

## 3. 方法引用

Lambda 只是调用一个已有方法时，可以更简写：

| 形式 | 等价 Lambda |
|------|------------|
| `String::length`（类名::实例方法） | `s -> s.length()` |
| `System.out::println`（对象::实例方法） | `x -> System.out.println(x)` |
| `Integer::parseInt`（类名::静态方法） | `s -> Integer.parseInt(s)` |
| `ArrayList::new`（构造器引用） | `() -> new ArrayList<>()` |

## 4. Stream：对数据的"流水线"处理

```
数据源 → 中间操作（惰性，可链式）→ 终结操作（触发执行，产生结果）
```

```java
List<String> words = List.of("apple", "banana", "cherry", "date", "fig", "grape");

List<String> result = words.stream()            // 创建流
        .filter(w -> w.length() > 4)            // 中间：过滤
        .map(String::toUpperCase)               // 中间：映射
        .sorted()                               // 中间：排序
        .limit(3)                               // 中间：截断
        .collect(Collectors.toList());          // 终结：收集    或直接 .toList()（Java 16+，不可变）
```

### 创建流

```java
list.stream();  Arrays.stream(arr);  Stream.of(1, 2, 3);
IntStream.range(0, 5);          // 0..4
IntStream.rangeClosed(1, 5);    // 1..5
Stream.iterate(1, x -> x * 2).limit(10);
"hello".chars();                // IntStream
Files.lines(Path.of("a.txt")); // 文件行（记得 try-with-resources 关闭）
```

### 常用中间操作

`filter` 过滤 · `map` 映射 · `flatMap` 展平 · `distinct` 去重 · `sorted` 排序 · `limit/skip` 截取 · `peek` 调试 · `mapToInt` 转基本类型流

```java
List<List<Integer>> nested = List.of(List.of(1, 2), List.of(3), List.of(4, 5));
nested.stream().flatMap(List::stream).toList();    // [1, 2, 3, 4, 5]
```

### 常用终结操作

```java
words.stream().count();
words.stream().anyMatch(w -> w.startsWith("a"));   // allMatch / noneMatch
words.stream().findFirst();                         // Optional
words.stream().mapToInt(String::length).sum();      // sum / average / max / min
IntStream.rangeClosed(1, 100).sum();                // 5050
words.stream().max(Comparator.comparingInt(String::length)).get();
words.stream().reduce("", (a, b) -> a + b.charAt(0));   // 归约："abcdfg"
words.forEach(System.out::println);
```

### Collectors：强大的收集器

```java
record Person(String name, String city, int age) { }
List<Person> ps = List.of(new Person("A", "北京", 20), new Person("B", "上海", 30), new Person("C", "北京", 40));

ps.stream().collect(Collectors.groupingBy(Person::city));                          // Map<String, List<Person>>
ps.stream().collect(Collectors.groupingBy(Person::city, Collectors.counting()));   // {北京=2, 上海=1}
ps.stream().collect(Collectors.groupingBy(Person::city, Collectors.averagingInt(Person::age)));
ps.stream().collect(Collectors.partitioningBy(p -> p.age() >= 30));               // Map<Boolean, List<Person>>
ps.stream().map(Person::name).collect(Collectors.joining(", ", "[", "]"));        // [A, B, C]
ps.stream().collect(Collectors.toMap(Person::name, Person::age));
ps.stream().collect(Collectors.summarizingInt(Person::age));                      // count/sum/min/avg/max
```

### 并行流（谨慎）

`list.parallelStream()` 利用多核，但：数据量小反而更慢；操作必须**无状态、无副作用**；默认共用 ForkJoinPool。**不要默认使用**，先测量。

### Stream 的注意事项

- 一个流只能消费**一次**。
- 中间操作是惰性的，没有终结操作什么都不会发生。
- Stream **不修改**数据源，产生新结果。
- 不要在 `map`/`filter` 里写有副作用的代码（改外部变量等）。
- 逻辑复杂时，普通 `for` 循环可能更清晰，Stream 不是银弹。

## 5. Optional：优雅地处理"可能没有值"

```java
Optional<String> opt = Optional.ofNullable(findName());   // of(x) 不允许 null；empty() 表示空
String name = opt.orElse("匿名");
String n2 = opt.orElseGet(() -> compute());               // 惰性默认值
String n3 = opt.orElseThrow(() -> new NoSuchElementException("没找到"));
opt.ifPresent(v -> System.out.println(v));
opt.ifPresentOrElse(System.out::println, () -> System.out.println("空"));
int len = opt.map(String::length).filter(l -> l > 2).orElse(0);   // 链式
```

用法准则：**只用作方法返回值**，表示"可能没有结果"；不要用作字段/参数；不要 `opt.get()`（没值会抛异常），用 `orElse*`。

## 6. 示例：用 Stream 重写词频统计

```java
String text = "the quick brown fox jumps over the lazy dog the end";
Map<String, Long> freq = Arrays.stream(text.split("\\s+"))
        .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
freq.entrySet().stream()
    .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
    .limit(3)
    .forEach(e -> System.out.println(e.getKey() + ": " + e.getValue()));
```

## 小结

- Lambda 简化函数式接口的实现；方法引用是 Lambda 的简写。
- Stream = 数据源 + 中间操作 + 终结操作；`collect(groupingBy…)` 非常强大。
- `Optional` 做返回值，替代返回 `null`。

**练习**：`exercises/functional`

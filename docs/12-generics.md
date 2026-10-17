# 12 · 泛型

**学习目标**：理解泛型解决的问题；会写泛型类/方法；理解类型擦除与通配符。

## 1. 为什么需要泛型

没有泛型时，容器只能存 `Object`，取出要强转，类型错误到运行时才暴露：

```java
List list = new ArrayList();      // 原始类型（raw type），不要使用
list.add("a");
list.add(1);
String s = (String) list.get(1);  // ClassCastException（运行时才发现）
```

泛型把类型作为**参数**，把错误提前到**编译期**，并省去强转：

```java
List<String> list = new ArrayList<>();   // <> 菱形语法，右边类型自动推断
list.add("a");
// list.add(1);                          // ❌ 编译错误
String s = list.get(0);                  // 无需强转
```

## 2. 泛型类与接口

```java
public class Box<T> {                    // T 是类型参数（Type）；常用名：T、E(元素)、K/V(键值)
    private T value;
    public Box(T value) { this.value = value; }
    public T get() { return value; }
    public void set(T value) { this.value = value; }
}
Box<Integer> b = new Box<>(42);
int v = b.get();

public class Pair<A, B> {
    private final A first; private final B second;
    public Pair(A a, B b) { first = a; second = b; }
    public A first() { return first; }
    public B second() { return second; }
}

public interface Repository<T, ID> {
    T findById(ID id);
    void save(T entity);
}
```

> 泛型的类型参数**只能是引用类型**：`Box<int>` 不行，用 `Box<Integer>`。

## 3. 泛型方法

在返回类型前声明 `<T>`：

```java
public static <T> void printAll(List<T> list) {
    for (T t : list) System.out.println(t);
}

public static <T extends Comparable<T>> T max(List<T> list) {   // 类型上界：T 必须可比较
    T best = list.get(0);
    for (T t : list) if (t.compareTo(best) > 0) best = t;
    return best;
}
max(List.of(3, 9, 2));        // 9，类型自动推断
max(List.of("b", "z", "a"));  // "z"
```

## 4. 类型擦除

泛型信息**只存在于编译期**，运行时会被擦除为上界（通常 `Object`）。所以：

```java
List<String> a = new ArrayList<>();
List<Integer> b = new ArrayList<>();
a.getClass() == b.getClass();      // true

// 以下都不允许：
// new T();                // 不能实例化类型参数
// new T[10];              // 不能创建泛型数组
// obj instanceof List<String>   // 不能对参数化类型做 instanceof
// static T field;         // 静态上下文不能用类的类型参数
// 基本类型作为类型实参
```

## 5. 通配符

`List<Integer>` **不是** `List<Number>` 的子类型（泛型不协变）。需要"某种 Number 的列表"时用通配符：

```java
static double sum(List<? extends Number> list) {   // 上界通配符：只读（生产者）
    double s = 0;
    for (Number n : list) s += n.doubleValue();
    return s;
}
sum(List.of(1, 2, 3));           // List<Integer> OK
sum(List.of(1.5, 2.5));          // List<Double> OK

static void fill(List<? super Integer> list) {     // 下界通配符：只写（消费者）
    for (int i = 0; i < 3; i++) list.add(i);
}
fill(new ArrayList<Number>());   // OK
fill(new ArrayList<Object>());   // OK

static void printAny(List<?> list) { }             // 无界：只读，不关心类型
```

记忆口诀 **PECS**：**P**roducer **E**xtends, **C**onsumer **S**uper —— 从容器里"取"用 `? extends`，往容器里"放"用 `? super`。

## 6. 常见问题

| 问题 | 说明 |
|------|------|
| `unchecked` 警告 | 使用了原始类型或不安全强转；尽量消除，必要时才 `@SuppressWarnings("unchecked")` |
| `Box<Object> b = new Box<String>("x")` 报错 | 泛型不协变，需要通配符 |
| 方法重载冲突 `void f(List<String>)` 与 `void f(List<Integer>)` | 擦除后签名相同，不能同时存在 |

## 小结

- 泛型 = 编译期类型安全 + 免强转；运行时被擦除。
- 泛型类 `class A<T>`，泛型方法 `<T> T f(...)`，上界 `<T extends X>`。
- 读用 `? extends`，写用 `? super`（PECS）。

**练习**：`exercises/collections`、`exercises/oop`

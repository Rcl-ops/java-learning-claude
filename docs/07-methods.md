# 07 · 方法

**学习目标**：会定义和调用方法；理解 Java 只有"值传递"；掌握重载与递归。

## 1. 定义与调用

```java
// 访问修饰符 [static] 返回类型 方法名(参数列表) { 方法体 }
public static int add(int a, int b) {
    return a + b;
}

public static void greet(String name) {   // 无返回值用 void
    System.out.println("Hello, " + name);
}

public static void main(String[] args) {
    int r = add(3, 4);      // 调用：实参按顺序传给形参
    greet("Alice");
}
```

- `return` 立即结束方法并返回值；`void` 方法可以用单独的 `return;` 提前结束。
- 有返回值的方法，**所有路径**都必须 `return`，否则编译报错 `missing return statement`。
- 方法不能嵌套定义（但可以有局部类/Lambda）。

**为什么要用方法**：消除重复、给一段逻辑起名字（提高可读性）、便于测试和修改。
**好方法的标准**：名字是动词短语（`calculateTax`）、只做一件事、参数不超过 3~4 个、长度一屏以内。

## 2. 值传递（重点）

> Java **只有值传递**：传给方法的永远是"值的副本"。

```java
static void change(int x) { x = 100; }                 // 修改的是副本
static void change(int[] arr) { arr[0] = 100; }        // 引用的副本，指向同一个数组
static void reassign(int[] arr) { arr = new int[]{9}; } // 只改变副本引用，不影响外部

int n = 1;
change(n);                 // n 仍然是 1

int[] a = {1, 2};
change(a);                 // a[0] 变成 100：副本引用指向同一对象，改的是对象内容
reassign(a);               // a 不变
```

对**基本类型**：副本是值本身，改了不影响外部。
对**引用类型**：副本是"地址的拷贝"，通过它修改对象内容，外部能看到；但让副本指向新对象，外部不受影响。

## 3. 方法重载（Overload）

同一个类里**方法名相同、参数列表不同**（个数、类型或顺序），与返回值无关。

```java
static int max(int a, int b) { return a > b ? a : b; }
static double max(double a, double b) { return a > b ? a : b; }
static int max(int a, int b, int c) { return max(max(a, b), c); }

max(1, 2);        // 调 int 版本
max(1.5, 2);      // 2 自动提升为 double，调 double 版本
```

`System.out.println` 就有 10 多个重载。

## 4. 变量作用域

```java
public class Scope {
    static int total = 0;          // 类变量（static 字段），整个类可用

    static void demo() {
        int x = 1;                 // 局部变量：从声明到所在大括号结束
        if (x > 0) {
            int y = 2;             // 只在 if 块内有效
        }
        // System.out.println(y);  // ❌ 编译错误
        for (int i = 0; i < 3; i++) { }   // i 只在 for 内
    }
}
```

局部变量不能与同一作用域内的其他局部变量/形参重名（但可与成员变量重名，会"遮蔽"成员变量）。

## 5. 递归

方法调用自己。必须有 **终止条件（基例）** 并且**每次向基例靠近**。

```java
static long factorial(int n) {
    if (n <= 1) return 1;            // 基例
    return n * factorial(n - 1);     // 递归
}

static int fib(int n) {              // 斐波那契：简洁但指数级慢
    return n < 2 ? n : fib(n - 1) + fib(n - 2);
}

// 带记忆化的版本：O(n)
static long fibMemo(int n, long[] memo) {
    if (n < 2) return n;
    if (memo[n] != 0) return memo[n];
    return memo[n] = fibMemo(n - 1, memo) + fibMemo(n - 2, memo);
}
```

递归太深会抛 `StackOverflowError`。能用循环清晰表达的，优先循环；树/图/分治/回溯等天然递归的问题用递归。

**汉诺塔**示例（体会"相信递归"的思维）：

```java
static void hanoi(int n, char from, char via, char to) {
    if (n == 0) return;
    hanoi(n - 1, from, to, via);                          // 先把上面 n-1 个移到中转柱
    System.out.println("移动盘 " + n + "：" + from + " → " + to);
    hanoi(n - 1, via, from, to);                          // 再把 n-1 个移到目标柱
}
```

## 6. 静态方法 vs 实例方法（预告）

- `static` 方法属于类，可直接 `类名.方法()` 调用；**不能直接访问实例成员**。
- 实例方法属于对象，需要 `new` 出对象后调用。第 09 章详述。在 `main`（static）里调用本类的非 static 方法，会报 `non-static method cannot be referenced from a static context`。

## 7. 小技巧

```java
static String describe(int n) {            // 多返回值用对象/record 包装
    return n % 2 == 0 ? "偶数" : "奇数";
}
record MinMax(int min, int max) { }        // Java 16+ 的 record，第 11 章
static MinMax range(int[] a) { ... }
```

## 小结

- 参数传递：**值传递**，引用类型传的是引用的副本。
- 重载看参数列表；递归要有基例。
- 方法要短小、单一职责、命名清晰。

**练习**：`exercises/arrays`（含方法与递归）、`exercises/algorithms`

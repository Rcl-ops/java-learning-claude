# 06 · 数组

**学习目标**：会声明、创建、遍历数组；理解数组是引用类型；会用 `Arrays` 工具类。

## 1. 创建数组

数组 = **长度固定、元素类型相同、连续存储**的容器。

```java
int[] a = new int[5];                 // 5 个元素，默认值 0
String[] names = new String[3];       // 默认值 null
int[] b = {1, 2, 3};                  // 静态初始化（声明时才能用简写）
int[] c = new int[]{4, 5, 6};
```

默认值：数值类型 `0`、`boolean` 为 `false`、`char` 为 `\u0000`、引用类型 `null`。

## 2. 访问与遍历

```java
int[] arr = {10, 20, 30};
System.out.println(arr[0]);          // 下标从 0 开始
System.out.println(arr.length);      // 长度是属性，不是方法（String 的 length() 才是方法）
arr[2] = 99;

for (int i = 0; i < arr.length; i++) System.out.println(arr[i]);
for (int x : arr) System.out.println(x);
// arr[3] → ArrayIndexOutOfBoundsException: Index 3 out of bounds for length 3
```

## 3. 数组是引用类型

```java
int[] x = {1, 2, 3};
int[] y = x;          // 复制的是引用，x 和 y 指向同一个数组
y[0] = 100;
System.out.println(x[0]);   // 100

int[] z = Arrays.copyOf(x, x.length);   // 真正复制内容
```

方法参数传递数组时，方法内修改元素会影响调用方的数组（见第 07 章"值传递"）。

## 4. 二维数组

```java
int[][] grid = new int[3][4];            // 3 行 4 列
int[][] m = {{1, 2, 3}, {4, 5, 6}};
System.out.println(m[1][2]);             // 6
for (int[] row : m) {
    for (int v : row) System.out.print(v + " ");
    System.out.println();
}
int[][] jagged = new int[3][];           // 不规则数组
jagged[0] = new int[]{1};
jagged[1] = new int[]{1, 2};
jagged[2] = new int[]{1, 2, 3};
```

## 5. Arrays 工具类（`java.util.Arrays`）

```java
int[] a = {5, 3, 9, 1};
Arrays.sort(a);                              // [1, 3, 5, 9]
System.out.println(Arrays.toString(a));      // 打印数组内容（直接 println(a) 只会打印地址）
int idx = Arrays.binarySearch(a, 5);         // 二分查找（必须先排序）→ 2
int[] copy = Arrays.copyOf(a, 6);            // 扩容复制，补 0
int[] part = Arrays.copyOfRange(a, 1, 3);    // [3, 5]
Arrays.fill(a, 7);                           // 全部填 7
boolean same = Arrays.equals(a, copy);       // 比较内容
System.out.println(Arrays.deepToString(new int[][]{{1}, {2, 3}})); // [[1], [2, 3]]
```

排序对象数组并自定义规则：

```java
String[] words = {"banana", "kiwi", "apple"};
Arrays.sort(words, (p, q) -> p.length() - q.length());   // 按长度排序（Lambda 见第 15 章）
```

## 6. 可变参数（varargs）

```java
static int sum(int... nums) {          // nums 在方法内就是 int[]
    int s = 0;
    for (int n : nums) s += n;
    return s;
}
sum();          // 0
sum(1, 2, 3);   // 6
```

可变参数必须是**最后一个参数**，且一个方法只能有一个。

## 7. 常见算法练手

```java
// 反转
for (int i = 0, j = a.length - 1; i < j; i++, j--) {
    int t = a[i]; a[i] = a[j]; a[j] = t;
}
// 冒泡排序
for (int i = 0; i < a.length - 1; i++)
    for (int j = 0; j < a.length - 1 - i; j++)
        if (a[j] > a[j + 1]) { int t = a[j]; a[j] = a[j + 1]; a[j + 1] = t; }
```

## 8. 局限与替代

数组长度固定，增删元素麻烦。实际开发中更常用 **`ArrayList`**（第 14 章）。数组仍用于：性能敏感代码、`main(String[] args)`、可变参数、底层 API。

## 小结

- 下标从 0 开始，越界抛 `ArrayIndexOutOfBoundsException`。
- 数组变量是引用，`=` 不会复制内容；用 `Arrays.copyOf`。
- 打印用 `Arrays.toString` / `deepToString`。

**练习**：`exercises/arrays`

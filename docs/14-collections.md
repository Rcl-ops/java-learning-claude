# 14 · 集合框架

**学习目标**：掌握 `List`/`Set`/`Map`/`Queue` 的常用实现与选择；会遍历、排序、去重、统计；理解 `equals/hashCode` 对哈希集合的影响。

## 1. 体系总览

```
Iterable
└── Collection
    ├── List     有序、可重复      ArrayList  LinkedList
    ├── Set      无序/去重         HashSet  LinkedHashSet(保插入序)  TreeSet(排序)
    └── Queue    队列              ArrayDeque  PriorityQueue   (Deque：双端队列)

Map（独立于 Collection）  键→值，键唯一
    HashMap  LinkedHashMap(保插入序)  TreeMap(按键排序)  ConcurrentHashMap(并发)
```

接口类型声明变量，实现类创建对象：`List<String> list = new ArrayList<>();`

## 2. List

```java
List<String> list = new ArrayList<>();
list.add("a");
list.add("b");
list.add(1, "x");            // 在下标 1 插入
list.get(0);
list.set(0, "A");
list.remove(0);              // 按下标删除
list.remove("b");            // 按元素删除
list.size(); list.isEmpty(); list.contains("x"); list.indexOf("x");
list.sort(null);                       // 自然顺序
list.sort(Comparator.reverseOrder());

List<Integer> nums = new ArrayList<>(List.of(1, 2, 3, 4));
nums.remove(Integer.valueOf(3));       // 删除元素 3；nums.remove(3) 是按下标！
nums.removeIf(n -> n % 2 == 0);        // 条件删除
```

| | ArrayList | LinkedList |
|---|---|---|
| 底层 | 动态数组 | 双向链表 |
| 随机访问 `get(i)` | O(1) | O(n) |
| 头部插入/删除 | O(n) | O(1) |
| 结论 | **默认选它** | 极少需要；队列用 `ArrayDeque` |

### 不可变集合（Java 9+）

```java
List<String> a = List.of("x", "y");        // 不可修改，不允许 null；add 抛 UnsupportedOperationException
Set<Integer> s = Set.of(1, 2, 3);
Map<String, Integer> m = Map.of("a", 1, "b", 2);
List<String> copy = List.copyOf(someList);
```

## 3. Set

```java
Set<String> set = new HashSet<>();
set.add("a"); set.add("a");         // 第二次返回 false
set.size();                         // 1
// HashSet 无序；LinkedHashSet 保持插入顺序；TreeSet 按自然/自定义顺序排序

Set<Integer> uniq = new TreeSet<>(List.of(5, 1, 3, 1));   // [1, 3, 5]
```

Set 常用于：去重、成员判断（`contains` 平均 O(1)）、集合运算：

```java
Set<Integer> a = new HashSet<>(List.of(1, 2, 3)), b = new HashSet<>(List.of(2, 3, 4));
a.retainAll(b);    // 交集
a.addAll(b);       // 并集
a.removeAll(b);    // 差集
```

## 4. Map

```java
Map<String, Integer> score = new HashMap<>();
score.put("Tom", 90);
score.put("Amy", 85);
score.get("Tom");                         // 90；不存在返回 null
score.getOrDefault("Bob", 0);             // 0
score.containsKey("Amy");
score.putIfAbsent("Tom", 100);            // 已存在不覆盖
score.remove("Amy");

// 遍历
for (Map.Entry<String, Integer> e : score.entrySet()) {
    System.out.println(e.getKey() + " -> " + e.getValue());
}
score.forEach((k, v) -> System.out.println(k + "=" + v));
for (String k : score.keySet()) { ... }
for (int v : score.values()) { ... }

// 经典：计数
Map<String, Integer> freq = new HashMap<>();
for (String w : words) freq.merge(w, 1, Integer::sum);     // 等价：freq.put(w, freq.getOrDefault(w, 0) + 1)

// 经典：分组
Map<Integer, List<String>> byLen = new HashMap<>();
for (String w : words) byLen.computeIfAbsent(w.length(), k -> new ArrayList<>()).add(w);
```

选择：

| 实现 | 顺序 | 复杂度 | 备注 |
|------|------|--------|------|
| `HashMap` | 无序 | O(1) 平均 | 默认选择，键需正确的 `hashCode/equals` |
| `LinkedHashMap` | 插入序（或访问序） | O(1) | 实现 LRU 缓存 |
| `TreeMap` | 按键排序 | O(log n) | 范围查询 `floorKey`、`subMap` |
| `ConcurrentHashMap` | 无序 | O(1) | 多线程场景 |

> 作为 `HashMap` 的 key，或放进 `HashSet` 的对象，必须**正确重写 `equals` 与 `hashCode`**，且参与计算的字段**不能在放入后被修改**（所以 key 最好不可变，如 `String`、`record`）。

## 5. Queue / Deque / PriorityQueue

```java
Deque<Integer> stack = new ArrayDeque<>();   // 栈：后进先出
stack.push(1); stack.push(2);
stack.pop();                                 // 2
stack.peek();

Queue<String> q = new ArrayDeque<>();        // 队列：先进先出
q.offer("a"); q.offer("b");
q.poll();                                    // "a"（空队列返回 null；remove() 则抛异常）

PriorityQueue<Integer> pq = new PriorityQueue<>();            // 小顶堆，按优先级出队
pq.add(5); pq.add(1); pq.add(3);
pq.poll();                                                    // 1
PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Comparator.reverseOrder());
```

> 不要再使用古老的 `Stack`、`Vector`、`Hashtable`。

## 6. 遍历与安全删除

```java
for (String s : list) {
    if (s.equals("x")) list.remove(s);       // ❌ ConcurrentModificationException
}

list.removeIf(s -> s.equals("x"));           // ✅ 最简洁

Iterator<String> it = list.iterator();       // ✅ 迭代器删除
while (it.hasNext()) {
    if (it.next().equals("x")) it.remove();
}
```

## 7. 排序：Comparable 与 Comparator

```java
// 自然顺序：让类实现 Comparable
record Student(String name, int score) implements Comparable<Student> {
    @Override public int compareTo(Student o) { return Integer.compare(score, o.score); }
}

// 外部比较器：灵活，可多种排序
List<Student> list = new ArrayList<>(List.of(new Student("A", 80), new Student("B", 95), new Student("C", 80)));
list.sort(Comparator.comparingInt(Student::score).reversed()
                    .thenComparing(Student::name));        // 分数降序，同分按名字升序
Collections.sort(list);                                    // 使用 compareTo
```

## 8. Collections 与 Java 21 的有序集合

```java
Collections.reverse(list); Collections.shuffle(list); Collections.max(list);
Collections.unmodifiableList(list); Collections.frequency(list, x); Collections.swap(list, 0, 1);

// Java 21：SequencedCollection
list.getFirst(); list.getLast(); list.reversed(); list.addFirst(x);
```

## 9. 选择指南

| 需求 | 选择 |
|------|------|
| 有序、按下标访问 | `ArrayList` |
| 去重 | `HashSet`（要顺序：`LinkedHashSet`；要排序：`TreeSet`） |
| 键值对 | `HashMap`（要顺序/排序见上表） |
| 栈 / 队列 / 双端队列 | `ArrayDeque` |
| 总是取最大/最小 | `PriorityQueue` |
| 多线程共享 | `ConcurrentHashMap`、`CopyOnWriteArrayList`、`BlockingQueue` |

## 小结

- 先想"需要什么特性"（有序？去重？键值？），再选实现类。
- Map 的 `merge` / `computeIfAbsent` 是写出简洁代码的利器。
- 哈希集合依赖 `equals/hashCode`；遍历时修改集合用 `removeIf` / 迭代器。

**练习**：`exercises/collections`

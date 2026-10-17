# 10 · 继承与多态

**学习目标**：掌握 `extends`、`super`、重写、向上/向下转型与多态；会重写 `equals` / `hashCode`；懂得"组合优于继承"。

## 1. 继承

```java
class Animal {
    protected String name;
    Animal(String name) { this.name = name; }
    void eat() { System.out.println(name + " 在吃东西"); }
}

class Dog extends Animal {                 // Dog 是 Animal 的子类（Java 单继承：只有一个父类）
    Dog(String name) {
        super(name);                       // 调用父类构造器，必须是第一条语句
    }
    void bark() { System.out.println(name + " 汪汪"); }
}
```

- 子类继承父类的非 `private` 成员（构造器不继承）。
- 所有类都（直接或间接）继承 `java.lang.Object`。
- 子类构造器第一行若没有写 `this(...)` / `super(...)`，编译器自动加 `super()`；如果父类没有无参构造器就会编译错误。

## 2. 方法重写（Override）

子类提供与父类**同签名**方法的新实现：

```java
class Cat extends Animal {
    Cat(String name) { super(name); }

    @Override                               // 强烈建议加，编译器帮你检查是否真的重写成功
    void eat() {
        super.eat();                        // 调用父类版本
        System.out.println(name + " 优雅地吃鱼");
    }
}
```

重写规则：方法名、参数列表相同；返回类型相同或是其子类型；访问权限**不能更严格**；不能重写 `private`/`static`/`final` 方法。

> 区分：**重载（Overload）** = 同类中名字相同、参数不同，编译期决定；**重写（Override）** = 子类改写父类方法，运行期决定。

## 3. 多态

> 同一个引用类型调用同一个方法，因实际对象不同而表现不同。

```java
Animal a1 = new Dog("旺财");     // 向上转型：父类引用指向子类对象（自动）
Animal a2 = new Cat("咪咪");
Animal[] zoo = {a1, a2};
for (Animal a : zoo) a.eat();     // 运行时根据真实类型调用对应的 eat()

// a1.bark();                     // ❌ 编译错误：Animal 类型上没有 bark
if (a1 instanceof Dog d) {        // 向下转型（模式匹配写法）
    d.bark();
}
Dog d2 = (Dog) a1;                // 传统强转：类型不符会 ClassCastException
```

多态三要素：**继承/实现 + 重写 + 父类引用指向子类对象**。
**字段和静态方法没有多态**，只有实例方法有。

多态的价值：写出面向"抽象"而不是面向"具体"的代码，新增子类不需要修改调用方（开闭原则）：

```java
static double totalArea(List<Shape> shapes) {   // 不关心具体是圆还是矩形
    double s = 0;
    for (Shape sh : shapes) s += sh.area();
    return s;
}
```

## 4. final

```java
final int x = 1;                 // 变量不可重新赋值
final class Util { }             // 类不可被继承（如 String）
class A { final void f() { } }   // 方法不可被重写
```

`final` 引用变量：引用不能变，但对象内容仍可变（`final List<String> l = new ArrayList<>(); l.add("x");` 是合法的）。

## 5. Object 类的关键方法

| 方法 | 作用 |
|------|------|
| `toString()` | 对象的字符串描述，默认 `类名@哈希` |
| `equals(Object)` | 逻辑相等判断，默认比较引用 |
| `hashCode()` | 哈希值，用于 `HashMap`/`HashSet` |
| `getClass()` | 运行时类型 |

### equals 与 hashCode 必须一起重写

```java
public class Point {
    private final int x, y;
    public Point(int x, int y) { this.x = x; this.y = y; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Point p)) return false;     // 同时处理 null
        return x == p.x && y == p.y;
    }

    @Override
    public int hashCode() { return java.util.Objects.hash(x, y); }

    @Override
    public String toString() { return "(" + x + "," + y + ")"; }
}
```

**契约**：`a.equals(b)` 为 true ⇒ `a.hashCode() == b.hashCode()`。
只重写 `equals` 不重写 `hashCode`，会导致"相等的对象在 `HashSet` 里放两份"。（`record` 自动帮你生成，见第 11 章。）

## 6. 组合优于继承

继承表达 **is-a**（狗是动物）；如果只是想"复用某些功能"，用 **has-a（组合）**：

```java
// ❌ 为了用 ArrayList 的方法而继承，导致 Stack"是一个" ArrayList，暴露了不该有的方法
class MyStack<T> extends ArrayList<T> { }

// ✅ 组合：内部持有 List，只暴露需要的方法
class MyStack<T> {
    private final List<T> data = new ArrayList<>();
    void push(T t) { data.add(t); }
    T pop() { return data.remove(data.size() - 1); }
}
```

继承耦合紧、层级深了难维护；优先考虑接口 + 组合。

## 7. 常见错误

| 问题 | 原因 |
|------|------|
| `ClassCastException` | 强转前没用 `instanceof` 检查 |
| 重写时漏写 `@Override`，结果其实是重载 | 总是加 `@Override` |
| 构造器里调用可被重写的方法 | 子类字段尚未初始化，容易出现意外的 `null`/0 |
| 只重写 `equals` 没重写 `hashCode` | 两者必须成对 |

## 小结

- `extends` 单继承；`super` 访问父类；`@Override` 重写。
- 多态：父类引用 + 子类对象 + 重写 → 运行时决定行为。
- `equals/hashCode/toString` 常需重写；优先组合而非继承。

**练习**：`exercises/oop`

# 19 · 注解与反射

**学习目标**：读懂 `@Override`、`@Mod`、`@SubscribeEvent` 这类注解；会自定义注解并用反射读取；理解反射的用途与代价。

## 1. 注解（Annotation）

注解是附加在代码上的**元数据**，本身不改变逻辑，由编译器、工具或运行时框架读取。

### 常见内置注解

| 注解 | 作用 |
|------|------|
| `@Override` | 声明重写，编译器检查 |
| `@Deprecated` | 标记已过时 |
| `@SuppressWarnings("unchecked")` | 抑制警告 |
| `@FunctionalInterface` | 声明函数式接口，编译器检查只有一个抽象方法 |
| `@SafeVarargs` | 声明泛型可变参数安全 |

框架里无处不在：Spring 的 `@Service`、JUnit 的 `@Test`、NeoForge 的 `@Mod`、`@SubscribeEvent`。

## 2. 自定义注解

```java
import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)        // 保留到运行时，才能用反射读取（SOURCE / CLASS / RUNTIME）
@Target(ElementType.METHOD)                // 可以标注在什么上（TYPE、FIELD、METHOD、PARAMETER ...）
public @interface Check {
    String name() default "";              // 注解的"属性"
    int priority() default 5;
}
```

元注解：`@Retention`（保留策略）、`@Target`（适用目标）、`@Documented`、`@Inherited`、`@Repeatable`。

## 3. 反射（Reflection）

**在运行时检查和操作类、方法、字段**，即使编译时不知道它们。

```java
Class<?> c1 = String.class;                    // ① 类字面量
Class<?> c2 = "abc".getClass();                // ② 对象
Class<?> c3 = Class.forName("java.util.ArrayList");   // ③ 全限定名（可能抛 ClassNotFoundException）

c3.getSimpleName(); c3.getName(); c3.getSuperclass(); c3.getInterfaces();
Object list = c3.getDeclaredConstructor().newInstance();   // 创建对象

Method m = c3.getMethod("add", Object.class);
m.invoke(list, "hello");                        // 调用方法

class Person { private String name = "Tom"; private void secret() { System.out.println("secret"); } }
Field f = Person.class.getDeclaredField("name");
f.setAccessible(true);                          // 突破 private（受模块系统限制）
f.set(person, "Amy");
Method s = Person.class.getDeclaredMethod("secret");
s.setAccessible(true); s.invoke(person);
```

## 4. 实战：迷你"注解驱动测试框架"

这就是 JUnit / NeoForge 事件系统的核心原理——扫描带注解的方法并调用：

```java
public class MiniTest {
    @Retention(RetentionPolicy.RUNTIME) @Target(ElementType.METHOD)
    @interface Check { String value() default ""; }

    static class Calc {
        @Check("加法") public void testAdd() { if (1 + 1 != 2) throw new AssertionError(); }
        @Check("故意失败") public void testFail() { throw new AssertionError("boom"); }
        public void helper() { }                                   // 没标注，不会被执行
    }

    public static void main(String[] args) throws Exception {
        Object target = new Calc();
        for (Method m : target.getClass().getDeclaredMethods()) {
            Check c = m.getAnnotation(Check.class);
            if (c == null) continue;
            try {
                m.invoke(target);
                System.out.println("✔ " + c.value());
            } catch (java.lang.reflect.InvocationTargetException e) {
                System.out.println("✘ " + c.value() + " : " + e.getCause().getMessage());
            }
        }
    }
}
```

> 联想 NeoForge：`@Mod("hellomod")` 告诉模组加载器"这个类是模组入口"；加载器通过反射找到它并调用其构造器。`@SubscribeEvent` 标注的方法会被反射注册为事件监听器。

## 5. 反射的用途、代价与限制

用途：框架（依赖注入、ORM、序列化、测试）、插件系统、IDE 工具。
代价：
- 慢于直接调用，且失去编译期类型检查（改名后才在运行时报错）；
- 破坏封装，`setAccessible(true)` 在 Java 9+ 受**模块系统**限制（`InaccessibleObjectException`）；
- 业务代码里**尽量不要用**，除非在写框架。

## 6. 动态代理（了解）

```java
Greeter proxy = (Greeter) Proxy.newProxyInstance(
        Greeter.class.getClassLoader(), new Class<?>[]{Greeter.class},
        (p, method, args) -> { System.out.println("调用前: " + method.getName()); return null; });
```

Spring AOP、MyBatis Mapper 都基于它。

## 小结

- 注解 = 元数据；`@Retention(RUNTIME)` 才能反射读取。
- 反射让程序在运行时探查并调用代码，是框架的基石，但应在业务代码中克制使用。

**练习**：`exercises/functional`（扩展思考）

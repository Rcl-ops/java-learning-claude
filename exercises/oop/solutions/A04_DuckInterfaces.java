package oop.solutions;

/**
 * 【参考答案】Q04_DuckInterfaces：接口：会飞会游的动物
 *
 * 解题思路：
 * 能力用接口表达，类按需实现多个接口，调用方根据能力判断而不是根据具体类。
 *
 * 运行：java -cp out oop.solutions.A04_DuckInterfaces
 */
public class A04_DuckInterfaces {
    interface Flyable { void fly(); }
    interface Swimmable { void swim(); }
    interface Walkable { void walk(); }

    static class Duck implements Flyable, Swimmable, Walkable {
        public void fly() { System.out.println("鸭子扇翅膀飞"); }
        public void swim() { System.out.println("鸭子划水"); }
        public void walk() { System.out.println("鸭子摇摇摆摆走"); }
    }
    static class Fish implements Swimmable {
        public void swim() { System.out.println("鱼摆尾游"); }
    }
    static class Dog implements Walkable, Swimmable {
        public void walk() { System.out.println("狗跑"); }
        public void swim() { System.out.println("狗刨"); }
    }
    static class Bird implements Walkable, Flyable {
        public void walk() { System.out.println("鸟蹦跳"); }
        public void fly() { System.out.println("鸟高飞"); }
    }

    static void showOff(Object o) {
        System.out.println("--- " + o.getClass().getSimpleName());
        if (o instanceof Flyable f) f.fly();
        if (o instanceof Swimmable s) s.swim();
        if (o instanceof Walkable w) w.walk();
    }

    public static void main(String[] args) {
        for (Object o : new Object[]{new Duck(), new Fish(), new Dog(), new Bird()}) showOff(o);
    }
}

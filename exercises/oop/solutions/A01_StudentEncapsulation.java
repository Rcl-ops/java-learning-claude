package oop.solutions;

/**
 * 【参考答案】Q01_StudentEncapsulation：封装：学生类
 *
 * 解题思路：
 * 字段私有；所有修改都经过带校验的 setter；构造器复用 setter。
 *
 * 运行：java -cp out oop.solutions.A01_StudentEncapsulation
 */
public class A01_StudentEncapsulation {
    static class Student {
        private String name;
        private int age;
        private int score;

        Student(String name, int age, int score) {
            setName(name); setAge(age); setScore(score);
        }

        String getName() { return name; }
        int getAge() { return age; }
        int getScore() { return score; }

        void setName(String name) {
            if (name == null || name.isBlank()) throw new IllegalArgumentException("姓名不能为空");
            this.name = name;
        }

        void setAge(int age) {
            if (age < 0 || age > 150) throw new IllegalArgumentException("年龄非法：" + age);
            this.age = age;
        }

        void setScore(int score) {
            if (score < 0 || score > 100) throw new IllegalArgumentException("成绩非法：" + score);
            this.score = score;
        }

        @Override public String toString() { return "Student{name=" + name + ", age=" + age + ", score=" + score + "}"; }
    }

    public static void main(String[] args) {
        Student s = new Student("小明", 18, 90);
        System.out.println(s);
        try {
            s.setScore(120);
        } catch (IllegalArgumentException e) {
            System.out.println("捕获：" + e.getMessage());
        }
    }
}

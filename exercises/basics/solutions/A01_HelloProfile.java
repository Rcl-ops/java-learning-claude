package basics.solutions;

/**
 * 【参考答案】Q01_HelloProfile：打印个人名片
 *
 * 解题思路：
 * 每种类型选对变量类型，printf 的格式符与参数类型要对应。
 *
 * 运行：java -cp out basics.solutions.A01_HelloProfile
 */
public class A01_HelloProfile {
    public static void main(String[] args) {
        String name = "小明";
        int age = 18;
        double height = 1.756;
        boolean student = true;
        System.out.println("===== 个人名片 =====");
        System.out.printf("姓名：%s%n", name);
        System.out.printf("年龄：%d 岁%n", age);
        System.out.printf("身高：%.2f 米%n", height);
        System.out.printf("学生：%b%n", student);
    }
}

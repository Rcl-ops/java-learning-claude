package exceptions.problems;

/**
 * 【异常与文件 · 第 2 题】自定义异常与异常链    难度：★★☆☆☆
 *
 * 题目描述：
 * (1) 定义受检异常 ValidationException（带字段名 field）；(2) 实现 validateUser(String name, int age)：name 为空或 age 不在 0~150 抛出；(3) 再写 registerUser，捕获底层异常并包装成 RuntimeException("注册失败", cause) 重新抛出，在 main 中打印完整异常链（getCause）。
 *
 * 提示：
 * 自定义异常构造器要调用 super(message)；包装时务必传入 cause。
 *
 * 扩展思考（获取灵感）：
 * 受检 vs 非受检：设计业务异常时你会选哪个？为什么 Spring 等框架偏爱非受检异常？
 *
 * 参考答案：solutions/A02_CustomException.java
 */
public class Q02_CustomException {
    // TODO: ValidationException

    public static void main(String[] args) {
        // TODO
    }
}

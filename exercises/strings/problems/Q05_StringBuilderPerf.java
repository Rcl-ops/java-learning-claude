package strings.problems;

/**
 * 【字符串 · 第 5 题】拼接性能对比    难度：★★☆☆☆
 *
 * 题目描述：
 * 分别用 String +=、StringBuilder、String.join / Collectors.joining 拼接 10 万个数字，用 System.nanoTime() 比较耗时，并思考原因。
 *
 * 提示：
 * 先用较小的次数（如 2 万）测试 String +=，否则可能运行很久。
 *
 * 扩展思考（获取灵感）：
 * StringBuilder 预分配容量 new StringBuilder(capacity) 有多大影响？JIT 预热对测量有什么影响？
 *
 * 参考答案：暂无（鼓励先自己实现；做完可对照同类题的答案思路）
 */
public class Q05_StringBuilderPerf {
    public static void main(String[] args) {
        // TODO: 在这里编写你的代码
    }
}

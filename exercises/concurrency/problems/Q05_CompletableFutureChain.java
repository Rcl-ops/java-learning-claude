package concurrency.problems;

/**
 * 【多线程与并发 · 第 5 题】CompletableFuture 编排    难度：★★★☆☆
 *
 * 题目描述：
 * 模拟：并行查询"用户信息"（耗时 300ms）和"订单列表"（耗时 500ms），两者都完成后合并成一条结果；任一失败则返回默认值（exceptionally）。测量总耗时应接近 500ms 而不是 800ms。
 *
 * 提示：
 * supplyAsync + thenCombine；Thread.sleep 模拟耗时（注意 InterruptedException）。
 *
 * 扩展思考（获取灵感）：
 * allOf / anyOf 的使用场景？超时控制 orTimeout / completeOnTimeout。
 *
 * 参考答案：暂无（鼓励先自己实现；做完可对照同类题的答案思路）
 */
public class Q05_CompletableFutureChain {
    public static void main(String[] args) {
        // TODO: 在这里编写你的代码
    }
}

package flow.problems;

/**
 * 【流程控制 · 第 7 题】猜数字游戏    难度：★★★☆☆
 *
 * 题目描述：
 * 程序随机生成 1~100 的数字，用户反复输入猜测，提示"太大/太小"，猜中后显示用了几次。最多 7 次，超过则失败并公布答案。输入非数字时提示重新输入而不是崩溃。
 *
 * 提示：
 * Scanner + while 循环；用 hasNextInt() 判断输入是否合法；ThreadLocalRandom.current().nextInt(1, 101)。
 *
 * 扩展思考（获取灵感）：
 * 用二分查找思想，7 次足够猜中 1~100 吗？为什么？让电脑扮演猜的人（你想一个数，电脑二分猜）。
 *
 * 参考答案：暂无（鼓励先自己实现；做完可对照同类题的答案思路）
 */
public class Q07_GuessNumber {
    public static void main(String[] args) {
        // TODO: 在这里编写你的代码
    }
}

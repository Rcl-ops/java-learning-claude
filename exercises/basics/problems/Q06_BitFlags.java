package basics.problems;

/**
 * 【基础：变量、类型、运算符 · 第 6 题】位运算：权限标志位    难度：★★★☆☆
 *
 * 题目描述：
 * 用一个 int 表示用户权限：READ=1, WRITE=2, EXEC=4, DELETE=8。
 * 实现方法：grant(perm, flag)、revoke(perm, flag)、has(perm, flag)、toggle(perm, flag)、describe(perm)（返回如 "READ|WRITE"）。
 *
 * 示例：
 * grant(0, READ|WRITE) → 3；has(3, WRITE) → true；revoke(3, WRITE) → 1
 *
 * 提示：
 * 授予用 |，撤销用 & ~，检查用 &，翻转用 ^。
 *
 * 扩展思考（获取灵感）：
 * 用 enum + EnumSet 实现同样功能，对比两种写法的可读性与类型安全。
 *
 * 参考答案：暂无（鼓励先自己实现；做完可对照同类题的答案思路）
 */
public class Q06_BitFlags {
    public static void main(String[] args) {
        // TODO: 在这里编写你的代码
    }
}

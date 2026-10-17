package exceptions.problems;

/**
 * 【异常与文件 · 第 5 题】递归复制目录    难度：★★★☆☆
 *
 * 题目描述：
 * 实现 copyDir(Path src, Path dst)：把目录及其所有子目录和文件复制到目标位置，保持结构。使用 Files.walk 或 Files.walkFileTree。统计复制的文件数和总字节数。
 *
 * 提示：
 * 先用 relativize 得到相对路径，再 resolve 到目标；遇到目录 createDirectories，文件 copy。
 *
 * 扩展思考（获取灵感）：
 * 如何安全删除一个目录树？如何避免符号链接造成的无限循环？
 *
 * 参考答案：暂无（鼓励先自己实现；做完可对照同类题的答案思路）
 */
public class Q05_CopyDirectory {
    public static void main(String[] args) {
        // TODO: 在这里编写你的代码
    }
}

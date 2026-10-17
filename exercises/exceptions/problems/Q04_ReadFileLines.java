package exceptions.problems;

import java.io.IOException;
import java.nio.file.*;
import java.util.*;
/**
 * 【异常与文件 · 第 4 题】读取文件并统计    难度：★★☆☆☆
 *
 * 题目描述：
 * 先用 Files.writeString 写一个临时文本文件（多行含中文），再用 Files 读回：统计行数、非空行数、总字符数、最长的一行；文件不存在时给出友好提示。
 *
 * 提示：
 * Files.createTempFile / Files.readAllLines；try-catch IOException；UTF-8。
 *
 * 扩展思考（获取灵感）：
 * 如果文件有 10GB 怎么办？（Files.lines 流式 / BufferedReader）
 *
 * 参考答案：solutions/A04_ReadFileLines.java
 */
public class Q04_ReadFileLines {
    public static void main(String[] args) throws IOException {
        Path file = Files.createTempFile("demo", ".txt");
        Files.writeString(file, "第一行\n\nhello world\n最长的一行在这里！\n");
        // TODO: 统计并打印
    }
}

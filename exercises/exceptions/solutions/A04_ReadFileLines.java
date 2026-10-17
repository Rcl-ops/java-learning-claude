package exceptions.solutions;

import java.io.IOException;
import java.nio.file.*;
import java.util.*;
/**
 * 【参考答案】Q04_ReadFileLines：读取文件并统计
 *
 * 解题思路：
 * readAllLines 得到行列表，用循环/Stream 统计；用 NoSuchFileException 处理不存在的文件。
 *
 * 运行：java -cp out exceptions.solutions.A04_ReadFileLines
 */
public class A04_ReadFileLines {
    public static void main(String[] args) throws IOException {
        Path file = Files.createTempFile("demo", ".txt");
        Files.writeString(file, "第一行\n\nhello world\n最长的一行在这里！\n");
        try {
            List<String> lines = Files.readAllLines(file);
            long nonEmpty = lines.stream().filter(l -> !l.isBlank()).count();
            int chars = lines.stream().mapToInt(String::length).sum();
            String longest = lines.stream().max(Comparator.comparingInt(String::length)).orElse("");
            System.out.printf("行数=%d 非空行=%d 字符数=%d%n最长行：%s%n", lines.size(), nonEmpty, chars, longest);
        } finally {
            Files.deleteIfExists(file);
        }
        try {
            Files.readAllLines(Path.of("no-such-file.txt"));
        } catch (NoSuchFileException e) {
            System.out.println("文件不存在：" + e.getMessage());
        }
    }
}

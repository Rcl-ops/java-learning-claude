package oop.problems;

/**
 * 【面向对象 · 第 7 题】综合：图书馆系统    难度：★★★★☆
 *
 * 题目描述：
 * 设计一个简易图书馆：Book(isbn, title, author, 是否在架)、Member(id, name, 已借书籍上限 3 本)、Library(书目、会员)。支持：添加图书/会员、借书、还书、按作者/书名关键字搜索、列出某会员已借书籍。非法操作用合适的异常表达。
 *
 * 提示：
 * 先画出类图和关系；Library 内部使用 Map<String, Book>；借还操作需要同时更新书和会员两侧的状态。
 *
 * 扩展思考（获取灵感）：
 * 加入借期与逾期罚款（LocalDate）；持久化到文件；加上单元测试；用接口抽象存储层（内存/文件）。
 *
 * 参考答案：暂无（鼓励先自己实现；做完可对照同类题的答案思路）
 */
public class Q07_LibrarySystem {
    public static void main(String[] args) {
        // TODO: 在这里编写你的代码
    }
}

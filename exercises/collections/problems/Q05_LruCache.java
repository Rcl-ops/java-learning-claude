package collections.problems;

import java.util.*;
/**
 * 【集合 · 第 5 题】LRU 缓存    难度：★★★☆☆
 *
 * 题目描述：
 * 实现容量固定的 LRU（最近最少使用）缓存：get(key)、put(key, value)；超过容量时淘汰最久未使用的条目。要求 O(1)。
 *
 * 提示：
 * LinkedHashMap 的 accessOrder=true 构造 + 重写 removeEldestEntry。
 *
 * 扩展思考（获取灵感）：
 * 不使用 LinkedHashMap，用 HashMap + 双向链表手写；线程安全版本如何做？
 *
 * 参考答案：solutions/A05_LruCache.java
 */
public class Q05_LruCache {
    static class LruCache<K, V> {
        // TODO
    }

    public static void main(String[] args) {
        // TODO：容量 2：put(1,A) put(2,B) get(1) put(3,C) → 2 被淘汰
    }
}

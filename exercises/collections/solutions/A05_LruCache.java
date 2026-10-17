package collections.solutions;

import java.util.*;
/**
 * 【参考答案】Q05_LruCache：LRU 缓存
 *
 * 解题思路：
 * LinkedHashMap 按访问顺序维护链表，removeEldestEntry 返回 true 时自动淘汰队首。
 *
 * 运行：java -cp out collections.solutions.A05_LruCache
 */
public class A05_LruCache {
    static class LruCache<K, V> extends LinkedHashMap<K, V> {
        private final int capacity;

        LruCache(int capacity) {
            super(16, 0.75f, true);              // true：按访问顺序而不是插入顺序
            this.capacity = capacity;
        }

        @Override
        protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
            return size() > capacity;
        }
    }

    public static void main(String[] args) {
        LruCache<Integer, String> cache = new LruCache<>(2);
        cache.put(1, "A");
        cache.put(2, "B");
        cache.get(1);              // 访问 1，使 2 成为最久未使用
        cache.put(3, "C");         // 淘汰 2
        System.out.println(cache.keySet());   // [1, 3]
    }
}

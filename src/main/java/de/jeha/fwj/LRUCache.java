package de.jeha.fwj;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class LRUCache<K, V> {

    static class CacheEntry<K, V> {
        final K key ;
        final V value;

        public CacheEntry(K key, V value) {
            this.key= key;
            this.value = value;
        }

        @Override
        public String toString() {
            return "key=" + key.toString() + ",value=" + value.toString();
        }
    }

    private int capacity;
    private Map<K, CacheEntry<K, V>> cache = new HashMap<>();
    private LinkedList<CacheEntry<K, V>> data = new LinkedList<>();

    public LRUCache(int capacity) {
        this.capacity = capacity;
    }

    public V get(K key) {
        CacheEntry<K, V> entry = cache.get(key);
        if (entry == null) {
            return null;
        }

        moveToFront(entry);

        return entry.value;
    }

    public void put(K key, V value) {
        CacheEntry<K, V> entry = cache.get(key);

        if (entry != null) {
            moveToFront(entry);
        } else {
            if (cache.size() == capacity) {
                CacheEntry<K, V> last = data.getLast();
                System.out.println("Evict " + last);
                cache.remove(last.key);
                data.removeLast();
            }
            entry = new CacheEntry<>(key, value);

            cache.put(key, entry);
            data.addFirst(entry);
        }
    }

    public void remove(K key) {
        CacheEntry<K, V> entry = cache.get(key);

        if (entry != null) {
            cache.remove(key);
            data.remove(entry);
        }
    }

    private void moveToFront(CacheEntry<K, V> entry) {
        data.remove(entry);
        data.addFirst(entry);
    }

    @Override
    public String toString() {
        return cache.toString();
    }

    public static void main(String... args) {
        LRUCache<String, String> cache = new LRUCache<>(3);

        cache.put("1", "1");
        cache.put("2", "2");
        cache.put("3", "3");
        cache.put("4", "4");
        cache.put("5", "5");
        cache.put("6", "6");

        cache.get("4");
        cache.get("4");
        cache.get("6");

        cache.remove("5");

        cache.put("5","5");

        System.out.println(cache);
    }

}

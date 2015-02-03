package de.jeha.fwj;

import java.util.*;

public class LRUCacheWithSynchronizedLinkedHashMap {

    public static <K, V> Map<K, V> lruCache(final int maxSize) {
        final int initialCapacity = maxSize * 4 / 3;
        final float loadfactor = 0.75f;
        final boolean accessOrder = true;

        return Collections.synchronizedMap(new LinkedHashMap<K, V>(initialCapacity, loadfactor, accessOrder) {

            @Override
            protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
                final boolean remove = size() > maxSize;
                if (remove) {
                    System.out.println("remove " + eldest);
                }
                return remove;
            }
        });
    }

    public static void main(String... args) {
        final Map<Object, Object> lru = LRUCacheWithSynchronizedLinkedHashMap.lruCache(3);

        for (int i = 1; i <= 6; i++) {
            final String s = Integer.toString(i);
            lru.put(s, s);
        }

        System.out.println(lru);
    }
}

package de.jeha.fwj.lru;


public class CacheEntry<K, V> {

    private final K key;
    private final V value;

    public CacheEntry(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "[key=" + key.toString() + ",value=" + value.toString() + "]";
    }
}

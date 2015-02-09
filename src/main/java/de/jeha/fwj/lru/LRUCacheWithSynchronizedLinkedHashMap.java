package de.jeha.fwj.lru;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.*;

public class LRUCacheWithSynchronizedLinkedHashMap {

    public static <K, V> Map<K, V> lruCache(final int maxSize) {
        final int initialCapacity = maxSize * 4 / 3;
        final float loadfactor = 0.75f;
        final boolean accessOrder = true;

        return new LinkedHashMap<K, V>(initialCapacity, loadfactor, accessOrder) {

            @Override
            public synchronized V put(K key, V value) {
                System.out.println("put {" + key + "=" + value + "}");
                return super.put(key, value);
            }

            @Override
            protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
                final boolean remove = size() > maxSize;
                if (remove) {
                    System.out.println("remove " + eldest);
                }
                return remove;
            }
        };
    }

    static final CountDownLatch LATCH = new CountDownLatch(1);

    public static void main(String... args) throws InterruptedException, BrokenBarrierException {
        final Map<Object, Object> lru = LRUCacheWithSynchronizedLinkedHashMap.lruCache(3);

        ExecutorService executorService = Executors.newFixedThreadPool(4);
        executorService.submit(new PutToCacheRunnable("1", lru));
        executorService.submit(new PutToCacheRunnable("2", lru));
        executorService.shutdown();

        LATCH.countDown();

        executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.MILLISECONDS);

        System.out.println(lru);
    }

    static class PutToCacheRunnable implements Runnable {

        final String threadId;
        final Map<Object, Object> cache;

        public PutToCacheRunnable(String threadId, Map<Object, Object> cache) {
            this.threadId = threadId;
            this.cache = cache;
        }

        @Override
        public void run() {
            try {
                LATCH.await();
                System.out.println("Start thread " + threadId);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            for (int i = 1; i <= 6; i++) {
                final String s = Integer.toString(i);
                cache.put(s, threadId + ":" + s);
            }
        }
    }
}

package de.jeha.fwj.guava;

import org.junit.Test;


public class CacheTest {

    @Test
    public void testCache() throws InterruptedException {
        Cache cache = new Cache();

        System.out.println(cache.list());
        System.out.println(cache.list());

        Thread.sleep(600);

        System.out.println(cache.list());
    }
}

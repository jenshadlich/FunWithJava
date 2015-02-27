package de.jeha.fwj.guava;

import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Cache {

    private Supplier<List<String>> supplier = () -> {
        System.out.println("get()");
        return Arrays.asList("foo", "bar");
    };

    private volatile Supplier<List<String>> memoized;

    {
        invalidate();
    }

    public List<String> list() {
        return memoized.get();
    }

    public void invalidate() {
        memoized = Suppliers.memoizeWithExpiration(supplier, 500, TimeUnit.MILLISECONDS);
    }

}

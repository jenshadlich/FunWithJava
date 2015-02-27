package de.jeha.fwj.guava;

import com.google.common.base.Function;
import com.google.common.base.Objects;
import com.google.common.base.Splitter;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Iterables;

import java.util.Arrays;
import java.util.List;

public class Main {

    private static final ImmutableMap<Integer, String> IMAP = ImmutableMap.of(1, "one", 2, "two");

    public static void main(String[] args) {

        System.out.println(Objects.toStringHelper(Main.class).add("foo", "bar"));
        // Preconditions.checkState(false, "%s", new Object[] { Boolean.FALSE });

        List<Double> exVat = Arrays.asList(99., 127., 35.);
        Iterable<Double> incVat = Iterables.transform(exVat, new Function<Double, Double>() {

            public Double apply(Double exVat) {
                return exVat * (1.196);
            }
        });
        System.out.println(incVat);

        for (String s : Splitter.on(',').omitEmptyStrings().trimResults().split(" foo, ,bar, quux,")) {
            System.out.println(s);
        }

        System.out.println(boolean.class);

    }

}

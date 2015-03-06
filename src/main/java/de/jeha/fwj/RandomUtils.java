package de.jeha.fwj;

import java.util.List;
import java.util.Random;

public class RandomUtils {

    private static Random GENERATOR = new Random();

    private static <T> T oneOfN(final List<T> list) {
        return list.size() > 1
                ? list.get(GENERATOR.nextInt(list.size() - 1))
                : list.get(0);
    }

}

package de.jeha.fwj;

import java.util.Locale;

/**
 * @author jenshadlich@googlemail.com
 */
public class SimpleHash {

    public static void main(String... args) {
        for (String s : new String[]{"foo", "bar", "baz", "zab", "bag"}) {
            final int h = hash(s);
            final int b = Math.abs(h % 3);
            System.out.printf(Locale.ENGLISH, "h('%s') = %d => %d\n", s, h, b);
        }
    }

    private static int hash(String key) {
        int h = 0;
        for (char c : key.toCharArray()) {
            h = (h * 37 + c) & 0xFFFFFFFF;
        }

        return h;
    }

}

package de.jeha.fwj;

/**
 * @author jenshadlich@googlemail.com
 */
public class SimpleHash {

    public static void main(String... args) {
        for (String s : new String[]{"foo", "bar", "baz"})
            System.out.println(hash(s));
    }

    private static int hash(String key) {
        int h = 0;
        for (char c : key.toCharArray())
            h = (h * 37 + c) & 0xFFFFFFFF;
        return h;
    }

}

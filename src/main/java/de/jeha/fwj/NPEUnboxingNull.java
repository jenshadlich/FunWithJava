package de.jeha.fwj;

/**
 * @author jenshadlich@googlemail.com
 */
public class NPEUnboxingNull {

    public static void main(String ... args) {
        Object o = null;

        someMethod((Long) o);
    }

    private static void someMethod(long l) {
        System.out.println(l);
    }

}

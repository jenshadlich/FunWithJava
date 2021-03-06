package de.jeha.fwj;

import java.util.concurrent.atomic.AtomicLong;

public class AtomicLongTest {

    static AtomicLong al = new AtomicLong(0);
    static long count, l;
    static final long F = 4_294_967_296L;
    static final int DEFAULT_COUNT = 20_000;
    static boolean ok = false, error = false;

    static void update() {
        l += F;
        al.addAndGet(F);

        if (l == al.longValue()) {
            printOK();
        } else {
            printError();
        }
    }

    static public void main(String... args) {
        final int n = args.length > 0 ? Integer.parseInt(args[0]) : DEFAULT_COUNT;

        for (count = 0; count < n; count++) {
            update();
        }
    }

    static void printOK() {
        if (!ok) {
            System.out.println("OK (iteration " + count + ")");
            ok = true;
            error = false;
        }
    }

    static void printError() {
        if (!error) {
            System.out.println("Error (iteration " + count + ", " + l + " != " + al.longValue());
            error = true;
            ok = false;
        }
        l = al.longValue(); // make them equal again
    }
}

package de.jeha.fwj;

import java.util.concurrent.atomic.AtomicLong;

public class AtomicLongTest {

    static AtomicLong al = new AtomicLong(0);
    static long count, l;
    static final long f = 4_294_967_296L;

    static void update() {
        l += f;
        al.addAndGet(f);

        if (l == al.longValue()) {
            printOK();
        } else {
            printError();
        }
    }

    static public void main(String... args) {
        final int n = args.length > 0 ? Integer.parseInt(args[0]) : 20_000;

        for (count = 0; count < n; count++) {
            update();
        }
    }

    static boolean ok = false, error = false;

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

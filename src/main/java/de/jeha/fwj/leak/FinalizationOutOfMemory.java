package de.jeha.fwj.leak;

import java.util.zip.Deflater;
import java.util.zip.Inflater;

/**
 * @author jenshadlich@googlemail.com
 */
public class FinalizationOutOfMemory {

    public static void main(String args[]) {
        final boolean triggerGC = true; // if false, system will be OOM pretty soon
        int i = 0;

        while (true) {
            final Deflater deflater = new Deflater(9, true);
            final Inflater inflater = new Inflater(true);
            i++;
            if ((i % 100 == 0) && triggerGC) {
                System.gc();
            }
        }
    }

}
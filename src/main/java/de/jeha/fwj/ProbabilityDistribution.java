package de.jeha.fwj;

import org.apache.commons.math3.distribution.FDistribution;
import org.apache.commons.math3.distribution.RealDistribution;

import java.util.Locale;

/**
 * @author jenshadlich@googlemail.com
 */
public class ProbabilityDistribution {

    public static void main(String... args) {
        RealDistribution distribution = new FDistribution(20.0, 20.0);

        double x = 2.1242;
        System.out.printf(Locale.US, "P(X <= %.5f) = %.5f\n", x, distribution.cumulativeProbability(x));
        System.out.println();

        for (int i = 0; i < 10; i++) {
            double sample = distribution.sample() / x;
            int n = (int)(sample * 1000);
            System.out.printf(Locale.US, "%.5f -> %d\n", sample, n);
        }
    }
}

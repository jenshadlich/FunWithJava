package de.jeha.fwj;

import de.jeha.fwj.RadarCoordinateCalculator.Entry;
import de.jeha.fwj.RadarCoordinateCalculator.Result;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class RadarCoordinateCalculatorTest {
    @Test
    public void test() {
        Entry e = new Entry("q1", "r1", "a");
        List<Entry> data = Arrays.asList(e);

        RadarCoordinateCalculator calculator = new RadarCoordinateCalculator(data);

        Map<Entry, Result> result = calculator.run();
        assertEquals(50, result.get(e).r);
        assertEquals(27, result.get(e).s);
    }
}

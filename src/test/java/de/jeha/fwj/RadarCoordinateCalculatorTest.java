package de.jeha.fwj;

import de.jeha.fwj.RadarCoordinateCalculator.Entry;
import de.jeha.fwj.RadarCoordinateCalculator.Result;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class RadarCoordinateCalculatorTest {

    @Test
    public void test1() {
        Entry e = new Entry("q1", "r1", "a");
        List<Entry> data = Arrays.asList(e);

        RadarCoordinateCalculator calculator = new RadarCoordinateCalculator(data);

        Map<Entry, Result> result = calculator.run();
        assertEquals(50, result.get(e).r);
        assertEquals(45, result.get(e).s);
    }

    @Test
    public void test2() {
        Entry e1 = new Entry("q1", "r1", "a");
        Entry e2 = new Entry("q1", "r1", "b");
        List<Entry> data = Arrays.asList(e1, e2);

        RadarCoordinateCalculator calculator = new RadarCoordinateCalculator(data);

        Map<Entry, Result> result = calculator.run();

        assertEquals(50, result.get(e1).r);
        assertEquals(27, result.get(e1).s);

        assertEquals(50, result.get(e2).r);
        assertEquals(62, result.get(e2).s);
    }
}

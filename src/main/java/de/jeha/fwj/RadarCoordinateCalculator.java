package de.jeha.fwj;

import java.util.*;

public class RadarCoordinateCalculator {

    private final int ringSize = 100;
    // rings
    private final List<String> rings;
    // sections: if 4 = quadrants
    private final List<String> sections;

    private final List<Entry> data;

    final int sectionSize;
    final int sectionPadding = 10;

    public RadarCoordinateCalculator(List<Entry> data) {
        this.rings = Arrays.asList("r1", "r2", "r3", "r4");
        this.sections = Arrays.asList("q1", "q2", "q3", "q4");
        this.sectionSize = 360 / sections.size();
        this.data = data;
    }

    public static void main(String... args) {

        List<Entry> data = new ArrayList<>();
        data.add(new Entry("q1", "r1", "a"));
        data.add(new Entry("q1", "r1", "b"));
        data.add(new Entry("q1", "r1", "c"));
        data.add(new Entry("q1", "r2", "d"));
        data.add(new Entry("q2", "r1", "e"));
        data.add(new Entry("q2", "r1", "f"));
        data.add(new Entry("q3", "r3", "g"));
        data.add(new Entry("q4", "r4", "h"));
        data.add(new Entry("q4", "r4", "i"));
        data.add(new Entry("q4", "r4", "j"));
        data.add(new Entry("q4", "r4", "k"));

        new RadarCoordinateCalculator(data).run();
    }

    private String getKey(String section, String ring) {
        return section + "-" + ring;
    }

    public Map<Entry, Result> run() {
        Map<String, List<Entry>> dataMap = new HashMap<>();
        for (Entry e : data) {
            String key = getKey(e.section, e.ring);
            if (!dataMap.containsKey(key)) {
                dataMap.put(key, new ArrayList<>());
            }
            final List<Entry> entryList = dataMap.get(key);
            entryList.add(e);
        }

        System.out.println(sectionSize);

        Map<Entry, Result> result = new HashMap<>();
        for (String section : sections) {
            for (String ring : rings) {
                String key = getKey(section, ring);
                if (dataMap.containsKey(key)) {
                    List<Entry> items = dataMap.get(key);
                    System.out.println(key + " -> " + items.size());
                    int i = 0;
                    for (Entry e : items) {
                        final int r = computeRingCoordinate(e.ring);
                        final int s = computeSectionCoordinate(e.section, i, items.size());
                        System.out.println(" " + e.name);
                        System.out.println("  r=" + r + ", s=" + s);
                        result.put(e, new Result(r, s));
                        i++;
                    }
                }
            }
        }
        return result;
    }

    private int computeSectionCoordinate(String section, int itemIndex, int itemCount) {
        int sOffset = sections.indexOf(section) * sectionSize;
        int sDelta = (sectionSize - (sectionPadding * 2)) / itemCount;
        final int s;
        if (itemCount > 1) {
            s = sOffset + sectionPadding + itemIndex * sDelta + (sectionSize - 2 * sectionPadding) / 4;
        } else {
            s = sOffset + (sectionSize / 2);
        }
        return s;
    }

    private int computeRingCoordinate(String ring) {
        int rOffset = rings.indexOf(ring) * ringSize;
        int r = rOffset + (ringSize / 2); // place in the center of the ring
        return r;
    }

    public static class Result {
        final int r;
        final int s;

        public Result(int r, int s) {
            this.r = r;
            this.s = s;
        }
    }

    public static class Entry {
        final String section;
        final String ring;
        final String name;

        public Entry(String section, String ring, String name) {
            this.section = section;
            this.ring = ring;
            this.name = name;
        }
    }
}

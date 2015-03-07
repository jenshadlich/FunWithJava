package de.jeha.fwj;

import java.util.*;

public class RadarCoordinateCalculator {

    private final int ringSize = 100;
    private final int minDistance = 10; // TODO
    // rings
    private final List<String> rings = Arrays.asList("r1", "r2", "r3", "r4");
    // sections: if 4 = quadrants
    private final List<String> sections = Arrays.asList("q1", "q2", "q3", "q4");

    private final List<Entry> data;

    public RadarCoordinateCalculator(List<Entry> data) {
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

        int sectionSize = 360 / sections.size();
        int sectionPadding = 10;
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
                        int rOffset = rings.indexOf(e.ring) * ringSize;
                        int sOffset = sections.indexOf(e.section) * sectionSize;
                        int r = rOffset + (ringSize / 2); // place in the center of the ring
                        int sDelta = (sectionSize - (sectionPadding * 2)) / items.size();
                        final int s;
                        if (items.size() > 1) {
                            s = sOffset + sectionPadding + i * sDelta + (sectionSize - 2 * sectionPadding) / 4;
                        }else {
                            s = sOffset + (sectionSize / 2);
                        }
                        System.out.println(" " + e.name);
                        //System.out.println(" rOffset=" + rOffset);
                        //System.out.println(" sOffset=" + sOffset);
                        System.out.println("  r=" + r + ", s=" + s);
                        result.put(e, new Result(r, s));
                        i++;
                    }
                }
            }
        }
        return result;
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

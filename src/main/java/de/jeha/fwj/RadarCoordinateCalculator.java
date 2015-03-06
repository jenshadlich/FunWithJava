package de.jeha.fwj;

import java.util.*;

public class RadarCoordinateCalculator {

    public static void main(String... args) {
        int ringSize = 100;
        int minDistance = 10; // TODO
        // rings
        List<String> rings = Arrays.asList("r1", "r2", "r3", "r4");
        // sections: if 4 = quadrants
        List<String> sections = Arrays.asList("q1", "q2", "q3", "q3");

        List<Entry> data = new ArrayList<>();
        data.add(new Entry("q1", "r1", "a"));
        data.add(new Entry("q1", "r1", "b"));
        data.add(new Entry("q1", "r2", "c"));
        data.add(new Entry("q2", "r1", "d"));
        data.add(new Entry("q2", "r1", "e"));
        data.add(new Entry("q3", "r3", "f"));
        data.add(new Entry("q4", "r4", "g"));

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
                        int sDelta = (sectionSize - sectionPadding * 2) / items.size();
                        int s = sOffset + sectionPadding + i * sDelta;
                        System.out.println(" " + e.name);
                        //System.out.println(" rOffset=" + rOffset);
                        //System.out.println(" sOffset=" + sOffset);
                        System.out.println("  r=" + r + ", s=" + s);
                        i++;
                    }
                }
            }
        }
    }

    private static String getKey(String section, String ring) {
        return section + "-" + ring;
    }

    private static class Entry {
        final String section;
        final String ring;
        final String name;

        private Entry(String section, String ring, String name) {
            this.section = section;
            this.ring = ring;
            this.name = name;
        }
    }
}

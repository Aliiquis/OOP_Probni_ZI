package hr.fer.oop.zi.z1;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class BillionaireStats {

    public static int countCountry(List<BillionaireDatum> dst, String country) {
        return (int) dst.stream()
                .filter(e -> e.getCountry().equals(country))
                .count();
    }

    public static int countAgeRange(List<BillionaireDatum> dst, int lower, int upper) {
        return (int) dst.stream()
                .filter(e -> e.getAge() != null && e.getAge() >= lower && e.getAge() <= upper)
                .count();
    }

    public static Map<Integer, Long> makeHistogram(List<BillionaireDatum> dst, int binWidth) {
        dst.sort(Comparator.comparing(BillionaireDatum::getWorth));
        int min = dst.get(0).getWorth();
        return dst.stream()
                .map(BillionaireDatum::getWorth)
                .collect(Collectors.groupingBy(e -> ((e - min) / binWidth) * binWidth + min, TreeMap::new, Collectors.counting()));
    }
}

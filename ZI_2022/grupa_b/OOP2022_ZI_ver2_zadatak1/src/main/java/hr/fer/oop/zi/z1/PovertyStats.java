package hr.fer.oop.zi.z1;


import java.util.AbstractMap;
import java.util.Comparator;
import java.util.List;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class PovertyStats {
    private final List<PovertyDatum> dataset;

    public PovertyStats(List<PovertyDatum> dataset) {
        this.dataset = dataset;
    }

    public List<String> topImproving(int cutoff) {
        return dataset.stream()
                .collect(Collectors.groupingBy(e -> e.getLocation(), TreeMap::new, Collectors.toList()))
                .entrySet()
                .stream()
                .map(e -> {
                    String key = e.getKey();
                    List<PovertyDatum> list = e.getValue();
                    if (list.size() < 2) return new AbstractMap.SimpleEntry<>(key, -50.);
                    double last = list.get(list.size() - 1).getProportion();
                    double stlast = list.get(list.size() - 2).getProportion();
                    return new AbstractMap.SimpleEntry<>(key, last - stlast);
                })
                .sorted(Comparator.comparing(e -> -e.getValue()))
                .filter(e -> e.getValue() >= 0)
                .map(e -> e.getKey())
                .limit(cutoff)
                .collect(Collectors.toList());
    }

    public List<String> topWorsening(int cutoff) {
        return dataset.stream()
                .collect(Collectors.groupingBy(e -> e.getLocation(), TreeMap::new, Collectors.toList()))
                .entrySet()
                .stream()
                .map(e -> {
                    String key = e.getKey();
                    List<PovertyDatum> list = e.getValue();
                    if (list.size() < 2) return new AbstractMap.SimpleEntry<>(key, 50.);
                    double last = list.get(list.size() - 1).getProportion();
                    double stlast = list.get(list.size() - 2).getProportion();
                    return new AbstractMap.SimpleEntry<>(key, last - stlast);
                })
                .sorted(Comparator.comparing(e -> e.getValue()))
                .filter(e -> e.getValue() <= 0)
                .map(e -> e.getKey())
                .limit(cutoff)
                .collect(Collectors.toList());
    }

    public PovertyDatum theWorst() {
        return dataset.stream()
                .max(Comparator.comparing(PovertyDatum::getProportion)).orElse(null);
    }
}

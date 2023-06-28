package hr.fer.oop.stats;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.stream.Stream;

public class Lambdas {

    public static BiConsumer<Stream<String>,
            Map<String, Collection<Entry<Integer, Long>>>> loadFromStream = (lines, map) -> lines.forEach(line -> {
        String[] args = line.split(",");
        String name = args[0];
        Integer year = Integer.parseInt(args[1]);
        Long value = Long.parseLong(args[2]);
        map.computeIfAbsent(name, v -> new ArrayList<>());
        Collection<Entry<Integer, Long>> col = map.get(name);
        col.add(new AbstractMap.SimpleEntry<>(year, value));
        map.compute(name, (k, v) -> col);
    });

    public static BiFunction<Map<String, Collection<Entry<Integer, Long>>>,
            Integer, Collection<Entry<String, Long>>> getValuesForYear = (map, year) -> {
        Collection<Entry<String, Long>> list = new ArrayList<>();
        map.entrySet().forEach(e -> e.getValue().forEach(entry -> {
            if (entry.getKey().equals(year)) {
                list.add(new AbstractMap.SimpleEntry<>(e.getKey(), entry.getValue()));
            }
        }));
        return list;
    };

    public static BiFunction<Map<String, Collection<Entry<Integer, Long>>>,
            String, Entry<Integer, Long>> getMinForCountry = (map, country) -> {
        AtomicReference<Integer> year = new AtomicReference<>();
        AtomicReference<Long> minValue = new AtomicReference<>(Long.MAX_VALUE);
        map.entrySet().stream().filter(k -> k.getKey().equals(country)).forEach(e -> e.getValue().forEach(entry -> {
            if (entry.getValue() < minValue.get()) {
                minValue.set(entry.getValue());
            }
        }));
        map.entrySet().forEach(e -> e.getValue().forEach(entry -> {
            if (entry.getValue().equals(minValue.get())) {
                year.set(entry.getKey());
            }
        }));
        return new AbstractMap.SimpleEntry<>(year.get(), minValue.get());
    };
}

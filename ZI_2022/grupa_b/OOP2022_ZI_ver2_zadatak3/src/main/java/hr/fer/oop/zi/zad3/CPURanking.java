package hr.fer.oop.zi.zad3;

import java.util.Comparator;

public class CPURanking {

    private CPURanking() {
    }

    public static Comparator<CPU> hasHyperthreadingFirst() {
        return Comparator.comparing(CPU::hasHyperthreading).reversed();
    }

    public static Comparator<CPU> withinBudgetFirst(int budget) {
        return Comparator.comparing(cpu -> cpu.getCost() > budget);
    }

    public static Comparator<CPU> byPotentialSpeed() {
        return Comparator.comparing(CPU::getClockSpeed)
                .thenComparing(CPU::getCores).reversed()
                .thenComparing(hasHyperthreadingFirst());
    }

    public static Comparator<CPU> byCostEfficiency() {
        return Comparator.comparing(cpu -> cpu.getCost() / (cpu.getClockSpeed() * cpu.getCores() * (cpu.hasHyperthreading() ? 1.2 : 1)));
    }

    public static Comparator<CPU> byAffordabilityAndEfficiency(int budget) {
        return withinBudgetFirst(budget)
                .thenComparing(byCostEfficiency())
                .thenComparing(byPotentialSpeed())
                .thenComparing(CPU::getName);
    }
}

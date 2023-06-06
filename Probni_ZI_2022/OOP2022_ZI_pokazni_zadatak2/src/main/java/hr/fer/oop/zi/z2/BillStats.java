package hr.fer.oop.zi.z2;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

public class BillStats {
    public List<Article> getArticlesSortedByQuantityAndDiscount(Bill bill) {
        return bill.getArticles()
                .stream()
                .sorted(Comparator.comparing(Article::getQuantity)
                        .reversed()
                        .thenComparing(Article::getDiscount))
                .toList();
    }

    public List<Double> getBillSums(Stream<Bill> billStream) {
        return billStream.mapToDouble(b ->
                        b.getArticles()
                                .stream()
                                .mapToDouble(a -> a.getBasePrice() * a.getQuantity() - a.totalDiscount())
                                .sum())
                .boxed().toList();
    }

    public Double getTotalBillDiscount(Bill bill) {
        return bill.getArticles()
                .stream()
                .mapToDouble(Article::totalDiscount)
                .sum();
    }

    public Stream<Article> getMostExpensiveArticles(Bill bill, int noOfArticles) {
        return bill.getArticles()
                .stream()
                .filter(a -> a.discountedPrice() < 10)
                .sorted(Comparator.comparing(Article::discountPerUnit).reversed())
                .limit(noOfArticles)
                .sorted(Comparator.comparing(Article::discountPerUnit));
    }
}

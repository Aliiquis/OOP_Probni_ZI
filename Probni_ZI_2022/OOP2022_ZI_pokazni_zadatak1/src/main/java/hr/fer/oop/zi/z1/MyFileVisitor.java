package hr.fer.oop.zi.z1;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class MyFileVisitor extends SimpleFileVisitor<Path> {
    private List<Bill> bills = new ArrayList<>();

    protected Bill loadBill(Path path) throws IOException {
        Bill bill = new Bill(path.toString().substring(path.toString().length() - 5, path.toString().length() - 4));
        Files.readAllLines(path, StandardCharsets.UTF_8).forEach(s -> bill.addArticle(parseArticle(s)));
        return bill;
    }

    protected Article parseArticle(String line) {
        String[] args = line.split(",");
        return new Article(args[0], Double.parseDouble(args[1]), Double.parseDouble(args[2]), Integer.parseInt(args[3]));
    }

    public List<Bill> getBills() {
        bills.sort(Comparator.comparing(b ->
                b.getArticles()
                        .stream()
                        .mapToInt(Article::getQuantity)
                        .sum()));
        return bills;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        if (file.toString().endsWith(".csv")) bills.add(loadBill(file));
        return FileVisitResult.CONTINUE;
    }
}

package hr.fer.oop.ZI2023;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

public class MyFileVisitor extends SimpleFileVisitor<Path> {
    private String[] extensions;
    private int numFiles;
    private int numFolders;
    private long largestFileSize;

    public MyFileVisitor(String[] extensions) {
        this.extensions = extensions;
        this.numFiles = 0;
        this.numFolders = 0;
        this.largestFileSize = 0;
    }

    public int getNumFiles() {
        return numFiles;
    }

    public int getNumFolders() {
        return numFolders;
    }

    public long getLargestFileSize() {
        return largestFileSize;
    }

    @Override
    public FileVisitResult visitFile(Path path, BasicFileAttributes attrs) throws IOException {
        for (String ext : extensions) {
            if (path.toString().endsWith(ext)) {
                numFiles++;
                if (Files.size(path) > largestFileSize) {
                    largestFileSize = Files.size(path);
                }
                break;
            }
        }
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
        numFolders++;
        return FileVisitResult.CONTINUE;
    }
}

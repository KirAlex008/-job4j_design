package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {
    private Map<FileProperty, List<Path>> files = new LinkedHashMap<>();

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        FileProperty fileProperty = new FileProperty(attrs.size(), file.getFileName().toString());
        files.putIfAbsent(fileProperty, new ArrayList<>());
        files.get(fileProperty).add(file.toAbsolutePath());
        return super.visitFile(file, attrs);
    }

    public void print() {
        for (var el : files.keySet()) {
            if (files.get(el).size() > 1) {
                System.out.println(el.getName() + " - " + el.getSize());
                files.get(el).forEach(System.out::println);
            }
        }
    }
}
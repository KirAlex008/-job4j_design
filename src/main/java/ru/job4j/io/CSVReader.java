package ru.job4j.io;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class CSVReader {
    public static void handle(ArgsName argsName) throws Exception {
        List<String> names = getNames(argsName);
        List<String> lexemes = getLexemes(argsName);
        Map<String, Integer> mapOfNames = getMapOfNames(names);
        List<Integer> numbersOfColumns = getColumnsNumbers(mapOfNames, lexemes);
        List<String> allLines = getAllLines(argsName);
        String table = filter(numbersOfColumns, allLines, argsName);
        CSVReader.printToFileOrConsole(argsName, table);
    }

    public static void printToFileOrConsole(ArgsName argsName, String string) {
        String address = argsName.get("out");
        if (address.equals("stdout")) {
            System.out.print(string);
        } else {
            try (PrintWriter out = new PrintWriter(
                    new BufferedOutputStream(
                            new FileOutputStream(address)))) {
                out.write(string);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    public static String filter(List<Integer> nums, List<String> lines, ArgsName argsName) {
        StringBuilder string = new StringBuilder();
        for (String line : lines) {
            String[] word = line.split(argsName.get("delimiter"));
            for (int i : nums) {
                string.append(word[i]).append(argsName.get("delimiter"));
            }
            string.deleteCharAt(string.length() - 1);
            string.append(System.lineSeparator());
        }
        return string.toString();
    }

    public static List<String> getAllLines(ArgsName argsName) throws FileNotFoundException {
        var address = argsName.get("path");
        List<String> lines = new ArrayList<>();
        try (var scanner = new Scanner(new FileReader(address))) {
            while (scanner.hasNext()) {
                var str = scanner.nextLine();
                lines.add(str);
            }
        }
        return lines;
    }

    public static List<Integer> getColumnsNumbers(Map<String, Integer> mapOfNames, List<String> listOfLexemes) {
        List<Integer> indexes = new ArrayList<>();
        for (var el : listOfLexemes) {
            indexes.add(mapOfNames.get(el));
        }
        return indexes;
    }

    public static List<String> getLexemes(ArgsName argsName) {
        String line = argsName.get("filter");
        String[] lexemes = line.split(",");
        return List.of(lexemes);
    }

    public static List<String> getNames(ArgsName argsName) throws FileNotFoundException {
        List<String> listOfAllNames;
        try (Scanner scanner = new Scanner(new FileReader(argsName.get("path")))) {
            String firstLine = scanner.nextLine();
            String[] names = firstLine.split(argsName.get("delimiter"));
            listOfAllNames = Arrays.asList(names);
        }
        return listOfAllNames;
    }

    public static Map<String, Integer> getMapOfNames(List<String> list) {
        return IntStream.range(0, list.size())
                .boxed()
                .collect(Collectors.toMap(list::get, index -> index));
    }

    public static void main(String[] args) throws Exception {
        ArgsName argsName = ArgsName.of(args);
        handle(argsName);
    }
}

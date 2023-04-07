package ru.job4j.io;

import java.util.*;

public class ArgsName {

    private final Map<String, String> values = new HashMap<>();

    public String get(String key) {
        return values.get(key);
    }

    public void parse(String[] args) {
        Arrays.stream(args)
                .map(s -> s.split("=", 2))
                .forEach(s -> values.put(s[0].substring(1), s[1]));
    }

    public static ArgsName of(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("Arguments array are empty.");
        }
        if (args.length != 4) {
            throw new IllegalArgumentException("The amount of arguments is wrong.");
        }
        ArgsName names = new ArgsName();
        names.parse(args);
        return names;
    }

    public static void main(String[] args) {
        ArgsName jvm = ArgsName.of(new String[] {"-Xmx=512", "-encoding=UTF-8"});
        System.out.println(jvm.get("Xmx"));

        ArgsName zip = ArgsName.of(new String[] {"-out=project.zip", "-encoding=UTF-8"});
        System.out.println(zip.get("out"));
    }
}
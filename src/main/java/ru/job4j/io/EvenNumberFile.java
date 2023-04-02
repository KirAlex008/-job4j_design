package ru.job4j.io;

import java.io.FileInputStream;
import java.io.IOException;

public class EvenNumberFile {
    public static void main(String[] args) {
        try (FileInputStream in = new FileInputStream("data/input.txt")) {
            StringBuilder text = new StringBuilder();
            int read;
            while ((read = in.read()) != -1) {
                text.append((char) read);
            }
            String[] strings = text.toString().split(System.lineSeparator());
            for (var el : strings) {
                if (Integer.parseInt(el) % 2 == 0) {
                    System.out.println(el);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

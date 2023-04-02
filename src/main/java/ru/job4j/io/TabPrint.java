package ru.job4j.io;

import java.io.FileOutputStream;
import java.text.DecimalFormat;

public class TabPrint {

    public static void main(String[] args) {
        DecimalFormat dF = new DecimalFormat("00");
        try (FileOutputStream out = new FileOutputStream("data/dataresult.txt")) {
            for (int i = 1; i < 10; i++) {
                for (int j = 1; j < 10; j++) {
                    out.write((dF.format(i * j)).getBytes());
                    out.write(" ".getBytes());
                }
                out.write(System.lineSeparator().getBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

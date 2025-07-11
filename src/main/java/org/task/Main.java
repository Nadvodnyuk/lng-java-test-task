package org.task;

import java.io.*;
import static org.task.LineParser.parseLineOrReturnNull;

public class Main {

    public static void main(String[] args) throws IOException {
        long start = System.nanoTime();
        if (args.length == 0) {
            System.err.println("Ошибка: не передан путь к файлу.");
            System.exit(1);
        }
        String pathToFile = args[0];

        String line;
        int count = 0;

        try (
                BufferedReader reader = new BufferedReader(new FileReader(pathToFile))
        ) {
            while ((line = reader.readLine()) != null) {
                if (parseLineOrReturnNull(line) != null){
                    count += 1;
                }
            }
        }

        try (
                BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt"))
        ) {
            writer.write("Количество строк: " + count);
        }
        long end = System.nanoTime();
        long elapsedNs = end - start;
        System.out.printf("Время выполнения: %.3f мс%n", elapsedNs / 1_000_000_000.0);
    }
}
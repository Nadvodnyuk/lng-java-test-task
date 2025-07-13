package org.task;

import java.io.*;
import java.util.*;

import static org.task.GroupManager.*;
import static org.task.GroupSort.*;
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
        String[] cols;

        int groupLineId = 0;

        Map<ValueColumn, Integer> lineElements = new HashMap<>();

        List<String> groupLines = new ArrayList<>();
        List<Integer> parent = new ArrayList<>();
        List<Integer> rank = new ArrayList<>();

        Set<String> seenLine = new HashSet<>();

        try (
                BufferedReader reader = new BufferedReader(new FileReader(pathToFile));
                BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt"))
        ) {
            while ((line = reader.readLine()) != null) {
                // проверка на корректность и поиск множества уникальных строчек
                if ((cols = parseLineOrReturnNull(line))!= null && seenLine.add(line)){
                    groupLines.add(line);
                    makeSet(parent, rank);

                    for (int i = 0; i < cols.length; i++){
                        ValueColumn keyElement = new ValueColumn(cols[i], i);

                        // "Если две строчки имеют совпадения непустых значений в одной или более колонках, они принадлежат одной группе"
                        if (lineElements.containsKey(keyElement) && !cols[i].isEmpty()) {
                            int sameElementLine = lineElements.get(keyElement);
                            mergeGroups(groupLineId, sameElementLine, parent, rank);
                        }

                        lineElements.put(keyElement, groupLineId);
                    }

                    groupLineId++;
                }
            }

            Map<Integer, List<Integer>> groups = buildGroups(parent, groupLineId);

            List<List<Integer>> sortedGroups = sortGroups(groups);

            int groupsOverOne = countGroupsOverOne(sortedGroups);

            int groupNumber = 1;

            //"В начале вывода указать получившееся число групп с более чем одним элементом."
            writer.write("Получившееся число групп с более чем одним элементом " + groupsOverOne + "\n");
            for (List<Integer> group : sortedGroups) {
                writer.write("Группа " + groupNumber++ + "\n");
                for (int lineNumber : group) {
                    writer.write(groupLines.get(lineNumber) + "\n");
                }
                writer.newLine();
            }
        }

        long end = System.nanoTime();
        long totalNs = end - start;
        System.out.printf("Время выполнения: %.3f с%n", totalNs / 1_000_000_000.0);
    }
}
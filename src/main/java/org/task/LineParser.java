package org.task;

import java.util.Arrays;

public class LineParser {
    // проверка строк на корректность и убирание ковычек
    public static String[] parseLineOrReturnNull(String line) {
        if (line == null || line.isEmpty()){
            return null;
        }
        String[] cols = line.split(";", -1);
        for (String col : cols){
            if (!(col.length() >= 2 && col.startsWith("\"") && col.endsWith("\""))){
                return null;
            }
            String inner = col.substring(1, col.length() - 1);
            if (inner.indexOf('"') >= 0) {
                return null;
            }
        }
        cols = Arrays.stream(cols)
                .map(col -> col.substring(1, col.length() - 1))
                .toArray(String[]::new);
        return cols;
    }
}

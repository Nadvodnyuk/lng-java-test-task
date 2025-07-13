package org.task;

import java.util.*;

public class GroupSort {

    public static Map<Integer, List<Integer>> buildGroups(List<Integer> parent, int totalLines) {
        Map<Integer, List<Integer>> groups = new HashMap<>();
        for (int i = 0; i < totalLines; i++) {
            int root = GroupManager.findParent(i, parent);
            groups.computeIfAbsent(root, k -> new ArrayList<>()).add(i);
        }
        return groups;
    }

    //"Сверху расположить группы с наибольшим числом элементов"
    public static List<List<Integer>> sortGroups(Map<Integer, List<Integer>> groups) {
        List<List<Integer>> sorted = new ArrayList<>(groups.values());
        sorted.sort((g1, g2) -> {
            int sizeCompare = Integer.compare(g2.size(), g1.size());
            if (sizeCompare != 0) {
                return sizeCompare;
            }
            return Integer.compare(g1.get(0), g2.get(0));
        });
        return sorted;
    }

    // вычисление числа групп с более чем одним элементом
    public static int countGroupsOverOne(List<List<Integer>> groups) {
        int count = 0;
        for (List<Integer> group : groups) {
            if (group.size() > 1) {
                count++;
            }
        }
        return count;
    }

}
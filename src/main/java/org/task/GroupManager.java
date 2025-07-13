package org.task;

import java.util.List;

public class GroupManager {
    // все для разбиения на на непересекающиеся группы
    public static void makeSet(List<Integer> parent, List<Integer> rank) {
        int lineId = parent.size();
        parent.add(lineId);
        rank.add(1);
    }

    public static int findParent(int lineNumber, List<Integer> parent) {
        int parentValue = parent.get(lineNumber);
        if (parentValue == lineNumber){
            return lineNumber;
        }else{
            int newParent = findParent(parentValue, parent);
            parent.set(lineNumber, newParent);
            return newParent;
        }
    }

    public static boolean mergeGroups(int lineNumberA, int lineNumberB, List<Integer> parent, List<Integer> rank) {
        int parentValueA = findParent(lineNumberA, parent);
        int parentValueB = findParent(lineNumberB, parent);
        if (parentValueA == parentValueB) {
            return false;
        } else {
            if (rank.get(parentValueA) < rank.get(parentValueB)){
                parent.set(parentValueA, parentValueB);
            } else if(rank.get(parentValueA) > rank.get(parentValueB)){
                parent.set(parentValueB, parentValueA);
            }else{
                parent.set(parentValueB, parentValueA);
                rank.set(parentValueA, rank.get(parentValueA) + 1);
            }
            return true;
        }
    }
}

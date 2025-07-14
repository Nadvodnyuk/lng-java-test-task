import org.junit.jupiter.api.Test;
import org.task.GroupSort;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class GroupSortTest {

    @Test
    public void buildGroupsTest() {
        List<Integer> parent = Arrays.asList(0, 0, 2, 3);
        Map<Integer, List<Integer>> groups = GroupSort.buildGroups(parent, 4);

        assertEquals(3, groups.size());
        assertTrue(groups.get(0).containsAll(Arrays.asList(0, 1)));
        assertTrue(groups.get(2).contains(2));
        assertTrue(groups.get(3).contains(3));
    }

    @Test
    public void sortGroupsTest() {
        Map<Integer, List<Integer>> groups = new HashMap<>();
        groups.put(1, Arrays.asList(1, 2));
        groups.put(3, Arrays.asList(3, 4, 5));
        groups.put(6, Arrays.asList(6));
        groups.put(7, Arrays.asList(7,8));
        List<List<Integer>> sortedGroups = GroupSort.sortGroups(groups);
        assertEquals(Arrays.asList(3, 4, 5), sortedGroups.get(0));
        assertEquals(Arrays.asList(1, 2), sortedGroups.get(1));
        assertEquals(Arrays.asList(7, 8), sortedGroups.get(2));
        assertEquals(Arrays.asList(6), sortedGroups.get(3));
    }

    @Test
    public void testCountGroupsOverOne() {
        List<List<Integer>> groups = Arrays.asList(
                Arrays.asList(1, 2 ,3),
                Arrays.asList(4, 5),
                Arrays.asList(6)
        );
        int count = GroupSort.countGroupsOverOne(groups);
        assertEquals(2, count);
    }
}

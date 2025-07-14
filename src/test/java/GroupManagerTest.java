import org.junit.jupiter.api.Test;
import org.task.GroupManager;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class GroupManagerTest {

    @Test
    public void testMakeSet() {
        List<Integer> parent = new ArrayList<>();
        List<Integer> rank = new ArrayList<>();

        GroupManager.makeSet(parent, rank);
        GroupManager.makeSet(parent, rank);

        assertEquals(List.of(0, 1), parent);
        assertEquals(List.of(1, 1), rank);
    }

    @Test
    public void FindParentTest() {
        List<Integer> parent = new ArrayList<>();
        parent.add(0);
        parent.add(0);
        parent.add(2);
        parent.add(0);

        assertEquals(0, GroupManager.findParent(0, parent));
        assertEquals(0, GroupManager.findParent(3, parent));
        assertEquals(2, GroupManager.findParent(2, parent));
    }

    @Test
    public void mergeGroupsTest(){
        List<Integer> parent = new ArrayList<>();
        List<Integer> rank = new ArrayList<>();

        for (int i = 0; i < 4; i++) {
            GroupManager.makeSet(parent, rank);
        }

        assertTrue(GroupManager.mergeGroups(0, 1, parent, rank));
        assertEquals(GroupManager.findParent(0, parent), GroupManager.findParent(1, parent));

        assertTrue(GroupManager.mergeGroups(1, 2, parent, rank));
        assertEquals(GroupManager.findParent(0, parent), GroupManager.findParent(2, parent));

        assertFalse(GroupManager.mergeGroups(2, 0, parent, rank));

    }
}
